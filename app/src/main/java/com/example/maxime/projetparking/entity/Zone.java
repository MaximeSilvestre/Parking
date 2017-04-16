package com.example.maxime.projetparking.entity;

import com.orm.SugarRecord;

/**
 * Created by Maxime on 16/04/2017.
 */

public class Zone extends SugarRecord {

    private String nom;
    private float longitude;
    private float latitude;
    private float hauteur;
    private float largueur;
    private int heureDebut;
    private int heureFin;
    private float prixHeure;

    public String getNom() {
        return nom;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getHauteur() {
        return hauteur;
    }

    public float getLargueur() {
        return largueur;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public float getPrixHeure() {
        return prixHeure;
    }

    public Zone(String nom, float longitude, float latitude, float hauteur, float largueur, int heureDebut, int heureFin, float prixHeure) {
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
        this.hauteur = hauteur;
        this.largueur = largueur;

        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.prixHeure = prixHeure;
    }

    public Zone(){}
}
