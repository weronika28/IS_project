package pl.pollub.ISbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.service.FireDepartmentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fire-department")

public class FireDepartmentController {

    @Autowired
    private FireDepartmentService fireDepartmentService;

    @GetMapping
    public List<FireDepartment> getAllFireDepartments() {
        return fireDepartmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FireDepartment> getFireDepartmentById(@PathVariable String id) {
        Optional<FireDepartment> fireDepartment = fireDepartmentService.findById(id);
        return fireDepartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FireDepartment createFireDepartment(@RequestBody FireDepartment fireDepartment) {
        return fireDepartmentService.save(fireDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FireDepartment> updateFireDepartment(@PathVariable String id, @RequestBody FireDepartment fireDepartmentDetails) {
        Optional<FireDepartment> fireDepartment = fireDepartmentService.findById(id);
        if (fireDepartment.isPresent()) {
            FireDepartment updatedFireDepartment = fireDepartment.get();
            updatedFireDepartment.setFBezJop(fireDepartmentDetails.getFBezJop());

            return ResponseEntity.ok(fireDepartmentService.save(updatedFireDepartment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFireDepartment(@PathVariable String id) {
        fireDepartmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export/json")
    public ResponseEntity<String> exportToJson() {
        try {
            fireDepartmentService.exportToJson("fire_departments.json");
            return ResponseEntity.ok("Export to JSON successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Export to JSON failed: " + e.getMessage());
        }
    }

    @PostMapping("/import/json")
    public ResponseEntity<String> importFromJson() {
        try {
            fireDepartmentService.importFromJson("fire_departments.json");
            return ResponseEntity.ok("Import from JSON successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from JSON failed: " + e.getMessage());
        }
    }

    @PostMapping("/import/csv")
    public ResponseEntity<String> importFromCsv(MultipartFile file) {
        try {
            fireDepartmentService.importFromCsv(file);
            return ResponseEntity.ok("Import from CSV successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from CSV failed: " + e.getMessage());
        }
    }

    @GetMapping("/export/xml")
    public ResponseEntity<String> exportToXml() {
        try {
            fireDepartmentService.exportToXml("fire_departments.xml");
            return ResponseEntity.ok("Export to XML successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Export to XML failed: " + e.getMessage());
        }
    }

    @PostMapping("/import/xml")
    public ResponseEntity<String> importFromXml() {
        try {
            fireDepartmentService.importFromXml("fire_departments.xml");
            return ResponseEntity.ok("Import from XML successful");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import from XML failed: " + e.getMessage());
        }
    }
}

