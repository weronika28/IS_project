package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.ISbackend.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
