package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.repository.VehicleRepository;

import java.io.File;
import java.io.IOException;
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
}
