package com.example.maxime.projetparking.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.maxime.projetparking.R;
import com.example.maxime.projetparking.entity.Ticket;

import java.util.List;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String immatriculation = intent.getStringExtra("immatriculation");

        //List<Ticket> ticket =  Ticket.find(Ticket.class,"immatriculation = ?",immatriculation, "dateDemande DESC", "1");
    }
}
