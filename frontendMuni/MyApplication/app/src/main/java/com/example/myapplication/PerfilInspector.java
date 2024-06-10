package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilInspector extends AppCompatActivity {
    ImageView btn_back;
    ImageView home;
    Inspector inspector;
    ImageView config;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_inspector);

        btn_back = findViewById(R.id.btn_back);
        home = findViewById(R.id.home);
        config = findViewById(R.id.configImage);

        String cargo = getIntent().getStringExtra("cargo");
        inspector =  (Inspector) getIntent().getSerializableExtra("usuario");

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilInspector.this, configuracion.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",inspector);
                startActivity(intent);
            }
        });


    }
}
