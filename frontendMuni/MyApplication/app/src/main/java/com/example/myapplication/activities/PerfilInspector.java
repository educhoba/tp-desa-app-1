package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.Inspector;

public class PerfilInspector extends AppCompatActivity {
    ImageView btn_back;
    ImageView home;
    Inspector inspector;
    ImageView config;
    Button btn_generar_reclamo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_inspector);

        home = findViewById(R.id.home);
        config = findViewById(R.id.configImage);
        btn_generar_reclamo = findViewById(R.id.btn_generar_reclamo);


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

        btn_generar_reclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilInspector.this, GenerarReclamo.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",inspector);
                startActivity(intent);
            }
        });


    }
}
