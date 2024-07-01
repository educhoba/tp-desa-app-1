package com.example.myapplication.activities;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.Usuarios;


public class PerfilUser extends AppCompatActivity {
    ImageView btn_back;
    Usuarios user;
    ImageView home;
    ImageView config;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_user);
        home = findViewById(R.id.home);
        //Button btn = findViewById(R.id.Password);
        Button btn_generar_publicacion = findViewById(R.id.btn_generar_publicacion);
        Button btn_generar_reclamo = findViewById(R.id.btn_generar_reclamo);
        Button btn_generar_denuncia = findViewById(R.id.btn_generar_denuncia);
        Button btn_ver_reclamos = findViewById(R.id.btn_ver_reclamos);
        Button btn_ver_denuncias = findViewById(R.id.btn_ver_denuncias);
        Button btn_comyprof = findViewById(R.id.btn_comyprof);



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
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, MainActivity.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });

        btn_generar_publicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, GenerarReclamo.class);
                startActivity(intent);
            }
        });

        btn_generar_denuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, GenerarDenuncia.class);
                intent.putExtra("usuario",user);
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
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });
        btn_ver_reclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, VerReclamos.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });

        btn_ver_denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, VerDenuncias.class);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });

        btn_comyprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PerfilUser.this, MainActivity.class);
                intent.putExtra("cargo",cargo);
                intent.putExtra("usuario",user);
                startActivity(intent);
            }
        });


    }
}
