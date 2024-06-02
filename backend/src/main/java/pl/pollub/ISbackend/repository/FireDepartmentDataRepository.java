package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.ISbackend.model.FireDepartment;

@Repository
public interface FireDepartmentDataRepository extends JpaRepository<FireDepartment, String> {
}
