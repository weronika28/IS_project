package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.model.VehicleStatistics;
import pl.pollub.ISbackend.service.VehicleDataService;
import pl.pollub.ISbackend.service.VehicleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicle-data")
public class VehicleDataController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleDataService vehicleDataService;

    @GetMapping("/voivodeships")
    public List<String> getAllVoivodeships() {
        return vehicleDataService.findAllVoivodeships();
    }

    @GetMapping("/vehicle-count/{rejestracjaWojewodztwo}")
    public int getVehicleCountByRejestracjaWojewodztwo(@PathVariable String rejestracjaWojewodztwo) {
        return vehicleDataService.countVehiclesByRejestracjaWojewodztwo(rejestracjaWojewodztwo);
    }

    @GetMapping("/vehicle-count-gmina/{rejestracjaGmina}")
    public int getVehicleCountByRejestracjaGmina(@PathVariable String rejestracjaGmina) {
        return vehicleDataService.countVehiclesByRejestracjaGmina(rejestracjaGmina);
    }

    @GetMapping("/voivodeship/{voivodeship}")
    public ResponseEntity<VehicleStatistics> getVehicleStatisticsByVoivodeship(@PathVariable String voivodeship) {
        VehicleStatistics stats = vehicleService.getVehicleStatisticsByVoivodeship(voivodeship);
        if (stats != null) {
            return ResponseEntity.ok(stats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{voivodeship}")
    public ResponseEntity<Map<String, Object>> getVehicleDataByVoivodeship(@PathVariable String voivodeship) {
        Map<String, Object> data = vehicleDataService.getVehicleDataByVoivodeship(voivodeship);
        return ResponseEntity.ok(data);
    }
}
