package com.example.tateti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView lblJugador, lblGanador;
    Button btnCentro, btnCentroArriba, btnCentroAbajo, btnCentroDerecha, btnCentroIzquierda,
           btnArribaDerecha, btnArribaIzquierda, btnAbajoDerecha, btnAbajoIzquierda;
    String nombre, usa;
    boolean tengoElTurno;
    ControlDeJuego cj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tengoElTurno =  true;
        nombre = getIntent().getStringExtra("nombre");
        usa = getIntent().getStringExtra("usa");
        cj = new ControlDeJuego();
        lblJugador = (TextView) findViewById(R.id.lblJugador);
        lblJugador.setText(nombre + " juega con " + usa);
        btnCentro = (Button) findViewById(R.id.btnCentro);
        btnCentroArriba = (Button) findViewById(R.id.btnCentroArriba);
        btnCentroAbajo = (Button) findViewById(R.id.btnCentroAbajo);
        btnCentroDerecha = (Button) findViewById(R.id.btnCentroDerecha);
        btnCentroIzquierda = (Button) findViewById(R.id.btnCentroIzquierda);
        btnArribaDerecha = (Button) findViewById(R.id.btnArribaDerecha);
        btnArribaIzquierda = (Button) findViewById(R.id.btnArribaIzquierda);
        btnAbajoDerecha = (Button) findViewById(R.id.btnAbajoDerecha);
        btnAbajoIzquierda = (Button) findViewById(R.id.btnAbajoIzquierda);
        lblGanador = (TextView) findViewById(R.id.lblGanador);

        btnArribaIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnArribaDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnAbajoIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnCentroAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
        btnAbajoDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v);
            }
        });
    }
    private void controloBoton(View v) {
        if (tengoElTurno) {
            Button seleccionado = (Button) v;
            if (usa.equalsIgnoreCase("circulos"))
                seleccionado.setText("O");
            else
                seleccionado.setText("X");
            tengoElTurno = false;
            seleccionado.setEnabled(false);
            cj.asignarValorJugado(1, 1, usa);
            if (cj.gano()) {
                lblGanador.setText("El ganador es " + nombre);
            } else {
                cj.proximoMovimiento();
                if (cj.gano()) {
                    lblGanador.setText("El ganador es la máquina");
                }
                tengoElTurno = true;
            }

        }
    }
}
