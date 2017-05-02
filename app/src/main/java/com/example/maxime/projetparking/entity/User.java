package com.example.maxime.projetparking.entity;

import com.orm.SugarRecord;

/**
 * Created by Maxime on 14/04/2017.
 */

public class User extends SugarRecord {

    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private String motDePasse;

    public User(){}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public User(String nom, String prenom, String telephone, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;

        this.telephone = telephone;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public Boolean verifierMotDePasse(String mdp) {
        return mdp.equals(this.motDePasse);
    }
}
