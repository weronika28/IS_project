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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Zakładając, że pola są oddzielone przecinkami

                FireDepartment fireDepartment = new FireDepartment();
                fireDepartment.setIdMeldunek(data[0]);
                fireDepartment.setFBezJop(data[1]);
                fireDepartment.setOperationType(data[2]);
                fireDepartment.setRodzaj(data[3]);
                fireDepartment.setWlk(data[4]);
                fireDepartment.setFMzRodz10(data[5]);
                fireDepartment.setTeryt(Integer.parseInt(data[6]));
                fireDepartment.setWojewodztwo(data[7]);
                fireDepartment.setPowiat(data[8]);
                fireDepartment.setGmina(data[9]);
                fireDepartment.setLocRoadNumber(Integer.parseInt(data[10]));
                fireDepartment.setLocRoadChainage(Integer.parseInt(data[11]));
                fireDepartment.setDataZau(new Date(Long.parseLong(data[12])));
                fireDepartment.setDataLok(new Date(Long.parseLong(data[13])));
                fireDepartment.setDataUsu(new Date(Long.parseLong(data[14])));
                fireDepartment.setKilom1(Integer.parseInt(data[15]));
                fireDepartment.setDataZgl(new Date(Long.parseLong(data[16])));
                fireDepartment.setDataDoj(new Date(Long.parseLong(data[17])));
                fireDepartment.setDataPow(new Date(Long.parseLong(data[18])));
                fireDepartment.setSumCzas(data[19]);
                fireDepartment.setIlPWod(Integer.parseInt(data[20]));
                fireDepartment.setIlPProsz(Integer.parseInt(data[21]));
                fireDepartment.setIlPPian(Integer.parseInt(data[22]));
                fireDepartment.setIlPPianc(Integer.parseInt(data[23]));
                fireDepartment.setIlPPians(Integer.parseInt(data[24]));
                fireDepartment.setIlPPianl(Integer.parseInt(data[25]));
                fireDepartment.setZuzWody(Integer.parseInt(data[26]));
                fireDepartment.setZuzProszku(Integer.parseInt(data[27]));
                fireDepartment.setZuzPiany(Integer.parseInt(data[28]));
                fireDepartment.setZuzNeut(Integer.parseInt(data[29]));
                fireDepartment.setZuzSorb(Integer.parseInt(data[30]));
                fireDepartment.setWypPspS(Integer.parseInt(data[31]));
                fireDepartment.setZl(Integer.parseInt(data[32]));
                fireDepartment.setIl1(Integer.parseInt(data[33]));
                fireDepartment.setIl2(Integer.parseInt(data[34]));
                fireDepartment.setIl3(Integer.parseInt(data[35]));

                fireDepartments.add(fireDepartment);
            }
        }
        this.fireDepartmentRepository.saveAll(fireDepartments);
    }
}

