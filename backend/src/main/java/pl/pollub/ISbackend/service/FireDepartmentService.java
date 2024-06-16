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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private String getValueOrNull(String value) {
        return value.isEmpty() ? null : value;
    }

    private Integer getIntegerValueOrNull(String value) {
        return value.isEmpty() ? null : Integer.parseInt(value);
    }

    private Double getDoubleValueOrNull(String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            if (value.contains(",")) {
                return Double.parseDouble(value.replace(",", "."));
            } else {
                return Double.parseDouble(value);
            }
        }
    }



    private Date getDateOrNull(String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }



    public void importFromCsv(MultipartFile file) throws IOException {
        List<FireDepartment> fireDepartments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int lineNumber = 1;

            List<String> selectedColumnNames = splitLine(reader.readLine());
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    List<String> data = splitLine(line);
                    // System.out.println("Data after split: " + data);

                    FireDepartment fireDepartment = new FireDepartment();
                    for (int i = 0; i < data.size(); i++) {
                        String columnName = selectedColumnNames.get(i);
                        switch (columnName) {
                            case "ID_MELDUNEK":
                                String idMeldunek = data.get(i);
                                if(idMeldunek.length() > 50) {
                                    throw new Exception("ID_MELDUNEK: "+idMeldunek+" is too long for data: "+data);
                                }
                                fireDepartment.setIdMeldunek(idMeldunek);
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
                                fireDepartment.setTeryt(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "WOJEWODZTWO": {
                                fireDepartment.setWojewodztwo(data.get(i));
                                break;
                            }
                            case "POWIAT":
                                fireDepartment.setPowiat(data.get(i));
                                break;
                            case "GMINA":
                                fireDepartment.setGmina(data.get(i));
                                break;
                            case "LOC_ROAD_NUMBER":
                                fireDepartment.setLocRoadNumber(data.get(i));
                                break;
                            case "LOC_ROAD_CHAINAGE":
                                fireDepartment.setLocRoadChainage(data.get(i));
                                break;
                            case "DATA_ZAU":
                                fireDepartment.setDataZau(getDateOrNull(data.get(i)));
                                break;
                            case "DATA_LOK":
                                fireDepartment.setDataLok(getDateOrNull(data.get(i)));
                                break;
                            case "DATA_USU":
                                fireDepartment.setDataUsu(getDateOrNull(data.get(i)));
                                break;
                            case "KILOM_1":
                                fireDepartment.setKilom1(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "DATA_ZGL":
                                fireDepartment.setDataZgl(getDateOrNull(data.get(i)));
                                break;
                            case "DATA_DOJ":
                                fireDepartment.setDataDoj(getDateOrNull(data.get(i)));
                                break;
                            case "DATA_POW":
                                fireDepartment.setDataPow(getDateOrNull(data.get(i)));
                                break;
                            case "SUM_CZAS":
                                fireDepartment.setSumCzas(data.get(i));
                                break;
                            case "IL_P_WOD":
                                fireDepartment.setIlPWod(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL_P_PROSZ":
                                fireDepartment.setIlPProsz(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL_P_PIAN":
                                fireDepartment.setIlPPian(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL_P_PIANC":
                                fireDepartment.setIlPPianc(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL_P_PIANS":
                                fireDepartment.setIlPPians(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL_P_PIANL":
                                fireDepartment.setIlPPianl(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZUZ_WODY":
                                fireDepartment.setZuzWody(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZUZ_PROSZKU":
                                fireDepartment.setZuzProszku(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZUZ_PIANY":
                                fireDepartment.setZuzPiany(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZUZ_NEUT":
                                fireDepartment.setZuzNeut(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZUZ_SORB":
                                fireDepartment.setZuzSorb(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "WYP_PSP_S":
                                fireDepartment.setWypPspS(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "ZL":
                                fireDepartment.setZl(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL1":
                                fireDepartment.setIl1(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL2":
                                fireDepartment.setIl2(getDoubleValueOrNull(data.get(i)));
                                break;
                            case "IL3":
                                fireDepartment.setIl3(getDoubleValueOrNull(data.get(i)));
                                break;
                            default:
                                break;
                        }
                    }

                    fireDepartments.add(fireDepartment);
                } catch (Exception e) {
                    System.err.println("Error processing line " + lineNumber + ": " + line);
                    e.printStackTrace();
                    return;
                }
            }
        } catch (IOException e) {
            //System.err.println("Error reading file: " + e.getMessage());
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
            } else if (c == ';' && !inQuotes) {
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