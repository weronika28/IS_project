package pl.pollub.ISbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.ISbackend.model.FireDepartment;
import pl.pollub.ISbackend.repository.FireDepartmentRepository;

import java.io.File;
import java.io.IOException;
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
        List<FireDepartment> fireDepartments = mapper.readValue(new File(filePath), new TypeReference<List<FireDepartment>>(){});
        fireDepartmentRepository.saveAll(fireDepartments);
    }

    public void exportToXml(String filePath) throws IOException {
        List<FireDepartment> fireDepartments = fireDepartmentRepository.findAll();
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(filePath), fireDepartments);
    }

    public void importFromXml(String filePath) throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<FireDepartment> fireDepartments = mapper.readValue(new File(filePath), new TypeReference<List<FireDepartment>>(){});
        fireDepartmentRepository.saveAll(fireDepartments);
    }
}
