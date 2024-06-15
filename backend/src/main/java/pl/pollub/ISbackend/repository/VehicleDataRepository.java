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

    @Query(value = "SELECT COUNT(v) FROM Vehicle v WHERE v.rejestracjaGmina = :rejestracjaGmina")
    int countVehiclesByRejestracjaGmina(@Param("rejestracjaGmina") String rejestracjaGmina);

    @Query("SELECT v.rejestracjaWojewodztwo, COUNT(v) FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo")
    List<Object[]> countByWojewodztwo();
    @Query("SELECT v.rejestracjaGmina, COUNT(v) FROM Vehicle v GROUP BY v.rejestracjaGmina")
    List<Object[]> countByGmina();

    @Query("SELECT v.rejestracjaWojewodztwo, v.marka, COUNT(v) as cnt FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo, v.marka ORDER BY cnt DESC")
    List<Object[]> mostPopularBrandByWojewodztwo();

    @Query("SELECT v.rejestracjaWojewodztwo, v.rodzajPaliwa, COUNT(v) as cnt FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo, v.rodzajPaliwa ORDER BY cnt DESC")
    List<Object[]> mostPopularFuelTypeByWojewodztwo();

    @Query("SELECT v.rejestracjaWojewodztwo, AVG(v.pojemnoscSkokowaSilnika) FROM Vehicle v GROUP BY v.rejestracjaWojewodztwo")
    List<Object[]> averageEngineCapacityByWojewodztwo();

    @Query("SELECT v.marka, COUNT(v) FROM Vehicle v WHERE v.rejestracjaWojewodztwo = :rejestracjaWojewodztwo GROUP BY v.marka ORDER BY COUNT(v) DESC")
    List<Object[]> findMostPopularBrandByVoivodeship(@Param("rejestracjaWojewodztwo") String rejestracjaWojewodztwo);

    @Query("SELECT v.rodzajPaliwa, COUNT(v) FROM Vehicle v WHERE v.rejestracjaWojewodztwo = :rejestracjaWojewodztwo GROUP BY v.rodzajPaliwa ORDER BY COUNT(v) DESC")
    List<Object[]> findMostPopularFuelTypeByVoivodeship(@Param("rejestracjaWojewodztwo") String rejestracjaWojewodztwo);

    @Query("SELECT AVG(v.pojemnoscSkokowaSilnika) FROM Vehicle v WHERE v.rejestracjaWojewodztwo = :rejestracjaWojewodztwo")
    double findAverageEngineCapacityByVoivodeship(@Param("rejestracjaWojewodztwo") String rejestracjaWojewodztwo);

}
