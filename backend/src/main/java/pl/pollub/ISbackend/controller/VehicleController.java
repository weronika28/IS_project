package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.service.VehicleService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAll();
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @GetMapping("/export/json")
    public ResponseEntity<String> exportToJson() {
        try {
            vehicleService.exportToJson("vehicles.json");
            return ResponseEntity.ok("Export to JSON successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Export to JSON failed: " + e.getMessage());
        }
    }

    @PostMapping("/import/json")
    public ResponseEntity<String> importFromJson() {
        try {
            vehicleService.importFromJson("vehicles.json");
            return ResponseEntity.ok("Import from JSON successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from JSON failed: " + e.getMessage());
        }
    }

    @GetMapping("/export/xml")
    public ResponseEntity<String> exportToXml() {
        try {
            vehicleService.exportToXml("vehicles.xml");
            return ResponseEntity.ok("Export to XML successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Export to XML failed: " + e.getMessage());
        }
    }

    @PostMapping("/import/xml")
    public ResponseEntity<String> importFromXml() {
        try {
            vehicleService.importFromXml("vehicles.xml");
            return ResponseEntity.ok("Import from XML successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from XML failed: " + e.getMessage());
        }
    }
}
