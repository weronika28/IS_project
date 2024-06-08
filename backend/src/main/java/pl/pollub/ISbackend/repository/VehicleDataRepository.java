package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.ISbackend.model.Vehicle;

@Repository
public interface VehicleDataRepository extends JpaRepository<Vehicle, Long> {
}
