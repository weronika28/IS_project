package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class FireDepartmentService {

    @Autowired
    private FireDepartmentRepository fireDepartmentRepository;

    public List<FireDepartment> findAll() {
        return fireDepartmentRepository.findAll();
    }

    public Optional<FireDepartment> findById(String id) {
        return fireDepartmentRepository.findById(id);
    }

    public FireDepartment save(FireDepartment fireDepartment) {
        return fireDepartmentRepository.save(fireDepartment);
    }

    public void deleteById(String id) {
        fireDepartmentRepository.deleteById(id);
    }

    public void exportToJson(String filePath) throws IOException {
        List<FireDepartment> fireDepartments = fireDepartmentRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), fireDepartments);
    }

    public void importFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<FireDepartment> fireDepartments = mapper.readValue(new File(filePath), new TypeReference<>() {
        });
        fireDepartmentRepository.saveAll(fireDepartments);
    }

    public void exportToXml(String filePath) throws IOException {
        List<FireDepartment> fireDepartments = fireDepartmentRepository.findAll();
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(filePath), fireDepartments);
    }

    public void importFromXml(String filePath) throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<FireDepartment> fireDepartments = mapper.readValue(new File(filePath), new TypeReference<>() {
        });
        fireDepartmentRepository.saveAll(fireDepartments);
    }


    public void importFromCsv(MultipartFile file) throws IOException {
        List<FireDepartment> fireDepartments = new ArrayList<>();
        List<String> selectedColumnNames = Arrays.asList("ID_MELDUNEK", "F_BEZ_JOP", "OPERATION_TYPE", "RODZAJ", "WLK", "F_MZ_RODZ_10", "TERYT", "WOJEWODZTWO", "POWIAT", "GMINA", "LOC_ROAD_NUMBER", "LOC_ROAD_CHAINAGE", "DATA_ZAU", "DATA_LOK", "DATA_USU", "KILOM_1", "DATA_ZGL", "DATA_DOJ", "DATA_POW", "SUM_CZAS", "IL_P_WOD", "IL_P_PROSZ", "IL_P_PIAN", "IL_P_PIANC", "IL_P_PIANS", "IL_P_PIANL", "ZUZ_WODY", "ZUZ_PROSZKU", "ZUZ_PIANY", "ZUZ_NEUT", "ZUZ_SORB", "WYP_PSP_S", "ZL", "IL1", "IL2", "IL3");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    List<String> data = splitLine(line);
                    System.out.println("Data after split: " + data);


                    FireDepartment fireDepartment = new FireDepartment();
                    for (int i = 0; i < data.size(); i++) {
                        String columnName = selectedColumnNames.get(i);
                        switch (columnName) {
                            case "ID_MELDUNEK":
                                fireDepartment.setIdMeldunek(data.get(i));
                                break;
                            case "F_BEZ_JOP":
                                fireDepartment.setFBezJop(data.get(i));
                                break;
                            case "OPERATION_TYPE":
                                fireDepartment.setOperationType(data.get(i));
                                break;
                            case "RODZAJ":
                                fireDepartment.setRodzaj(data.get(i));
                                break;
                            case "WLK":
                                fireDepartment.setWlk(data.get(i));
                                break;
                            case "F_MZ_RODZ_10":
                                fireDepartment.setFMzRodz10(data.get(i));
                                break;
                            case "TERYT":
                                fireDepartment.setTeryt(Integer.parseInt(data.get(i)));
                                break;
                            case "WOJEWODZTWO":
                                fireDepartment.setWojewodztwo(data.get(i));
                                break;
                            case "POWIAT":
                                fireDepartment.setPowiat(data.get(i));
                                break;
                            case "GMINA":
                                fireDepartment.setGmina(data.get(i));
                                break;
                            case "LOC_ROAD_NUMBER":
                                fireDepartment.setLocRoadNumber(Integer.parseInt(data.get(i)));
                                break;
                            case "LOC_ROAD_CHAINAGE":
                                fireDepartment.setLocRoadChainage(Integer.parseInt(data.get(i)));
                                break;
                            case "DATA_ZAU":
                                fireDepartment.setDataZau(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "DATA_LOK":
                                fireDepartment.setDataLok(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "DATA_USU":
                                fireDepartment.setDataUsu(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "KILOM_1":
                                fireDepartment.setKilom1(Integer.parseInt(data.get(i)));
                                break;
                            case "DATA_ZGL":
                                fireDepartment.setDataZgl(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "DATA_DOJ":
                                fireDepartment.setDataDoj(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "DATA_POW":
                                fireDepartment.setDataPow(new Date(Long.parseLong(data.get(i))));
                                break;
                            case "SUM_CZAS":
                                fireDepartment.setSumCzas(data.get(i));
                                break;
                            case "IL_P_WOD":
                                fireDepartment.setIlPWod(Integer.parseInt(data.get(i)));
                                break;
                            case "IL_P_PROSZ":
                                fireDepartment.setIlPProsz(Integer.parseInt(data.get(i)));
                                break;
                            case "IL_P_PIAN":
                                fireDepartment.setIlPPian(Integer.parseInt(data.get(i)));
                                break;
                            case "IL_P_PIANC":
                                fireDepartment.setIlPPianc(Integer.parseInt(data.get(i)));
                                break;
                            case "IL_P_PIANS":
                                fireDepartment.setIlPPians(Integer.parseInt(data.get(i)));
                                break;
                            case "IL_P_PIANL":
                                fireDepartment.setIlPPianl(Integer.parseInt(data.get(i)));
                                break;
                            case "ZUZ_WODY":
                                fireDepartment.setZuzWody(Integer.parseInt(data.get(i)));
                                break;
                            case "ZUZ_PROSZKU":
                                fireDepartment.setZuzProszku(Integer.parseInt(data.get(i)));
                                break;
                            case "ZUZ_PIANY":
                                fireDepartment.setZuzPiany(Integer.parseInt(data.get(i)));
                                break;
                            case "ZUZ_NEUT":
                                fireDepartment.setZuzNeut(Integer.parseInt(data.get(i)));
                                break;
                            case "ZUZ_SORB":
                                fireDepartment.setZuzSorb(Integer.parseInt(data.get(i)));
                                break;
                            case "WYP_PSP_S":
                                fireDepartment.setWypPspS(Integer.parseInt(data.get(i)));
                                break;
                            case "ZL":
                                fireDepartment.setZl(Integer.parseInt(data.get(i)));
                                break;
                            case "IL1":
                                fireDepartment.setIl1(Integer.parseInt(data.get(i)));
                                break;
                            case "IL2":
                                fireDepartment.setIl2(Integer.parseInt(data.get(i)));
                                break;
                            case "IL3":
                                fireDepartment.setIl3(Integer.parseInt(data.get(i)));
                                break;
                            default:
                                // Obsługa nieznanej kolumny
                                System.err.println("Unknown column name: " + columnName);
                        }
                    }

                    fireDepartments.add(fireDepartment);
                } catch (Exception e) {
                    System.err.println("Error processing line " + lineNumber + ": " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            throw e;
        }

        System.out.println("Liczba wierszy do zapisania: " + fireDepartments.size());

        fireDepartmentRepository.saveAll(fireDepartments);
        System.out.println("Zapis danych do bazy danych zakończony.");

    }


    private List<String> splitLine(String line) {
        List<String> values = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            values.add(sb.toString());
        }
        return values;
    }
}