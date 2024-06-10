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

    @Column(name = "DATA_OSTATNIEJ_REJESTRACJI_W_KRAJU")
    private String dataOstatniejRejestracjiWKraju;

    @Column(name = "DATA_WYREJESTROWANIA_POJAZDU")
    private String dataWyrejestrowaniaPojazdu;

    @Column(name = "REJESTRACJA_WOJEWODZTWO")
    private String rejestracjaWojewodztwo;

    @Column(name = "REJESTRACJA_GMINA")
    private String rejestracjaGmina;

    @Column(name = "REJESTRACJA_POWIAT")
    private String rejestracjaPowiat;

    @Column(name = "MARKA")
    private String marka;

    @Column(name = "RODZAJ_PALIWA")
    private String rodzajPaliwa;

    @Column(name = "POJEMNOSC_SKOKOWA_SILNIKA")
    private Double pojemnoscSkokowaSilnika;

    @Column(name = "MOC_NETTO_SILNIKA")
    private Double mocNettoSilnika;

    @Column(name = "MASA_WLASNA")
    private Double masaWlasna;

    @Column(name = "DOPUSZCZALNA_MASA_CALKOWITA")
    private Double dopuszczalnaMasaCalkowita;

    @Column(name = "LICZBA_MIEJSC_SIEDZACYCH")
    private Integer liczbaMiejscSiedzacych;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "RODZAJ_POJAZDU")
    private String rodzajPojazdu;

}
