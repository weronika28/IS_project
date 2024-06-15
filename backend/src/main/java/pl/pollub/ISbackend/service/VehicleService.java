package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.model.VehicleStatistics;
import pl.pollub.ISbackend.repository.VehicleDataRepository;
import pl.pollub.ISbackend.repository.VehicleRepository;

import java.io.*;
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

    public void exportToJson(String filePath) throws IOException {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), vehicles);
    }

    public void importFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Vehicle> vehicles = mapper.readValue(new File(filePath), new TypeReference<List<Vehicle>>() {
        });
        vehicleRepository.saveAll(vehicles);
    }

    public void exportToXml(String filePath) throws IOException {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(filePath), vehicles);
    }

    public void importFromXml(String filePath) throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<Vehicle> vehicles = mapper.readValue(new File(filePath), new TypeReference<List<Vehicle>>() {
        });
        vehicleRepository.saveAll(vehicles);
    }


//    public void importFromCsv(MultipartFile file) throws IOException {
//        List<Vehicle> vehicles = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            String line;
//            int lineNumber = 1;
//
//            List<String> selectedColumnNames = splitLine(reader.readLine());
//            while ((line = reader.readLine()) != null) {
//                lineNumber++;
//                try {
//                    List<String> data = splitLine(line);
//                    Vehicle vehicle = new Vehicle();
//                    for (int i = 0; i < data.size(); i++) {
//                        String columnName = selectedColumnNames.get(i);
//                        switch (columnName) {
//                            case "ID":
//                                vehicle.setId(getLongValueOrNull(data.get(i)));
//                                break;
//                            case "DATA_PIERWSZEJ_REJESTRACJI_W_KRAJU":
//                                vehicle.setDataPierwszejRejestracjiWKraju((data.get(i)));
//                                break;
//                            case "REJESTRACJA_WOJEWODZTWO":
//                                vehicle.setRejestracjaWojewodztwo(data.get(i));
//                                break;
//                            case "REJESTRACJA_POWIAT":
//                                vehicle.setRejestracjaPowiat(data.get(i));
//                                break;
//                            case "MARKA":
//                                vehicle.setMarka(data.get(i));
//                                break;
//                            case "RODZAJ_PALIWA":
//                                vehicle.setRodzajPaliwa(data.get(i));
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    vehicles.add(vehicle);
//                } catch (Exception e) {
//                    System.err.println("Error processing line " + lineNumber + ": " + line);
//                    e.printStackTrace();
//                    return;
//                }
//            }
//        } catch (IOException e) {
//            throw e;
//        }
//
//        System.out.println("Liczba wierszy do zapisania: " + vehicles.size());
//        vehicleRepository.saveAll(vehicles);
//        System.out.println("Zapis danych do bazy danych zakończony.");
//    }

    private List<String> splitLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            values.add(sb.toString());
        }
        return values;
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

    private boolean isNumeric(String s) {
        if (s == null) return false;

        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
