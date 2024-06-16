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

    @PostMapping("/import/csv")
    public ResponseEntity<String> importFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            fireDepartmentService.importFromCsv(file);
            return ResponseEntity.ok("Import z CSV zakończony sukcesem");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Import z CSV nie powiódł się: " + e.getMessage());
        }
    }

}

