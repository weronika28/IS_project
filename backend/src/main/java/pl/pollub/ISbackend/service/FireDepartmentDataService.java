package pl.pollub.ISbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentDataRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public Map<String, Double> calculateAverageTimePerKilometer() {
        List<Object[]> results = fireDepartmentDataRepository.findAllForCalculation();
        Map<String, Double> totalTimes = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Object[] result : results) {
            Date dataZgl = (Date) result[0];
            Date dataDoj = (Date) result[1];
            String wojewodztwo = (String) result[2];
            Double kilom1 = (Double) result[3];

            if (dataZgl != null && dataDoj != null && kilom1 != null && kilom1 > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String godzinaDojazdu = sdf.format(dataDoj);

                Calendar cal = Calendar.getInstance();
                cal.setTime(dataDoj);
                cal.add(Calendar.MINUTE, -3);
                Date dataDojMinus3Min = cal.getTime();

                long diffInMillies = Math.abs(dataDojMinus3Min.getTime() - dataZgl.getTime());
                double diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
                double timePerKilometer = diffInMinutes / kilom1;

                totalTimes.put(wojewodztwo, totalTimes.getOrDefault(wojewodztwo, 0.0) + timePerKilometer);
                count.put(wojewodztwo, count.getOrDefault(wojewodztwo, 0) + 1);
            }
        }

        Map<String, Double> averageTimes = new HashMap<>();
        for (String wojewodztwo : totalTimes.keySet()) {
            averageTimes.put(wojewodztwo, totalTimes.get(wojewodztwo) / count.get(wojewodztwo));
        }

        return averageTimes;
    }

    public Map<String, Long> getCountByGmina() {
        List<Object[]> results = fireDepartmentDataRepository.countByGmina();
        Map<String, Long> countMap = new HashMap<>();
        for (Object[] result : results) {
            String gmina = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(gmina, count);
        }
        return countMap;
    }

    public Map<String, Double> calculateAverageTimePerKilometerByGmina() {
        List<Object[]> results = fireDepartmentDataRepository.findAllForGminaCalculation();
        Map<String, Double> totalTimes = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Object[] result : results) {
            Date dataZgl = (Date) result[0];
            Date dataDoj = (Date) result[1];
            String gmina = (String) result[2];
            Double kilom1 = (Double) result[3];

            if (dataZgl != null && dataDoj != null && kilom1 != null && kilom1 > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String godzinaDojazdu = sdf.format(dataDoj);

                Calendar cal = Calendar.getInstance();
                cal.setTime(dataDoj);
                cal.add(Calendar.MINUTE, -3);
                Date dataDojMinus3Min = cal.getTime();

                long diffInMillies = Math.abs(dataDojMinus3Min.getTime() - dataZgl.getTime());
                double diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
                double timePerKilometer = diffInMinutes / kilom1;

                totalTimes.put(gmina, totalTimes.getOrDefault(gmina, 0.0) + timePerKilometer);
                count.put(gmina, count.getOrDefault(gmina, 0) + 1);
            }
        }

        Map<String, Double> averageTimes = new HashMap<>();
        for (String gmina : totalTimes.keySet()) {
            averageTimes.put(gmina, totalTimes.get(gmina) / count.get(gmina));
        }

        return averageTimes;
    }

}
