package com.example.maxime.projetparking.entity;

import com.orm.SugarRecord;

/**
 * Created by Maxime on 15/04/2017.
 */

public class Voiture extends SugarRecord {

    private String marque;
    private String modele;
    private String immatriculation;
    private String mailUser;

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public Voiture(String marque, String modele, String immatriculation, String mailUser) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.mailUser = mailUser;
    }

    public Voiture(){}

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMailUser() {
        return mailUser;
    }
}
