package com.example.myapplication.activities;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.Publicacion;
import com.example.myapplication.models.Usuarios;


public class PerfilUser extends AppCompatActivity {
    ImageView btn_back;

    ImageView home;
    Usuarios user;
    ImageView config;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);

        btn_back = findViewById(R.id.btn_back);
        home = findViewById(R.id.home);

        Button btn = findViewById(R.id.Password);
        Button btn_generar_publicacion = findViewById(R.id.btn_generar_publicacion);
        Button btn_generar_reclamo = findViewById(R.id.Password);



        config = findViewById(R.id.configImage);
        String cargo = getIntent().getStringExtra("cargo");
        user =  (Usuarios) getIntent().getSerializableExtra("usuario");

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUser.this, configuracion.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, GenerarReclamo.class);
                startActivity(intent);
            }
        });

        btn_generar_publicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, Publicacion.class);
                startActivity(intent);
            }
        });

        btn_generar_reclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, GenerarReclamo.class);
                startActivity(intent);
            }
        });





    }
}
