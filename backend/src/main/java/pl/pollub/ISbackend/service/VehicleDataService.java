package pl.pollub.ISbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.Vehicle;
import pl.pollub.ISbackend.repository.VehicleDataRepository;

import java.util.List;

@Service
public class VehicleDataService {

    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    public List<String> findAllVoivodeships() {
        return vehicleDataRepository.findAllVoivodeships();
    }

    public int countVehiclesByRejestracjaWojewodztwo(String rejestracjaWojewodztwo) {
        return vehicleDataRepository.countVehiclesByRejestracjaWojewodztwo(rejestracjaWojewodztwo);
    }

    // Other service methods as needed
}
