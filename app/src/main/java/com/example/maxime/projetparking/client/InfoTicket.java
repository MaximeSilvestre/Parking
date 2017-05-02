package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxime.projetparking.Inscription;
import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Ticket;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InfoTicket extends AppCompatActivity {

    Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String item = intent.getStringExtra("idTicket");
        int idTicket = Integer.parseInt(item.substring(5));

        ticket = Ticket.findById(Ticket.class,idTicket);

        Button btn1 = (Button) findViewById(R.id.ajouter);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ticket.setDureeStationementInitiale(ticket.getDureeStationementInitiale()+10);
                ticket.save();
                ((TextView)findViewById(R.id.dureeStationementInitiale)).setText("Durée stationnement prévu : "+ticket.getDureeStationementInitiale());
            }
        });

        Button btn3 = (Button) findViewById(R.id.finir);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date dateFin = new Date();
                Date dateDebut = ticket.getDateDebut();
                int difference = (int ) (dateFin.getTime() - dateDebut.getTime())/60000;
                ticket.setDateFin(dateFin);
                ticket.setDureeStationementEffectif(difference);
                ticket.save();
                ((TextView)findViewById(R.id.dureeStationementEffective)).setText("Durée stationnement effective : "+difference);
            }
        });

        Button btn2 = (Button) findViewById(R.id.map);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+ticket.getLatitude()+","+ticket.getLongitude()+"?q="+ticket.getLatitude()+","+ticket.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });


        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(ticket.getLatitude(), ticket.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String adresse = "Adresse : Pas de resultat";
        try{
            Address address = addresses.get(0);
            adresse = address.getPostalCode()+" , "+address.getLocality()+" , "+address.getFeatureName()+" "+address.getThoroughfare();
        }catch (Exception e){}


        if(ticket.getDureeStationementEffectif() != 0){
            btn3.setEnabled(false);
            btn1.setEnabled(false);
        }
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
        ((TextView)findViewById(R.id.titre)).setText("Numéro ticket : "+ticket.getId());
        ((TextView)findViewById(R.id.dureeStationementInitiale)).setText("Durée stationnement prévu : "+ticket.getDureeStationementInitiale());
        ((TextView)findViewById(R.id.dureeStationementEffective)).setText("Durée stationnement réel : "+ticket.getDureeStationementEffectif());
        ((TextView)findViewById(R.id.immatriculaion)).setText("Immatriculation : "+ticket.getImmatriculation());
        ((TextView)findViewById(R.id.zone)).setText("Nom zone : "+ticket.getNomZoneStationnement());
        ((TextView)findViewById(R.id.debutStationnement)).setText("Date début : "+shortDateFormat.format(ticket.getDateDebut()));
        ((TextView)findViewById(R.id.adresse)).setText(adresse);
    }

}
