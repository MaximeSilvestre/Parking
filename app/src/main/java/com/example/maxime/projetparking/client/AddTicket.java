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
import android.view.WindowManager;
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
import com.example.maxime.projetparking.counter.NotifyService;
import com.example.maxime.projetparking.entity.Ticket;
import com.example.maxime.projetparking.entity.Voiture;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTicket extends AppCompatActivity {

    Spinner spinnerImmatriculation;
    Spinner spinnerZone;
    TimePicker time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();

        Intent intent = getIntent();
        String mail = intent.getStringExtra("user");
        List<Voiture> voitures = Voiture.listAll(Voiture.class);
        List<String> listeVoiture = new ArrayList<>();
        List<String> listeZone = new ArrayList<>();
        listeZone.add("zone1");
        listeZone.add("zone2");
        for (Voiture v : voitures) {
            listeVoiture.add(v.getImmatriculation());
        }
        spinnerImmatriculation = (Spinner) findViewById(R.id.spinnerImmatriculation);
        spinnerZone = (Spinner) findViewById(R.id.spinnerZone);
        time = (TimePicker) findViewById(R.id.timePicker);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            time.setHour(0);
            time.setMinute(0);
        }
        time.setIs24HourView(true);
        ArrayAdapter adapterImmatriculation = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listeVoiture
        );

        ArrayAdapter adapterZone = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listeZone
        );

        adapterImmatriculation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImmatriculation.setAdapter(adapterImmatriculation);

        adapterZone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZone.setAdapter(adapterZone);

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

                int dureestationnementInitiale = (hour*60+minute);
                long dureestationnementInitialeMS = (hour*60+minute)*60000;
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                String immatriculation = spinnerImmatriculation.getSelectedItem().toString();
                String zone = spinnerZone.getSelectedItem().toString();
                Intent intent = getIntent();
                String idUser = intent.getStringExtra("user");
                Ticket ticket = new Ticket(idUser,dateDemande,null,dureestationnementInitiale,0,immatriculation,latitude,longitude,zone);
                ticket.save();

                Intent service = new Intent(getApplicationContext(), NotifyService.class);
                service.putExtra("user", idUser);
                service.putExtra("idTicket", ticket.getId());
                service.putExtra(NotifyService.KEY_EXTRA_LONG_TIME_IN_MS, dureestationnementInitialeMS);
                startService(service);

                finish();

            }
        });

    }
}
