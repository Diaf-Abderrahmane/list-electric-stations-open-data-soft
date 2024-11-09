package com.example.opendatasoftapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {
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

    // Implementing Parcelable
    protected Result(Parcel in) {
        nomAmenageur = in.readString();
        sirenAmenageur = in.readString();
        contactAmenageur = in.readString();
        nomOperateur = in.readString();
        contactOperateur = in.readString();
        telephoneOperateur = in.readString();
        nomEnseigne = in.readString();
        idStationItinerance = in.readString();
        idStationLocal = in.readString();
        nomStation = in.readString();
        implantationStation = in.readString();
        adresseStation = in.readString();
        codeInseeCommune = in.readString();
        nbrePdc = in.readString();
        idPdcItinerance = in.readString();
        idPdcLocal = in.readString();
        puissanceNominale = in.readString();
        priseTypeEf = in.readString();
        priseType2 = in.readString();
        priseTypeComboCcs = in.readString();
        priseTypeChademo = in.readString();
        priseTypeAutre = in.readString();
        gratuit = in.readString();
        paiementActe = in.readString();
        paiementCb = in.readString();
        paiementAutre = in.readString();
        tarification = in.readString();
        conditionAcces = in.readString();
        reservation = in.readString();
        horaires = in.readString();
        accessibilitePmr = in.readString();
        restrictionGabarit = in.readString();
        stationDeuxRoues = in.readString();
        raccordement = in.readString();
        numPdl = in.readString();
        dateMiseEnService = in.readString();
        observations = in.readString();
        dateMaj = in.readString();
        metaNameCom = in.readString();
        metaCodeCom = in.readString();
        metaNameDep = in.readString();
        metaCodeDep = in.readString();
        metaNameReg = in.readString();
        metaCodeReg = in.readString();
        metaGeoPoint = in.readParcelable(GeoPoint.class.getClassLoader());
        metaOsmId = in.readString();
        metaOsmUrl = in.readString();
        metaFirstUpdate = in.readString();
        metaLastUpdate = in.readString();
        metaVersionsNumber = in.readInt();
        metaUsersNumber = in.readInt();
        coordonneesxy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomAmenageur);
        dest.writeString(sirenAmenageur);
        dest.writeString(contactAmenageur);
        dest.writeString(nomOperateur);
        dest.writeString(contactOperateur);
        dest.writeString(telephoneOperateur);
        dest.writeString(nomEnseigne);
        dest.writeString(idStationItinerance);
        dest.writeString(idStationLocal);
        dest.writeString(nomStation);
        dest.writeString(implantationStation);
        dest.writeString(adresseStation);
        dest.writeString(codeInseeCommune);
        dest.writeString(nbrePdc);
        dest.writeString(idPdcItinerance);
        dest.writeString(idPdcLocal);
        dest.writeString(puissanceNominale);
        dest.writeString(priseTypeEf);
        dest.writeString(priseType2);
        dest.writeString(priseTypeComboCcs);
        dest.writeString(priseTypeChademo);
        dest.writeString(priseTypeAutre);
        dest.writeString(gratuit);
        dest.writeString(paiementActe);
        dest.writeString(paiementCb);
        dest.writeString(paiementAutre);
        dest.writeString(tarification);
        dest.writeString(conditionAcces);
        dest.writeString(reservation);
        dest.writeString(horaires);
        dest.writeString(accessibilitePmr);
        dest.writeString(restrictionGabarit);
        dest.writeString(stationDeuxRoues);
        dest.writeString(raccordement);
        dest.writeString(numPdl);
        dest.writeString(dateMiseEnService);
        dest.writeString(observations);
        dest.writeString(dateMaj);
        dest.writeString(metaNameCom);
        dest.writeString(metaCodeCom);
        dest.writeString(metaNameDep);
        dest.writeString(metaCodeDep);
        dest.writeString(metaNameReg);
        dest.writeString(metaCodeReg);
        dest.writeParcelable(metaGeoPoint, flags);
        dest.writeString(metaOsmId);
        dest.writeString(metaOsmUrl);
        dest.writeString(metaFirstUpdate);
        dest.writeString(metaLastUpdate);
        dest.writeInt(metaVersionsNumber);
        dest.writeInt(metaUsersNumber);
        dest.writeString(coordonneesxy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
