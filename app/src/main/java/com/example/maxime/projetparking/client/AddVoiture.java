package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Voiture;

public class AddVoiture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voiture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn1 = (Button) findViewById(R.id.btn_add_car);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String marque = ((EditText) findViewById(R.id.input_marque)).getText().toString();
                String model = ((EditText) findViewById(R.id.input_modele_voiture)).getText().toString();
                String immatriculation = ((EditText) findViewById(R.id.input_immatriculation_voiture)).getText().toString();
                Intent intent = getIntent();
                String currentMail = intent.getStringExtra("user");
                Voiture voiture = new Voiture(marque,model,immatriculation,currentMail);
                voiture.save();
                finish();
            }
        });

    }

}
