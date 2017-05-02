package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxime.projetparking.Inscription;
import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Voiture;

import java.util.List;

public class ModifierVoiture extends AppCompatActivity {

    Voiture voitureCourrante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voiture);
        Intent intent = getIntent();
        String immatriculation = intent.getStringExtra("immatriculation");
        immatriculation = immatriculation.substring(18,immatriculation.length());
        ((TextView) findViewById(R.id.input_immatriculation_voiture)).setText(immatriculation);
        final List<Voiture> voiture = Voiture.find(Voiture.class,"immatriculation = ?",immatriculation);
        voitureCourrante = voiture.get(0);
        ((Button) findViewById(R.id.btn_add_car)).setText("Modifier");
        ((TextView) findViewById(R.id.input_marque)).setText(voitureCourrante.getMarque());
        ((TextView) findViewById(R.id.input_modele_voiture)).setText(voitureCourrante.getModele());
        ((TextView) findViewById(R.id.input_immatriculation_voiture)).setText(voitureCourrante.getImmatriculation());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn1 = (Button) findViewById(R.id.btn_add_car);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voitureCourrante.setImmatriculation(((TextView) findViewById(R.id.input_immatriculation_voiture)).getText().toString());
                voitureCourrante.setMarque(((TextView) findViewById(R.id.input_marque)).getText().toString());
                voitureCourrante.setModele(((TextView) findViewById(R.id.input_modele_voiture)).getText().toString());
                voitureCourrante.save();
                Intent intent = new Intent(getApplicationContext(), ClientActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
