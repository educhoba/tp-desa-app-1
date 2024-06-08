package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class PerfilUser extends AppCompatActivity {
    ImageView btn_back;
    ImageView home;

    ImageView config;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);

        btn_back = findViewById(R.id.btn_back);
        home = findViewById(R.id.home);
        config = findViewById(R.id.configImage);

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUser.this, configuracion.class);
                startActivity(intent);
            }
        });



    }
}
