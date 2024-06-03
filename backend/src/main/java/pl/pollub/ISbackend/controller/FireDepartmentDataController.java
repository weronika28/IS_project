package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.service.FireDepartmentDataService;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/fire-department")
public class FireDepartmentDataController {

    private static final Logger logger = LoggerFactory.getLogger(FireDepartmentDataController.class);

    @Autowired
    private FireDepartmentDataService fireDepartmentDataService;

    @GetMapping("/data-summary")
    public ResponseEntity<Map<String, Double>> getDataSummary() {
        Map<String, Double> summary = fireDepartmentDataService.getDataSummary();
        logger.info("Returning data summary: {}", summary);
        return ResponseEntity.ok(summary);
    }
}
