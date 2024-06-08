package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.service.VehicleDataService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-data")
public class VehicleDataController {

    @Autowired
    private VehicleDataService vehicleDataService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDataService.findAll();
    }
}
