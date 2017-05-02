package com.example.maxime.projetparking.entity;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Maxime on 16/04/2017.
 */

public class Ticket extends SugarRecord {
    private String idUser;
    private Date dateDebut;
    private Date dateFin;
    private int dureeStationementInitiale;
    private int dureeStationementEffectif;
    private String immatriculation;
    private double latitude;
    private double longitude;
    private String nomZoneStationnement;

    public Ticket(String idUser, Date dateDebut, Date dateFin, int dureeStationementInitiale, int dureeStationementEffectif, String immatriculation, double latitude, double longitude, String nomZoneStationnement) {
        this.idUser = idUser;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeStationementInitiale = dureeStationementInitiale;
        this.dureeStationementEffectif = dureeStationementEffectif;
        this.immatriculation = immatriculation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomZoneStationnement = nomZoneStationnement;
    }

    public Ticket() {   }

    public String getMail() {
        return idUser;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getDureeStationementInitiale() {
        return dureeStationementInitiale;
    }

    public int getDureeStationementEffectif() {
        return dureeStationementEffectif;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNomZoneStationnement() {
        return nomZoneStationnement;
    }


    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setDureeStationementInitiale(int dureeStationementInitiale) {
        this.dureeStationementInitiale = dureeStationementInitiale;
    }

    public void setDureeStationementEffectif(int dureeStationementEffectif) {
        this.dureeStationementEffectif = dureeStationementEffectif;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setNomZoneStationnement(String nomZoneStationnement) {
        this.nomZoneStationnement = nomZoneStationnement;
    }
}
