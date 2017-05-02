package com.example.maxime.projetparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.maxime.projetparking.entity.User;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button btn1 = (Button) findViewById(R.id.btn_signup);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String nom = ((EditText) findViewById(R.id.input_nom)).getText().toString();
                String prenom = ((EditText) findViewById(R.id.input_prenom)).getText().toString();
                String telephone = ((EditText) findViewById(R.id.input_telephone)).getText().toString();
                String mail = ((EditText) findViewById(R.id.input_email)).getText().toString();
                String motDePasse = ((EditText) findViewById(R.id.input_password)).getText().toString();

                User user = new User(nom,prenom,telephone,mail,motDePasse);
                user.save();

                Intent intent = new Intent(getApplicationContext(), Connexion.class);
                startActivity(intent);
                finish();

            }
        });



    }
}
