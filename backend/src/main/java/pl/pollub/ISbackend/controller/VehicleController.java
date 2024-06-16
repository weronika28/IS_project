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


    @PostMapping("/import/api")
    public ResponseEntity<String> importFromApi(@RequestParam String wojewodztwo) {
        String apiUrlBase = "https://api.cepik.gov.pl/pojazdy?data-od=20230101&data-do=20231231&typ-daty=1&tylko-zarejestrowane=true&pokaz-wszystkie-pola=false&fields=data-pierwszej-rejestracji-w-kraju&fields=data-ostatniej-rejestracji-w-kraju&fields=data-wyrejestrowania-pojazdu&fields=rejestracja-wojewodztwo&fields=rejestracja-gmina&fields=rejestracja-powiat&fields=marka&fields=rodzaj-paliwa&fields=pojemnosc-skokowa-silnika&fields=moc-netto-silnika&fields=masa-wlasna&fields=dopuszczalna-masa-calkowita&fields=liczba-miejsc-siedzacych&fields=model&fields=rodzaj-pojazdu&limit=500";
        try {
            vehicleService.importFromApi(apiUrlBase, wojewodztwo);
            return ResponseEntity.ok("Import from API successful");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Import from API failed: " + e.getMessage());
        }
    }
}
