package pl.pollub.ISbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentDataRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class FireDepartmentDataService {

    @Autowired
    private FireDepartmentDataRepository fireDepartmentDataRepository;

    public Map<String, Double> getDataSummary() {
        PageRequest pageable = PageRequest.of(0, 1000);
        Page<FireDepartment> fireDepartmentsPage = fireDepartmentDataRepository.findAll(pageable);

        Map<String, Double> summary = new HashMap<>();
        summary.put("zuzWody", fireDepartmentsPage.stream().mapToDouble(fd -> fd.getZuzWody() != null ? fd.getZuzWody() : 0).sum());
        summary.put("zuzProszku", fireDepartmentsPage.stream().mapToDouble(fd -> fd.getZuzProszku() != null ? fd.getZuzProszku() : 0).sum());
        summary.put("zuzPiany", fireDepartmentsPage.stream().mapToDouble(fd -> fd.getZuzPiany() != null ? fd.getZuzPiany() : 0).sum());
        summary.put("zuzNeut", fireDepartmentsPage.stream().mapToDouble(fd -> fd.getZuzNeut() != null ? fd.getZuzNeut() : 0).sum());
        summary.put("zuzSorb", fireDepartmentsPage.stream().mapToDouble(fd -> fd.getZuzSorb() != null ? fd.getZuzSorb() : 0).sum());

        return summary;
    }
}
