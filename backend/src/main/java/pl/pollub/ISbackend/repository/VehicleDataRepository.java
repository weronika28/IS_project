package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pollub.ISbackend.model.Vehicle;

import java.util.List;

public interface VehicleDataRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT DISTINCT v.rejestracjaWojewodztwo FROM Vehicle v")
    List<String> findAllVoivodeships();

    @Query(value = "SELECT COUNT(v) FROM Vehicle v WHERE v.rejestracjaWojewodztwo = :rejestracjaWojewodztwo")
    int countVehiclesByRejestracjaWojewodztwo(@Param("rejestracjaWojewodztwo") String rejestracjaWojewodztwo);
}
