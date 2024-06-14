package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.service.VehicleDataService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicle-data")
public class VehicleDataController {

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

}
