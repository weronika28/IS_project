package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.repository.VehicleRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

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
        List<Vehicle> vehicles = mapper.readValue(new File(filePath), new TypeReference<List<Vehicle>>(){});
        vehicleRepository.saveAll(vehicles);
    }

    public void exportToXml(String filePath) throws IOException {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(filePath), vehicles);
    }

    public void importFromXml(String filePath) throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<Vehicle> vehicles = mapper.readValue(new File(filePath), new TypeReference<List<Vehicle>>(){});
        vehicleRepository.saveAll(vehicles);
    }
    private String getValueOrNull(String value) {
        return value.isEmpty() ? null : value;
    }

    private Integer getIntegerValueOrNull(String value) {
        return value.isEmpty() ? null : Integer.parseInt(value);
    }

    private Double getDoubleValueOrNull(String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            if (value.contains(",")) {
                return Double.parseDouble(value.replace(",", "."));
            } else {
                return Double.parseDouble(value);
            }
        }
    }

    private Date getDateOrNull(String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void importFromCsv(MultipartFile file) throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int lineNumber = 1;

            List<String> selectedColumnNames = splitLine(reader.readLine());
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    List<String> data = splitLine(line);
                    Vehicle vehicle = new Vehicle();
                    for (int i = 0; i < data.size(); i++) {
                        String columnName = selectedColumnNames.get(i);
                        switch (columnName) {
                            case "ID":
                                vehicle.setId(getLongValueOrNull(data.get(i)));
                                break;
                            case "DATA_PIERWSZEJ_REJESTRACJI_W_KRAJU":
                                vehicle.setDataPierwszejRejestracjiWKraju((data.get(i)));
                                break;
                            case "REJESTRACJA_WOJEWODZTWO":
                                vehicle.setRejestracjaWojewodztwo(data.get(i));
                                break;
                            case "REJESTRACJA_GMINA":
                                vehicle.setRejestracjaGmina(data.get(i));
                                break;
                            case "REJESTRACJA_POWIAT":
                                vehicle.setRejestracjaPowiat(data.get(i));
                                break;
                            default:
                                break;
                        }
                    }
                    vehicles.add(vehicle);
                } catch (Exception e) {
                    System.err.println("Error processing line " + lineNumber + ": " + line);
                    e.printStackTrace();
                    return;
                }
            }
        } catch (IOException e) {
            throw e;
        }

        System.out.println("Liczba wierszy do zapisania: " + vehicles.size());
        vehicleRepository.saveAll(vehicles);
        System.out.println("Zapis danych do bazy danych zakończony.");
    }

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

    private Long getLongValueOrNull(String value) {
        return value.isEmpty() ? null : Long.parseLong(value);
    }
}
