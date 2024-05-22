package pl.pollub.ISbackend.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @Column(name = "F_POZ_LAS_1")
    private String fPozLas1;

    @Column(name = "F_POZ_LAS_2")
    private String fPozLas2;

    @Column(name = "F_POZ_LAS_3")
    private String fPozLas3;

    @Column(name = "F_POZ_LAS_4")
    private String fPozLas4;

    @Column(name = "F_MZ_RODZ_1")
    private String fMzRodz1;

    @Column(name = "F_MZ_RODZ_2")
    private String fMzRodz2;

    @Column(name = "F_MZ_RODZ_3")
    private String fMzRodz3;

    @Column(name = "F_MZ_RODZ_4")
    private String fMzRodz4;

    @Column(name = "F_MZ_RODZ_5")
    private String fMzRodz5;

    @Column(name = "F_MZ_RODZ_6")
    private String fMzRodz6;

    @Column(name = "F_MZ_RODZ_7")
    private String fMzRodz7;

    @Column(name = "F_MZ_RODZ_8")
    private String fMzRodz8;

    @Column(name = "F_MZ_RODZ_9")
    private String fMzRodz9;

    @Column(name = "F_MZ_RODZ_10")
    private String fMzRodz10;

    @Column(name = "F_MZ_RODZ_11")
    private String fMzRodz11;

    @Column(name = "F_MZ_RODZ_12")
    private String fMzRodz12;

    @Column(name = "F_MZ_RODZ_13")
    private String fMzRodz13;

    @Column(name = "F_MZ_RODZ_14")
    private String fMzRodz14;

    @Column(name = "TERYT")
    private Integer teryt;

    @Column(name = "WOJEWODZTWO")
    private String wojewodztwo;

    @Column(name = "POWIAT")
    private String powiat;

    @Column(name = "GMINA")
    private String gmina;

    @Column(name = "LOC_ROAD_NUMBER")
    private Integer locRoadNumber;

    @Column(name = "LOC_ROAD_CHAINAGE")
    private Integer locRoadChainage;

    @Column(name = "OBIEKT_KOD_1")
    private Integer obiektKod1;

    @Column(name = "OBIEKT_OPIS_1")
    private String obiektOpis1;

    @Column(name = "OBIEKT_KOD_2")
    private Integer obiektKod2;

    @Column(name = "OBIEKT_OPIS_2")
    private String obiektOpis2;

    @Column(name = "WLASCICIEL_KOD_1")
    private Integer wlascicielKod1;

    @Column(name = "WLASCICIEL_OPIS_1")
    private String wlascicielOpis1;

    @Column(name = "WLASCICIEL_KOD_2")
    private Integer wlascicielKod2;

    @Column(name = "WLASCICIEL_OPIS_2")
    private String wlascicielOpis2;

    @Column(name = "DATA_ZAU")
    private Date dataZau;

    @Column(name = "DATA_LOK")
    private Date dataLok;

    @Column(name = "DATA_USU")
    private Date dataUsu;

    @Column(name = "KILOM_1")
    private Integer kilom1;

    @Column(name = "DATA_ZGL")
    private Date dataZgl;

    @Column(name = "DATA_DOJ")
    private Date dataDoj;

    @Column(name = "DATA_POW")
    private Date dataPow;

    @Column(name = "SUM_CZAS")
    private String sumCzas;

    @Column(name = "F_RODZ_ZAUW_1")
    private String fRodzZauw1;

    @Column(name = "F_RODZ_ZAUW_2")
    private String fRodzZauw2;

    @Column(name = "F_RODZ_ZAUW_3")
    private String fRodzZauw3;

    @Column(name = "F_RODZ_ZAUW_4")
    private String fRodzZauw4;

    @Column(name = "F_RODZ_ZAUW_5")
    private String fRodzZauw5;

    @Column(name = "F_RODZ_ZGL_1")
    private String fRodzZgl1;

    @Column(name = "F_RODZ_ZGL_2")
    private String fRodzZgl2;

    @Column(name = "F_RODZ_ZGL_3")
    private String fRodzZgl3;

    @Column(name = "F_RODZ_ZGL_4")
    private String fRodzZgl4;

    @Column(name = "JRG_S")
    private Integer jrgS;

    @Column(name = "JRG_LUDZ")
    private Integer jrgLudz;

    @Column(name = "OSPK_S")
    private Integer ospkS;

    @Column(name = "OSPK_LUDZ")
    private Integer ospkLudz;

    @Column(name = "OSP_S")
    private Integer ospS;

    @Column(name = "OSP_LUDZ")
    private Integer ospLudz;

    @Column(name = "GSP_S")
    private Integer gspS;

    @Column(name = "GSP_LUDZ")
    private Integer gspLudz;

    @Column(name = "WOP_S")
    private Integer wopS;

    @Column(name = "WOP_LUDZ")
    private Integer wopLudz;

    @Column(name = "ZSP_S")
    private Integer zspS;

    @Column(name = "ZSP_LUDZ")
    private Integer zspLudz;

    @Column(name = "ZSR_S")
    private Integer zsrS;

    @Column(name = "ZSR_LUDZ")
    private Integer zsrLudz;

    @Column(name = "INNE_S")
    private Integer inneS;

    @Column(name = "INNE_LUDZ")
    private Integer inneLudz;

    @Column(name = "GS_CHEM_EKO_S")
    private Integer gsChemEkoS;

    @Column(name = "GS_CHEM_EKO_LUDZ")
    private Integer gsChemEkoLudz;

    @Column(name = "GS_WYS_S")
    private Integer gsWysS;

    @Column(name = "GS_WYS_LUDZ")
    private Integer gsWysLudz;

    @Column(name = "GS_POSZ_S")
    private Integer gsPoszS;

    @Column(name = "GS_POSZ_LUDZ")
    private Integer gsPoszLudz;

    @Column(name = "GS_WOD_NUR_S")
    private Integer gsWodNurS;

    @Column(name = "GS_WOD_NUR_LUDZ")
    private Integer gsWodNurLudz;

    @Column(name = "GS_INNE_S")
    private Integer gsInneS;

    @Column(name = "GS_INNE_LUDZ")
    private Integer gsInneLudz;

    @Column(name = "GO_KW_S")
    private Integer goKwS;

    @Column(name = "GO_KW_LUDZ")
    private Integer goKwLudz;

    @Column(name = "GO_KP_S")
    private Integer goKpS;

    @Column(name = "GO_KP_LUDZ")
    private Integer goKpLudz;

    @Column(name = "PR_SP")
    private Integer prSp;

    @Column(name = "PR_LUDZ")
    private Integer prLudz;

    @Column(name = "PE_SP")
    private Integer peSp;

    @Column(name = "PE_LUDZ")
    private Integer peLudz;

    @Column(name = "PG_SP")
    private Integer pgSp;

    @Column(name = "PG_LUDZ")
    private Integer pgLudz;

    @Column(name = "SL_SP")
    private Integer slSp;

    @Column(name = "SL_LUDZ")
    private Integer slLudz;

    @Column(name = "WOJ_SP")
    private Integer wojSp;

    @Column(name = "WOJ_LUDZ")
    private Integer wojLudz;

    @Column(name = "PP_SP")
    private Integer ppSp;

    @Column(name = "PP_LUDZ")
    private Integer ppLudz;

    @Column(name = "SM_SP")
    private Integer smSp;

    @Column(name = "SM_LUDZ")
    private Integer smLudz;

    @Column(name = "OS_SP")
    private Integer osSp;

    @Column(name = "OS_LUDZ")
    private Integer osLudz;

    @Column(name = "WOPR_SP")
    private Integer woprSp;

    @Column(name = "WOPR_LUDZ")
    private Integer woprLudz;

    @Column(name = "GOPR_SP")
    private Integer goprSp;

    @Column(name = "GOPR_LUDZ")
    private Integer goprLudz;

    @Column(name = "IN_SP")
    private Integer inSp;

    @Column(name = "IN_LUDZ")
    private Integer inLudz;

    @Column(name = "SG_L")
    private Integer sgL;

    @Column(name = "SG_S")
    private Integer sgS;

    @Column(name = "SG_C")
    private Integer sgC;

    @Column(name = "SG_P")
    private Integer sgP;

    @Column(name = "SG_IN")
    private Integer sgIn;

    @Column(name = "SS_SD")
    private Integer ssSd;

    @Column(name = "SS_SH")
    private Integer ssSh;

    @Column(name = "SS_SW")
    private Integer ssSw;

    @Column(name = "SS_SRD")
    private Integer ssSrd;

    @Column(name = "SS_SRT")
    private Integer ssSrt;

    @Column(name = "SS_SPGAZ")
    private Integer ssSpgaz;

    @Column(name = "SS_SRW")
    private Integer ssSrw;

    @Column(name = "SS_SRCHEM")
    private Integer ssSrchem;

    @Column(name = "SS_ON")
    private Integer ssOn;

    @Column(name = "SS_SDL")
    private Integer ssSdl;

    @Column(name = "SS_SOP")
    private Integer ssSop;

    @Column(name = "SS_SRMED")
    private Integer ssSrmed;

    @Column(name = "SS_IN")
    private Integer ssIn;

    @Column(name = "SS_PLYW")
    private Integer ssPlyw;

    @Column(name = "SS_LOT")
    private Integer ssLot;

    @Column(name = "SS_ZRZUT")
    private Integer ssZrzut;

    @Column(name = "L_MOTORY")
    private Integer lMotory;

    @Column(name = "L_CYS_W")
    private Integer lCysW;

    @Column(name = "L_CYS_P")
    private Integer lCysP;

    @Column(name = "L_AUTOB")
    private Integer lAutob;

    @Column(name = "L_KARETKI")
    private Integer lKaretki;

    @Column(name = "L_RADIOWOZY")
    private Integer lRadiowozy;

    @Column(name = "L_S_CIEZ")
    private Integer lSCiez;

    @Column(name = "L_S_OSOB")
    private Integer lSOsob;

    @Column(name = "L_SP_KOP")
    private Integer lSpKop;

    @Column(name = "L_DZWIG")
    private Integer lDzwig;

    @Column(name = "L_PLYW")
    private Integer lPlyw;

    @Column(name = "L_INNE")
    private Integer lInne;

    @Column(name = "F_RODZ_DZ_LO_1")
    private String fRodzDzLo1;

    @Column(name = "F_RODZ_DZ_LO_2")
    private String fRodzDzLo2;

    @Column(name = "F_RODZ_DZ_LO_3")
    private String fRodzDzLo3;

    @Column(name = "F_RODZ_DZ_LO_4")
    private String fRodzDzLo4;

    @Column(name = "F_RODZ_DZ_LO_5")
    private String fRodzDzLo5;

    @Column(name = "F_RODZ_DZ_LO_6")
    private String fRodzDzLo6;

    @Column(name = "F_RODZ_DZ_LO_7")
    private String fRodzDzLo7;

    @Column(name = "F_RODZ_DZ_LO_8")
    private String fRodzDzLo8;

    @Column(name = "F_RODZ_DZ_LO_9")
    private String fRodzDzLo9;

    @Column(name = "F_RODZ_DZ_LO_10")
    private String fRodzDzLo10;

    @Column(name = "F_RODZ_DZ_LO_11")
    private String fRodzDzLo11;

    @Column(name = "F_RODZ_DZ_LO_12")
    private String fRodzDzLo12;

    @Column(name = "F_RODZ_DZ_LO_13")
    private String fRodzDzLo13;

    @Column(name = "F_RODZ_DZ_LO_14")
    private String fRodzDzLo14;

    @Column(name = "F_RODZ_DZ_LO_15")
    private String fRodzDzLo15;

    @Column(name = "F_RODZ_DZ_LO_16")
    private String fRodzDzLo16;

    @Column(name = "F_RODZ_DZ_LO_17")
    private String fRodzDzLo17;

    @Column(name = "F_RODZ_DZ_LO_18")
    private String fRodzDzLo18;

    @Column(name = "F_RODZ_DZ_LO_19")
    private String fRodzDzLo19;

    @Column(name = "F_RODZ_DZ_LO_20")
    private String fRodzDzLo20;

    @Column(name = "F_RODZ_DZ_LO_21")
    private String fRodzDzLo21;

    @Column(name = "F_RODZ_DZ_LO_22")
    private String fRodzDzLo22;

    @Column(name = "F_RODZ_DZ_LO_23")
    private String fRodzDzLo23;

    @Column(name = "F_RODZ_DZ_LO_24")
    private String fRodzDzLo24;

    @Column(name = "F_RODZ_DZ_LO_25")
    private String fRodzDzLo25;

    @Column(name = "F_RODZ_DZ_LO_26")
    private String fRodzDzLo26;

    @Column(name = "F_RODZ_DZ_LO_27")
    private String fRodzDzLo27;

    @Column(name = "F_RODZ_DZ_LO_28")
    private String fRodzDzLo28;

    @Column(name = "F_RODZ_DZ_LO_29")
    private String fRodzDzLo29;

    @Column(name = "F_RODZ_DZ_LO_30")
    private String fRodzDzLo30;

    @Column(name = "F_RODZ_DZ_LO_31")
    private String fRodzDzLo31;

    @Column(name = "F_RODZ_DZ_LO_32")
    private String fRodzDzLo32;

    @Column(name = "F_RODZ_DZ_HI_1")
    private String fRodzDzHi1;

    @Column(name = "F_RODZ_DZ_HI_2")
    private String fRodzDzHi2;

    @Column(name = "F_RODZ_DZ_HI_3")
    private String fRodzDzHi3;

    @Column(name = "F_RODZ_DZ_HI_4")
    private String fRodzDzHi4;

    @Column(name = "F_RODZ_DZ_HI_5")
    private String fRodzDzHi5;

    @Column(name = "F_RODZ_DZ_HI_6")
    private String fRodzDzHi6;

    @Column(name = "F_RODZ_DZ_HI_7")
    private String fRodzDzHi7;

    @Column(name = "F_RODZ_DZ_HI_8")
    private String fRodzDzHi8;

    @Column(name = "F_RODZ_DZ_HI_9")
    private String fRodzDzHi9;

    @Column(name = "F_RODZ_DZ_HI_10")
    private String fRodzDzHi10;

    @Column(name = "F_RODZ_DZ_HI_11")
    private String fRodzDzHi11;

    @Column(name = "F_RODZ_DZ_HI_12")
    private String fRodzDzHi12;

    @Column(name = "F_UZYT_SP_1")
    private String fUzytSp1;

    @Column(name = "F_UZYT_SP_2")
    private String fUzytSp2;

    @Column(name = "F_UZYT_SP_3")
    private String fUzytSp3;

    @Column(name = "F_UZYT_SP_4")
    private String fUzytSp4;

    @Column(name = "F_UZYT_SP_5")
    private String fUzytSp5;

    @Column(name = "F_UZYT_SP_6")
    private String fUzytSp6;

    @Column(name = "F_UZYT_SP_7")
    private String fUzytSp7;

    @Column(name = "F_UZYT_SP_8")
    private String fUzytSp8;

    @Column(name = "F_UZYT_SP_9")
    private String fUzytSp9;

    @Column(name = "F_UZYT_SP_10")
    private String fUzytSp10;

    @Column(name = "F_UZYT_SP_11")
    private String fUzytSp11;

    @Column(name = "F_UZYT_SP_12")
    private String fUzytSp12;

    @Column(name = "F_UZYT_SP_13")
    private String fUzytSp13;

    @Column(name = "F_UZYT_SP_14")
    private String fUzytSp14;

    @Column(name = "F_UZYT_SP_15")
    private String fUzytSp15;

    @Column(name = "F_UZYT_SP_16")
    private String fUzytSp16;

    @Column(name = "F_UZYT_SP_17")
    private String fUzytSp17;

    @Column(name = "F_UZYT_SP_18")
    private String fUzytSp18;

    @Column(name = "F_UZYT_SP_19")
    private String fUzytSp19;

    @Column(name = "F_UZYT_SP_20")
    private String fUzytSp20;

    @Column(name = "F_UZYT_SP_21")
    private String fUzytSp21;

    @Column(name = "F_UZYT_SP_22")
    private String fUzytSp22;

    @Column(name = "F_UZYT_SP_23")
    private String fUzytSp23;

    @Column(name = "F_UZYT_SP_24")
    private String fUzytSp24;

    @Column(name = "F_UZYT_SP_25")
    private String fUzytSp25;

    @Column(name = "F_UZYT_SP_26")
    private String fUzytSp26;

    @Column(name = "F_UZYT_SP_27")
    private String fUzytSp27;

    @Column(name = "F_UZYT_SP_28")
    private String fUzytSp28;

    @Column(name = "F_UZYT_SP_29")
    private String fUzytSp29;

    @Column(name = "F_UZYT_SP_30")
    private String fUzytSp30;

    @Column(name = "F_UZYT_SP_31")
    private String fUzytSp31;

    @Column(name = "F_UZYT_SP_32")
    private String fUzytSp32;

    @Column(name = "F_MIEJ_DZ_1")
    private String fMiejDz1;

    @Column(name = "F_MIEJ_DZ_2")
    private String fMiejDz2;

    @Column(name = "F_MIEJ_DZ_3")
    private String fMiejDz3;

    @Column(name = "F_MIEJ_DZ_4")
    private String fMiejDz4;

    @Column(name = "F_MIEJ_DZ_5")
    private String fMiejDz5;

    @Column(name = "F_MIEJ_DZ_6")
    private String fMiejDz6;

    @Column(name = "F_MIEJ_DZ_7")
    private String fMiejDz7;

    @Column(name = "F_MIEJ_DZ_8")
    private String fMiejDz8;

    @Column(name = "F_MIEJ_DZ_9")
    private String fMiejDz9;

    @Column(name = "F_MIEJ_DZ_10")
    private String fMiejDz10;

    @Column(name = "F_MIEJ_DZ_11")
    private String fMiejDz11;

    @Column(name = "IL_P_WOD")
    private Integer ilPWod;

    @Column(name = "IL_P_PROSZ")
    private Integer ilPProsz;

    @Column(name = "IL_P_PIAN")
    private Integer ilPPian;

    @Column(name = "IL_P_PIANC")
    private Integer ilPPianc;

    @Column(name = "IL_P_PIANS")
    private Integer ilPPians;

    @Column(name = "IL_P_PIANL")
    private Integer ilPPianl;

    @Column(name = "ZUZ_WODY")
    private Integer zuzWody;

    @Column(name = "ZUZ_PROSZKU")
    private Integer zuzProszku;

    @Column(name = "ZUZ_PIANY")
    private Integer zuzPiany;

    @Column(name = "ZUZ_NEUT")
    private Integer zuzNeut;

    @Column(name = "ZUZ_SORB")
    private Integer zuzSorb;

    @Column(name = "F_HYDRANT_1")
    private String fHydrant1;

    @Column(name = "ZUZ_WODY_HZ")
    private Integer zuzWodyHz;

    @Column(name = "F_HYDRANT_2")
    private String fHydrant2;

    @Column(name = "ZUZ_WODY_ZS")
    private Integer zuzWodyZs;

    @Column(name = "F_HYDRANT_3")
    private String fHydrant3;

    @Column(name = "ZUZ_WODY_ZN")
    private Integer zuzWodyZn;

    @Column(name = "F_HYDRANT_4")
    private String fHydrant4;

    @Column(name = "ZUZ_WODY_RZEKI")
    private Integer zuzWodyRzeki;

    @Column(name = "ONZ")
    private Integer onz;

    @Column(name = "F_ADR")
    private String fAdr;

    @Column(name = "F_WYBUCH_1")
    private String fWybuch1;

    @Column(name = "F_WYBUCH_2")
    private String fWybuch2;

    @Column(name = "F_WYBUCH_3")
    private String fWybuch3;

    @Column(name = "POMOC_ALL")
    private Integer pomocAll;

    @Column(name = "POMOC_PSP")
    private Integer pomocPsp;

    @Column(name = "POMOC_SZP")
    private Integer pomocSzp;

    @Column(name = "POMOC_EWA")
    private Integer pomocEwa;

    @Column(name = "WYP_RAT_S")
    private Integer wypRatS;

    @Column(name = "WYP_RAT_R")
    private Integer wypRatR;

    @Column(name = "WYP_PSP_S")
    private Integer wypPspS;

    @Column(name = "WYP_PSP_R")
    private Integer wypPspR;

    @Column(name = "WYP_INNI_S")
    private Integer wypInniS;

    @Column(name = "WYP_INNI_R")
    private Integer wypInniR;

    @Column(name = "WYP_DZ_S")
    private Integer wypDzS;

    @Column(name = "WYP_DZ_R")
    private Integer wypDzR;

    @Column(name = "WLK_POW")
    private Integer wlkPow;

    @Column(name = "WLK_KUB")
    private Integer wlkKub;

    @Column(name = "WLK_OB_DL")
    private Integer wlkObDl;

    @Column(name = "WLK_OB_SZ")
    private Integer wlkObSz;

    @Column(name = "WLK_OB_WY")
    private Integer wlkObWy;

    @Column(name = "STRATY")
    private Integer straty;

    @Column(name = "STRATY_BUD")
    private Integer stratyBud;

    @Column(name = "URATOWANO")
    private Integer uratowano;

    @Column(name = "PRZYCZYNA_KOD")
    private Integer przyczynaKod;

    @Column(name = "PRZYCZYNA_OPIS")
    private String przyczynaOpis;

    @Column(name = "F_INST_1")
    private String fInst1;

    @Column(name = "F_INST_2")
    private String fInst2;

    @Column(name = "F_INST_3")
    private String fInst3;

    @Column(name = "F_INST_4")
    private String fInst4;

    @Column(name = "F_INST_5")
    private String fInst5;

    @Column(name = "F_INST_6")
    private String fInst6;

    @Column(name = "F_INST_7")
    private String fInst7;

    @Column(name = "F_INST_8")
    private String fInst8;

    @Column(name = "F_INST_9")
    private String fInst9;

    @Column(name = "F_INST_10")
    private String fInst10;

    @Column(name = "F_INST_11")
    private String fInst11;

    @Column(name = "F_INST_12")
    private String fInst12;

    @Column(name = "F_INST_13")
    private String fInst13;

    @Column(name = "F_INST_14")
    private String fInst14;

    @Column(name = "F_INST_15")
    private String fInst15;

    @Column(name = "F_OPIS_OB_1")
    private String fOpisOb1;

    @Column(name = "F_OPIS_OB_2")
    private String fOpisOb2;

    @Column(name = "F_OPIS_OB_3")
    private String fOpisOb3;

    @Column(name = "F_OPIS_OB_4")
    private String fOpisOb4;

    @Column(name = "F_OPIS_OB_5")
    private String fOpisOb5;

    @Column(name = "F_OPIS_OB_6")
    private String fOpisOb6;

    @Column(name = "F_OPIS_OB_7")
    private String fOpisOb7;

    @Column(name = "F_ZABYTEK")
    private String fZabytek;

    @Column(name = "F_DOST_1")
    private String fDost1;

    @Column(name = "F_DOST_2")
    private String fDost2;

    @Column(name = "F_DOST_3")
    private String fDost3;

    @Column(name = "F_DOST_4")
    private String fDost4;

    @Column(name = "F_DOST_5")
    private String fDost5;

    @Column(name = "F_DOST_6")
    private String fDost6;

    @Column(name = "ZL")
    private Integer zl;

    @Column(name = "IL1")
    private Integer il1;

    @Column(name = "IL2")
    private Integer il2;

    @Column(name = "IL3")
    private Integer il3;
}
