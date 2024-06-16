package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentDataRepository;
import pl.pollub.ISbackend.repository.VehicleDataRepository;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class FireDepartmentDataService {

    @Autowired
    private FireDepartmentDataRepository fireDepartmentDataRepository;
    @Autowired
    private VehicleDataRepository vehicleDataRepository;

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

    public Map<String, Long> getCountVehiclesByWojewodztwo() {
        List<Object[]> results = vehicleDataRepository.countByWojewodztwo();
        Map<String, Long> countMap = new HashMap<>();
        for (Object[] result : results) {
            String wojewodztwo = (String) result[0];
            Long count = (Long) result[1];
            countMap.put(wojewodztwo.toLowerCase(), count);
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

//    public Map<String, Long> getCountVehiclesByGmina() {
//        List<Object[]> results = vehicleDataRepository.countByGmina();
//        Map<String, Long> countMap = new HashMap<>();
//        for (Object[] result : results) {
//            String gmina = (String) result[0];
//            Long count = (Long) result[1];
//            countMap.put(gmina, count);
//        }
//        return countMap;
//    }
public Map<String, Long> getCountVehiclesByGmina() {
    List<Object[]> results = vehicleDataRepository.countByGmina();
    Map<String, Long> countMap = new HashMap<>();
    for (Object[] result : results) {
        String gmina = (String) result[0];
        Long count = ((Number) result[1]).longValue(); // Convert to Long if necessary
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
                long diffInMillies = Math.abs(dataDoj.getTime() - dataZgl.getTime());
                double diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
                double timePerKilometer = diffInMinutes / kilom1;

                if (gmina != null) {
                    totalTimes.put(gmina.toLowerCase(), totalTimes.getOrDefault(gmina.toLowerCase(), 0.0) + timePerKilometer);
                    count.put(gmina.toLowerCase(), count.getOrDefault(gmina.toLowerCase(), 0) + 1);
                } else {
                    System.out.println("Null gmina encountered");
                }
            }
        }

        Map<String, Double> averageTimes = new HashMap<>();
        for (String gmina : totalTimes.keySet()) {
            averageTimes.put(gmina, totalTimes.get(gmina) / count.get(gmina));
        }

        // Logowanie wyników obliczeń
        averageTimes.forEach((gmina, avgTime) -> {
            System.out.println("Gmina: " + gmina + ", Average Time per Km: " + avgTime);
        });

        return averageTimes;
    }

    public byte[] exportToXml() {
        List<Object[]> results = fireDepartmentDataRepository.findAllForGminaCalculation();
        Map<String, Object> dataMap = processResults(results);

        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.writeValueAsBytes(dataMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert data to XML", e);
        }
    }

    public byte[] exportToJson() {
        List<Object[]> results = fireDepartmentDataRepository.findAllForGminaCalculation();
        Map<String, Object> dataMap = processResults(results);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsBytes(dataMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert data to JSON", e);
        }
    }

    private Map<String, Object> processResults(List<Object[]> results) {
        Map<String, Double> totalTimes = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        for (Object[] result : results) {
            Date dataZgl = (Date) result[0];
            Date dataDoj = (Date) result[1];
            String gmina = (String) result[2];
            Double kilom1 = (Double) result[3];

            if (dataZgl != null && dataDoj != null && kilom1 != null && kilom1 > 0) {
                long diffInMillies = Math.abs(dataDoj.getTime() - dataZgl.getTime());
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

        dataMap.put("totalTimes", totalTimes);
        dataMap.put("averageTimes", averageTimes);
        return dataMap;
    }




}