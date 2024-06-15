package pl.pollub.ISbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.repository.VehicleDataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int countVehiclesByRejestracjaGmina(String rejestracjaGmina) {
        return vehicleDataRepository.countVehiclesByRejestracjaGmina(rejestracjaGmina);
    }

    public Map<String, Object> getVehicleDataByVoivodeship(String voivodeship) {
        Map<String, Object> data = new HashMap<>();

        int count = vehicleDataRepository.countVehiclesByRejestracjaWojewodztwo(voivodeship);
        data.put("count", count);

        List<Object[]> popularBrands = vehicleDataRepository.mostPopularBrandByWojewodztwo();
        List<Object[]> popularFuels = vehicleDataRepository.mostPopularFuelTypeByWojewodztwo();
        List<Object[]> avgEngineCapacity = vehicleDataRepository.averageEngineCapacityByWojewodztwo();

        data.put("mostPopularBrand", popularBrands.get(0)[1]); // Assuming the first result is the most popular
        data.put("mostPopularFuelType", popularFuels.get(0)[1]); // Assuming the first result is the most popular
        data.put("averageEngineCapacity", avgEngineCapacity.get(0)[1]);

        return data;
    }

}
