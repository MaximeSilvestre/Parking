package com.example.maxime.projetparking.client;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.maxime.projetparking.GPSTracker;
import com.example.maxime.projetparking.Inscription;
import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Ticket;
import com.example.maxime.projetparking.entity.Voiture;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTicket extends AppCompatActivity {

    Spinner spinner;
    TimePicker time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();

        Intent intent = getIntent();
        String mail = intent.getStringExtra("user");
        List<Voiture> voitures = Voiture.listAll(Voiture.class);
        List<String> listeVoiture = new ArrayList<>();
        for (Voiture v : voitures) {
            listeVoiture.add(v.getImmatriculation());
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        time = (TimePicker) findViewById(R.id.timePicker);
        time.setIs24HourView(true);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listeVoiture
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btn1 = (Button) findViewById(R.id.btn_add_car);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                GPSTracker gps = new GPSTracker(context);
                Date dateDemande = new Date();
                int heureDemande = dateDemande.getHours();
                int minuteDemande = dateDemande.getMinutes();
                int hour = heureDemande;
                int minute = minuteDemande;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                   hour = time.getHour();
                   minute = time.getMinute();
                }

                int dureestationnementInitiale = (hour*60+minute)-(heureDemande*60-minuteDemande);
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                String immatriculation = spinner.getSelectedItem().toString();
                String zone = "zone1";
                Intent intent = getIntent();
                String currentMail = intent.getStringExtra("user");
                Ticket ticket = new Ticket(currentMail,dateDemande,heureDemande,minuteDemande,dureestationnementInitiale,0,0,0,immatriculation,latitude,longitude,zone);
                ticket.save();
                finish();

            }
        });

    }
}
