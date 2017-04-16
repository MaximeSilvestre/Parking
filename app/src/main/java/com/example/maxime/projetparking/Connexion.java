package com.example.maxime.projetparking;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maxime.projetparking.client.ClientActivity;
import com.example.maxime.projetparking.entity.User;

import java.util.List;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Button btn1 = (Button) findViewById(R.id.btn_signin);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inscription.class);
                startActivity(intent);

            }
        });

        Button btn2 = (Button) findViewById(R.id.btn_login);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.input_email)).getText().toString();
                String password = ((EditText) findViewById(R.id.input_password)).getText().toString();
                if(email.equals("admin") && password.equals("admin")){

                    finish();
                }
                List<User> user = User.find(User.class,"mail = ?",email);
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
                if(user.size() == 0){
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Identifiant ou mot de passe pas valide", Snackbar.LENGTH_LONG)
                            .setDuration(1000);

                    snackbar.show();
                }else{
                    User currentUser = user.get(0);
                    if(currentUser.verifierMotDePasse(password)){
                        Intent intent = new Intent(getApplicationContext(), ClientActivity.class);
                        intent.putExtra("user", currentUser.getMail());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
