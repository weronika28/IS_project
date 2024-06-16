package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.model.VehicleStatistics;
import pl.pollub.ISbackend.repository.VehicleDataRepository;
import pl.pollub.ISbackend.repository.VehicleRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void importFromApi(String apiUrlBase, String wojewodztwo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> allVehicles = new ArrayList<>();

        int page = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            System.out.println("Fetching page " + page);
            JsonNode dataNode = null;
            boolean error = true;
            int tries = 5;

            while (error) {
                try {
                    String apiUrl = String.format("%s&wojewodztwo=%s&page=%d", apiUrlBase, wojewodztwo, page);
                    String jsonContent = fetchJsonContent(apiUrl);
                    JsonNode rootNode = objectMapper.readTree(jsonContent);
                    dataNode = rootNode.get("data");
                    error = false;
                } catch (Exception e) {
                    System.out.println("Skipping error while fetching data from page " + page + ". " + e.getMessage());
                    tries--;
                    if (tries == 0)
                        throw new RuntimeException(e);
                }
            }

            if (dataNode == null || !dataNode.isArray() || dataNode.size() == 0) {
                hasMoreData = false;
            } else {
                for (JsonNode vehicleNode : dataNode) {
                    try {
                        Vehicle vehicle = createVehicleFromJson(vehicleNode);
                        if (vehicle != null)
                            allVehicles.add(vehicle);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Fetched " + dataNode.size() + " vehicles");
                page++;
            }
        }

        vehicleRepository.saveAll(allVehicles);
    }

    private String fetchJsonContent(String apiUrl) throws IOException {
        try (InputStream input = new URL(apiUrl).openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        }
    }

    private Vehicle createVehicleFromJson(JsonNode vehicleNode) throws ParseException {
        JsonNode attributesNode = vehicleNode.get("attributes");

        if (attributesNode == null) {
            System.err.println("Attributes node is null for vehicle id: " + vehicleNode.get("id"));
            return null;
        }
        Vehicle vehicle = new Vehicle();

        vehicle.setId(getLongValueOrNull(vehicleNode, "id"));
        vehicle.setDataPierwszejRejestracjiWKraju(getValueOrNull(attributesNode, "data-pierwszej-rejestracji-w-kraju"));
        vehicle.setDataOstatniejRejestracjiWKraju(getValueOrNull(attributesNode, "data-ostatniej-rejestracji-w-kraju"));
        vehicle.setDataWyrejestrowaniaPojazdu(getValueOrNull(attributesNode, "data-wyrejestrowania-pojazdu"));
        vehicle.setRejestracjaWojewodztwo(getValueOrNull(attributesNode, "rejestracja-wojewodztwo"));
        vehicle.setRejestracjaGmina(getValueOrNull(attributesNode, "rejestracja-gmina"));
        vehicle.setRejestracjaPowiat(getValueOrNull(attributesNode, "rejestracja-powiat"));
        vehicle.setMarka(getValueOrNull(attributesNode, "marka"));
        vehicle.setRodzajPaliwa(getValueOrNull(attributesNode, "rodzaj-paliwa"));
        vehicle.setPojemnoscSkokowaSilnika(getDoubleValueOrNull(attributesNode, "pojemnosc-skokowa-silnika"));
        vehicle.setMocNettoSilnika(getDoubleValueOrNull(attributesNode, "moc-netto-silnika"));
        vehicle.setMasaWlasna(getDoubleValueOrNull(attributesNode, "masa-wlasna"));
        vehicle.setDopuszczalnaMasaCalkowita(getDoubleValueOrNull(attributesNode, "dopuszczalna-masa-calkowita"));
        vehicle.setLiczbaMiejscSiedzacych(getIntegerValueOrNull(attributesNode, "liczba-miejsc-siedzacych"));
        vehicle.setModel(getValueOrNull(attributesNode, "model"));
        vehicle.setRodzajPojazdu(getValueOrNull(attributesNode, "rodzaj-pojazdu"));


        return vehicle;
    }

    private String getValueOrNull(JsonNode node, String fieldName) {
        if (node == null) {
            return null;
        } else {
            JsonNode valueNode = node.get(fieldName);
            return (valueNode != null && !valueNode.isNull()) ? valueNode.asText() : null;
        }
    }

    private Integer getIntegerValueOrNull(JsonNode node, String fieldName) {
        if (node == null) {
            return null;
        }
        JsonNode valueNode = node.get(fieldName);
        return (valueNode != null && !valueNode.isNull()) ? valueNode.asInt() : null;
    }

    private Double getDoubleValueOrNull(JsonNode node, String fieldName) {
        if (node == null) {
            return null;
        }
        JsonNode valueNode = node.get(fieldName);
        return (valueNode != null && !valueNode.isNull()) ? valueNode.asDouble() : null;
    }

    private Date getDateOrNull(JsonNode node, String fieldName) throws ParseException {
        if (node == null) {
            return null;
        }
        JsonNode valueNode = node.get(fieldName);
        if (valueNode != null && !valueNode.isNull()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(valueNode.asText());
        } else {
            return null;
        }
    }

    private Long getLongValueOrNull(JsonNode node, String fieldName) {
        if (node == null) {
            return null;
        }
        JsonNode valueNode = node.get(fieldName);
        if (valueNode == null) {
            return null;
        }
        if (valueNode.isNull()) {
            return null;
        }
        return valueNode.asLong();
    }

    public VehicleStatistics getVehicleStatisticsByVoivodeship(String voivodeship) {
        long vehicleCount = vehicleDataRepository.countVehiclesByRejestracjaWojewodztwo(voivodeship);

        List<Object[]> mostPopularBrandResult = vehicleDataRepository.findMostPopularBrandByVoivodeship(voivodeship);
        String mostPopularBrand = mostPopularBrandResult.isEmpty() ? "Brak danych" : (String) mostPopularBrandResult.get(0)[0];

        List<Object[]> mostPopularFuelTypeResult = vehicleDataRepository.findMostPopularFuelTypeByVoivodeship(voivodeship);
        String mostPopularFuelType = mostPopularFuelTypeResult.isEmpty() ? "Brak danych" : (String) mostPopularFuelTypeResult.get(0)[0];

        double averageEngineCapacity = vehicleDataRepository.findAverageEngineCapacityByVoivodeship(voivodeship);

        return new VehicleStatistics(voivodeship, vehicleCount, mostPopularBrand, mostPopularFuelType, averageEngineCapacity);
    }

}
