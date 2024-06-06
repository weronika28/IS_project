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
//    @PostMapping("/import/csv")
//    public ResponseEntity<String> importFromCsv(@RequestParam("file") MultipartFile file) {
//        try {
//            vehicleService.importFromCsv(file);
//            return ResponseEntity.ok("Import z CSV zakończony sukcesem");
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Import z CSV nie powiódł się: " + e.getMessage());
//        }
//    }
    @PostMapping("/import/api")
    public ResponseEntity<String> importFromApi(@RequestParam String wojewodztwo) {
        String apiUrlBase = "https://api.cepik.gov.pl/pojazdy?data-od=20230101&data-do=20231231&typ-daty=1&tylko-zarejestrowane=true&pokaz-wszystkie-pola=false&fields=data-pierwszej-rejestracji-w-kraju&fields=rejestracja-wojewodztwo&fields=rejestracja-powiat&fields=marka&fields=rodzaj-paliwa&limit=500";
        try {
            vehicleService.importFromApi(apiUrlBase, wojewodztwo);
            return ResponseEntity.ok("Import from API successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from API failed: " + e.getMessage());
        }
    }

}
