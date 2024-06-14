package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.service.FireDepartmentDataService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fire-department")
public class FireDepartmentDataController {

    @Autowired
    private FireDepartmentDataService fireDepartmentDataService;

    @GetMapping("/data-summary")
    public ResponseEntity<Map<String, Double>> getDataSummary() {
        Map<String, Double> summary = fireDepartmentDataService.getDataSummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/count-by-wojewodztwo")
    public ResponseEntity<Map<String, Long>> getCountByWojewodztwo() {
        Map<String, Long> countByWojewodztwo = fireDepartmentDataService.getCountByWojewodztwo();
        return ResponseEntity.ok(countByWojewodztwo);
    }

    @GetMapping("/operation-type-percentage")
    public ResponseEntity<Map<String, Double>> getOperationTypePercentage() {
        Map<String, Double> percentageMap = fireDepartmentDataService.getOperationTypePercentage();
        return ResponseEntity.ok(percentageMap);
    }

    @GetMapping("/voivodeship-data")
    public ResponseEntity<Map<String, Map<String, Double>>> getVoivodeshipData() {
        Map<String, Long> countByWojewodztwo = fireDepartmentDataService.getCountByWojewodztwo();
        Map<String, Double> avgTimePerKmByWojewodztwo = fireDepartmentDataService.calculateAverageTimePerKilometer();

        Map<String, Map<String, Double>> voivodeshipData = new HashMap<>();
        for (String wojewodztwo : countByWojewodztwo.keySet()) {
            Map<String, Double> data = new HashMap<>();
            data.put("count", countByWojewodztwo.get(wojewodztwo).doubleValue());
            data.put("avgTimePerKm", avgTimePerKmByWojewodztwo.getOrDefault(wojewodztwo, 0.0));
            voivodeshipData.put(wojewodztwo, data);
        }

        return ResponseEntity.ok(voivodeshipData);
    }

    @GetMapping("/gmina-data")
    public ResponseEntity<Map<String, Map<String, Double>>> getGminaData() {
        Map<String, Long> countByGmina = fireDepartmentDataService.getCountByGmina();
        Map<String, Double> avgTimePerKmByGmina = fireDepartmentDataService.calculateAverageTimePerKilometerByGmina();

        Map<String, Map<String, Double>> gminaData = new HashMap<>();
        for (String gmina : countByGmina.keySet()) {
            Map<String, Double> data = new HashMap<>();
            data.put("count", countByGmina.get(gmina).doubleValue());
            data.put("avgTimePerKm", avgTimePerKmByGmina.getOrDefault(gmina, 0.0));
            gminaData.put(gmina, data);
        }

        return ResponseEntity.ok(gminaData);
    }

    @GetMapping("/selected-gminas-data")
    public ResponseEntity<Map<String, Map<String, Double>>> getSelectedGminasData() {
        Map<String, Long> countByGmina = fireDepartmentDataService.getCountByGmina();
        Map<String, Double> avgTimePerKmByGmina = fireDepartmentDataService.calculateAverageTimePerKilometerByGmina();

        Map<String, Map<String, Double>> selectedGminasData = new HashMap<>();
        for (String gmina : countByGmina.keySet()) {
            if ("Opole".equals(gmina) || "Nysa".equals(gmina) || "Szczecin".equals(gmina) ||
                    "Przysucha".equals(gmina) || "Radom".equals(gmina) || "Limanowa".equals(gmina) ||
                    "Brzeg".equals(gmina) || "Lipno".equals(gmina)) {
                Map<String, Double> data = new HashMap<>();
                data.put("count", countByGmina.get(gmina).doubleValue());
                data.put("avgTimePerKm", avgTimePerKmByGmina.getOrDefault(gmina, 0.0));
                selectedGminasData.put(gmina, data);
            }
        }

        return ResponseEntity.ok(selectedGminasData);
    }

}
