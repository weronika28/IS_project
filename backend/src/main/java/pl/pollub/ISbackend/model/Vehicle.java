package pl.pollub.ISbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_PIERWSZEJ_REJESTRACJI_W_KRAJU")
    private String dataPierwszejRejestracjiWKraju;

    @Column(name = "REJESTRACJA_WOJEWODZTWO")
    private String rejestracjaWojewodztwo;

    @Column(name = "REJESTRACJA_GMINA")
    private String rejestracjaGmina;

    @Column(name = "REJESTRACJA_POWIAT")
    private String rejestracjaPowiat;
}
