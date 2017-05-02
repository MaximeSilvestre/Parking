package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maxime.projetparking.Connexion;
import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.User;
import com.example.maxime.projetparking.entity.Voiture;

import java.util.List;

public class ModifierUtilisateur extends AppCompatActivity {

    User utilisateurCourant;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        idUser = getIntent().getStringExtra("user");

        utilisateurCourant = User.findById(User.class, Integer.parseInt(idUser));


        ((Button) findViewById(R.id.btn_signup)).setText("Modifier");


        ((TextView) findViewById(R.id.input_email)).setText(utilisateurCourant.getMail());
        ((TextView) findViewById(R.id.input_nom)).setText(utilisateurCourant.getNom());
        ((TextView) findViewById(R.id.input_prenom)).setText(utilisateurCourant.getPrenom());
        ((TextView) findViewById(R.id.input_telephone)).setText(utilisateurCourant.getTelephone());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn1 = (Button) findViewById(R.id.btn_signup);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nom = ((EditText) findViewById(R.id.input_nom)).getText().toString();
                String prenom = ((EditText) findViewById(R.id.input_prenom)).getText().toString();
                String telephone = ((EditText) findViewById(R.id.input_telephone)).getText().toString();
                String mail = ((EditText) findViewById(R.id.input_email)).getText().toString();

                utilisateurCourant.setNom(nom);
                utilisateurCourant.setMail(mail);
                utilisateurCourant.setPrenom(prenom);
                utilisateurCourant.setTelephone(telephone);
                utilisateurCourant.save();
                finish();
            }
        });


    }

}
