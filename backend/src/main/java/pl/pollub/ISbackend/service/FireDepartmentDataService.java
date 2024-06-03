package pl.pollub.ISbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentDataRepository;

import java.util.HashMap;
import java.util.List;
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

    public Map<String, Long> getCountByWojewodztwo() {
        List<Object[]> results = fireDepartmentDataRepository.countByWojewodztwo();
        Map<String, Long> countMap = new HashMap<>();
        for (Object[] result : results) {
            String wojewodztwo = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(wojewodztwo, count);
        }
        return countMap;
    }

    public Map<String, Double> getOperationTypePercentage() {
        long total = fireDepartmentDataRepository.totalCount();
        long rescueIncidentsCount = fireDepartmentDataRepository.countByOperationType("ratownicze");
        long nonRescueIncidentsCount = total - rescueIncidentsCount;

        Map<String, Double> percentages = new HashMap<>();
        percentages.put("Ratownicze", (double) rescueIncidentsCount / total * 100);
        percentages.put("Nieratownicze", (double) nonRescueIncidentsCount / total * 100);

        return percentages;
    }

}
