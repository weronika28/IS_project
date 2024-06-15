package pl.pollub.ISbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.ISbackend.service.FireDepartmentDataService;
import pl.pollub.ISbackend.service.VehicleDataService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fire-department")
public class FireDepartmentDataController {

    @Autowired
    private FireDepartmentDataService fireDepartmentDataService;
    @Autowired
    private VehicleDataService vehicleDataService;

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
        Map<String, Long> countByWojewodztwo = fireDepartmentDataService.getCountVehiclesByWojewodztwo();
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
        Map<String, Long> countByGmina = fireDepartmentDataService.getCountVehiclesByGmina();
        Map<String, Double> avgTimePerKmByGmina = fireDepartmentDataService.calculateAverageTimePerKilometerByGmina();

        Map<String, Map<String, Double>> selectedGminasData = new HashMap<>();
        for (String gmina : countByGmina.keySet()) {
            if (gmina != null && (
                    "opole".equalsIgnoreCase(gmina) || "nysa".equalsIgnoreCase(gmina) || "szczecin".equalsIgnoreCase(gmina) ||
                            "sanok".equalsIgnoreCase(gmina) || "przysucha".equalsIgnoreCase(gmina) || "radom".equalsIgnoreCase(gmina) ||
                            "limanowa".equalsIgnoreCase(gmina) || "brzeg".equalsIgnoreCase(gmina) || "lipno".equalsIgnoreCase(gmina))) {
                Map<String, Double> data = new HashMap<>();
                data.put("count", countByGmina.get(gmina).doubleValue());
                data.put("avgTimePerKm", avgTimePerKmByGmina.getOrDefault(gmina.toLowerCase(), 0.0));
                selectedGminasData.put(gmina, data);
            }
        }

        // Logowanie danych przed zwróceniem odpowiedzi
        selectedGminasData.forEach((gmina, data) -> {
            System.out.println("Gmina: " + gmina + ", Data: " + data);
        });

        return ResponseEntity.ok(selectedGminasData);
    }

//    @PostMapping("/export")
//    public ResponseEntity<byte[]> exportData(@RequestParam String format) {
//        byte[] data;
//        String filename;
//
//        if ("xml".equalsIgnoreCase(format)) {
//            data = fireDepartmentDataService.exportToXml();
//            filename = "correlation_data.xml";
//        } else if ("json".equalsIgnoreCase(format)) {
//            data = fireDepartmentDataService.exportToJson();
//            filename = "correlation_data.json";
//        } else {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData(filename, filename);
//        headers.setContentType("xml".equalsIgnoreCase(format) ? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON);
//
//        return ResponseEntity.ok().headers(headers).body(data);
//    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportData(@RequestParam String format, @RequestBody Map<String, Object> requestData) {
        byte[] data;
        String filename;
        MediaType mediaType;

        try {
            if ("xml".equalsIgnoreCase(format)) {
                XmlMapper xmlMapper = new XmlMapper();
                data = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(requestData);
                filename = "correlation_data.xml";
                mediaType = MediaType.APPLICATION_XML;
            } else if ("json".equalsIgnoreCase(format)) {
                ObjectMapper objectMapper = new ObjectMapper();
                data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(requestData);
                filename = "correlation_data.json";
                mediaType = MediaType.APPLICATION_JSON;
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(filename, filename);
        headers.setContentType(mediaType);

        return ResponseEntity.ok().headers(headers).body(data);
    }



}
