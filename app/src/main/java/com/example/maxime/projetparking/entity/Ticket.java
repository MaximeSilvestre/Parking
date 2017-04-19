package com.example.maxime.projetparking.entity;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Maxime on 16/04/2017.
 */

public class Ticket extends SugarRecord {
    private String mail;
    private Date dateDemande;
    private int heureDebut;
    private int minuteDebut;
    private int dureeStationementInitiale;
    private int dureeStationementEffectif;
    private int heureFin;
    private int minuteFin;
    private String immatriculation;
    private double latitude;
    private double longitude;
    private String nomZoneStationnement;

    public Ticket(String mail,Date dateDemande, int heureDebut, int minuteDebut, int dureeStationementInitiale, int dureeStationementEffectif, int heureFin, int minuteFin, String immatriculation, double latitude, double longitude, String nomZoneStationnement) {
        this.mail = mail;
        this.dateDemande = dateDemande;
        this.heureDebut = heureDebut;
        this.minuteDebut = minuteDebut;
        this.dureeStationementInitiale = dureeStationementInitiale;
        this.dureeStationementEffectif = dureeStationementEffectif;
        this.heureFin = heureFin;
        this.minuteFin = minuteFin;
        this.immatriculation = immatriculation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomZoneStationnement = nomZoneStationnement;
    }

    public Ticket() {   }

    public Date getDateDemande() {
        return dateDemande;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getDureeStationementInitiale() {
        return dureeStationementInitiale;
    }

    public int getDureeStationementEffectif() {
        return dureeStationementEffectif;
    }

    public int getHeureFin() {
        return heureFin;
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

}
