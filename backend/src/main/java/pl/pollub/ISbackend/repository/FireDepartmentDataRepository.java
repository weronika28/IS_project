package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pollub.ISbackend.model.FireDepartment;

import java.util.List;

@Repository
public interface FireDepartmentDataRepository extends JpaRepository<FireDepartment, String> {

    @Query("SELECT fd.wojewodztwo, COUNT(fd) FROM FireDepartment fd GROUP BY fd.wojewodztwo")
    List<Object[]> countByWojewodztwo();

    @Query("SELECT COUNT(fd) FROM FireDepartment fd WHERE fd.operationType = :operationType")
    long countByOperationType(String operationType);

    @Query("SELECT COUNT(fd) FROM FireDepartment fd")
    long totalCount();
}
