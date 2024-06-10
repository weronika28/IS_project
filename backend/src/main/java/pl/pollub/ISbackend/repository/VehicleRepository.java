package pl.pollub.ISbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pollub.ISbackend.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v.rejestracjaWojewodztwo, COUNT(v) FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo")
    List<Object[]> countByWojewodztwo();

    @Query("SELECT v.rejestracjaWojewodztwo, v.rejestracjaPowiat, COUNT(v) FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo, v.rejestracjaPowiat")
    List<Object[]> countByWojewodztwoAndPowiat();

    @Query("SELECT v.rodzajPaliwa, COUNT(v) FROM Vehicle v GROUP BY v.rodzajPaliwa")
    List<Object[]> countByFuelType();

    @Query("SELECT v.marka, COUNT(v) FROM Vehicle v GROUP BY v.marka")
    List<Object[]> countByBrand();

    @Query("SELECT v.dataPierwszejRejestracjiWKraju, COUNT(v) FROM Vehicle v GROUP BY v.dataPierwszejRejestracjiWKraju")
    List<Object[]> countByProductionYear();
}
