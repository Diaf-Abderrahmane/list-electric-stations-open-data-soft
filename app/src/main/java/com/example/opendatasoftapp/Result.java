package com.example.opendatasoftapp;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("nom_amenageur")
    private String nomAmenageur;

    @SerializedName("siren_amenageur")
    private String sirenAmenageur;

    @SerializedName("contact_amenageur")
    private String contactAmenageur;

    @SerializedName("nom_operateur")
    private String nomOperateur;

    @SerializedName("contact_operateur")
    private String contactOperateur;

    @SerializedName("telephone_operateur")
    private String telephoneOperateur;

    @SerializedName("nom_enseigne")
    private String nomEnseigne;

    @SerializedName("id_station_itinerance")
    private String idStationItinerance;

    @SerializedName("id_station_local")
    private String idStationLocal;

    @SerializedName("nom_station")
    private String nomStation;

    @SerializedName("implantation_station")
    private String implantationStation;

    @SerializedName("adresse_station")
    private String adresseStation;

    @SerializedName("code_insee_commune")
    private String codeInseeCommune;

    @SerializedName("nbre_pdc")
    private String nbrePdc;

    @SerializedName("id_pdc_itinerance")
    private String idPdcItinerance;

    @SerializedName("id_pdc_local")
    private String idPdcLocal;

    @SerializedName("puissance_nominale")
    private String puissanceNominale;

    @SerializedName("prise_type_ef")
    private String priseTypeEf;

    @SerializedName("prise_type_2")
    private String priseType2;

    @SerializedName("prise_type_combo_ccs")
    private String priseTypeComboCcs;

    @SerializedName("prise_type_chademo")
    private String priseTypeChademo;

    @SerializedName("prise_type_autre")
    private String priseTypeAutre;

    @SerializedName("gratuit")
    private String gratuit;

    @SerializedName("paiement_acte")
    private String paiementActe;

    @SerializedName("paiement_cb")
    private String paiementCb;

    @SerializedName("paiement_autre")
    private String paiementAutre;

    @SerializedName("tarification")
    private String tarification;

    @SerializedName("condition_acces")
    private String conditionAcces;

    @SerializedName("reservation")
    private String reservation;

    @SerializedName("horaires")
    private String horaires;

    @SerializedName("accessibilite_pmr")
    private String accessibilitePmr;

    @SerializedName("restriction_gabarit")
    private String restrictionGabarit;

    @SerializedName("station_deux_roues")
    private String stationDeuxRoues;

    @SerializedName("raccordement")
    private String raccordement;

    @SerializedName("num_pdl")
    private String numPdl;

    @SerializedName("date_mise_en_service")
    private String dateMiseEnService;

    @SerializedName("observations")
    private String observations;

    @SerializedName("date_maj")
    private String dateMaj;

    @SerializedName("meta_name_com")
    private String metaNameCom;

    @SerializedName("meta_code_com")
    private String metaCodeCom;

    @SerializedName("meta_name_dep")
    private String metaNameDep;

    @SerializedName("meta_code_dep")
    private String metaCodeDep;

    @SerializedName("meta_name_reg")
    private String metaNameReg;

    @SerializedName("meta_code_reg")
    private String metaCodeReg;

    @SerializedName("meta_geo_point")
    private GeoPoint metaGeoPoint;

    @SerializedName("meta_osm_id")
    private String metaOsmId;

    @SerializedName("meta_osm_url")
    private String metaOsmUrl;

    @SerializedName("meta_first_update")
    private String metaFirstUpdate;

    @SerializedName("meta_last_update")
    private String metaLastUpdate;

    @SerializedName("meta_versions_number")
    private int metaVersionsNumber;

    @SerializedName("meta_users_number")
    private int metaUsersNumber;

    @SerializedName("coordonneesxy")
    private String coordonneesxy;

    // Getters and Setters for all fields
    public String getNomAmenageur() { return nomAmenageur; }
    public String getSirenAmenageur() { return sirenAmenageur; }
    public String getContactAmenageur() { return contactAmenageur; }
    public String getNomOperateur() { return nomOperateur; }
    public String getContactOperateur() { return contactOperateur; }
    public String getTelephoneOperateur() { return telephoneOperateur; }
    public String getNomEnseigne() { return nomEnseigne; }
    public String getIdStationItinerance() { return idStationItinerance; }
    public String getIdStationLocal() { return idStationLocal; }
    public String getNomStation() { return nomStation; }
    public String getImplantationStation() { return implantationStation; }
    public String getAdresseStation() { return adresseStation; }
    public String getCodeInseeCommune() { return codeInseeCommune; }
    public String getNbrePdc() { return nbrePdc; }
    public String getIdPdcItinerance() { return idPdcItinerance; }
    public String getIdPdcLocal() { return idPdcLocal; }
    public String getPuissanceNominale() { return puissanceNominale; }
    public String getPriseTypeEf() { return priseTypeEf; }
    public String getPriseType2() { return priseType2; }
    public String getPriseTypeComboCcs() { return priseTypeComboCcs; }
    public String getPriseTypeChademo() { return priseTypeChademo; }
    public String getPriseTypeAutre() { return priseTypeAutre; }
    public String getGratuit() { return gratuit; }
    public String getPaiementActe() { return paiementActe; }
    public String getPaiementCb() { return paiementCb; }
    public String getPaiementAutre() { return paiementAutre; }
    public String getTarification() { return tarification; }
    public String getConditionAcces() { return conditionAcces; }
    public String getReservation() { return reservation; }
    public String getHoraires() { return horaires; }
    public String getAccessibilitePmr() { return accessibilitePmr; }
    public String getRestrictionGabarit() { return restrictionGabarit; }
    public String getStationDeuxRoues() { return stationDeuxRoues; }
    public String getRaccordement() { return raccordement; }
    public String getNumPdl() { return numPdl; }
    public String getDateMiseEnService() { return dateMiseEnService; }
    public String getObservations() { return observations; }
    public String getDateMaj() { return dateMaj; }
    public String getMetaNameCom() { return metaNameCom; }
    public String getMetaCodeCom() { return metaCodeCom; }
    public String getMetaNameDep() { return metaNameDep; }
    public String getMetaCodeDep() { return metaCodeDep; }
    public String getMetaNameReg() { return metaNameReg; }
    public String getMetaCodeReg() { return metaCodeReg; }
    public GeoPoint getMetaGeoPoint() { return metaGeoPoint; }
    public String getMetaOsmId() { return metaOsmId; }
    public String getMetaOsmUrl() { return metaOsmUrl; }
    public String getMetaFirstUpdate() { return metaFirstUpdate; }
    public String getMetaLastUpdate() { return metaLastUpdate; }
    public int getMetaVersionsNumber() { return metaVersionsNumber; }
    public int getMetaUsersNumber() { return metaUsersNumber; }
    public String getCoordonneesxy() { return coordonneesxy; }

}


