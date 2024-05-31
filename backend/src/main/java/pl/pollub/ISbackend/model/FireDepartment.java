package pl.pollub.ISbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Fire_Department")
@Data

public class FireDepartment {
        @Id
        @Column(name = "ID_MELDUNEK")
        private String idMeldunek;

        @Column(name = "F_BEZ_JOP")
        private String fBezJop;

        @Column(name = "OPERATION_TYPE")
        private String operationType;

        @Column(name = "RODZAJ")
        private String rodzaj;

        @Column(name = "WLK")
        private String wlk;

        @Column(name = "F_MZ_RODZ_10")
        private String fMzRodz10;

        @Column(name = "TERYT")
        private Double teryt;

        @Column(name = "WOJEWODZTWO")
        private String wojewodztwo;

        @Column(name = "POWIAT")
        private String powiat;

        @Column(name = "GMINA")
        private String gmina;

        @Column(name = "LOC_ROAD_NUMBER")
        private String locRoadNumber;

        @Column(name = "LOC_ROAD_CHAINAGE")
        private String locRoadChainage;

        @Column(name = "DATA_ZAU")
        private Date dataZau;

        @Column(name = "DATA_LOK")
        private Date dataLok;

        @Column(name = "DATA_USU")
        private Date dataUsu;

        @Column(name = "KILOM_1")
        private Double kilom1;

        @Column(name = "DATA_ZGL")
        private Date dataZgl;

        @Column(name = "DATA_DOJ")
        private Date dataDoj;

        @Column(name = "DATA_POW")
        private Date dataPow;

        @Column(name = "SUM_CZAS")
        private String sumCzas;

        @Column(name = "IL_P_WOD")
        private Double ilPWod;

        @Column(name = "IL_P_PROSZ")
        private Double ilPProsz;

        @Column(name = "IL_P_PIAN")
        private Double ilPPian;

        @Column(name = "IL_P_PIANC")
        private Double ilPPianc;

        @Column(name = "IL_P_PIANS")
        private Double ilPPians;

        @Column(name = "IL_P_PIANL")
        private Double ilPPianl;

        @Column(name = "ZUZ_WODY")
        private Double zuzWody;

        @Column(name = "ZUZ_PROSZKU")
        private Double zuzProszku;

        @Column(name = "ZUZ_PIANY")
        private Double zuzPiany;

        @Column(name = "ZUZ_NEUT")
        private Double zuzNeut;

        @Column(name = "ZUZ_SORB")
        private Double zuzSorb;

        @Column(name = "WYP_PSP_S")
        private Double wypPspS;

        @Column(name = "ZL")
        private Double zl;

        @Column(name = "IL1")
        private Double il1;

        @Column(name = "IL2")
        private Double il2;

        @Column(name = "IL3")
        private Double il3;
    }
