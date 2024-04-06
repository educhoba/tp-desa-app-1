package com.example.tateti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView lblJugador, lblGanador;
    Button btnCentro, btnCentroArriba, btnCentroAbajo, btnCentroDerecha, btnCentroIzquierda,
           btnArribaDerecha, btnArribaIzquierda, btnAbajoDerecha, btnAbajoIzquierda, btnOtraVez;
    String nombre, jugadorUsa;
    Integer maquinaUsa, jugadas=0;
    boolean tengoElTurno;
    ControlDeJuego cj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tengoElTurno =  true;
        nombre = getIntent().getStringExtra("nombre");
        jugadorUsa = getIntent().getStringExtra("usa");
        cj = new ControlDeJuego();
        lblJugador = (TextView) findViewById(R.id.lblJugador);
        lblJugador.setText(nombre + " juega con " + jugadorUsa);
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

        btnOtraVez = (Button) findViewById(R.id.btnOtraVez);

        btnArribaIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,0,0);
            }
        });
        btnCentroArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,1,0);
            }
        });
        btnArribaDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,2,0);
            }
        });
        btnCentroIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,0,1);
            }
        });
        btnCentro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,1,1);
            }
        });
        btnCentroDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,2,1);
            }
        });
        btnAbajoIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,0,2);
            }
        });
        btnCentroAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,1,2);
            }
        });
        btnAbajoDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controloBoton(v,2,2);
            }
        });
        btnOtraVez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
    }
    private void controloBoton(View v, int i, int j) {
        if (tengoElTurno) {
            jugadas++;
            Button seleccionado = (Button) v;
            if (jugadorUsa.equalsIgnoreCase("circulos"))
                seleccionado.setText("O");
            else
                seleccionado.setText("X");

            tengoElTurno = false;
            seleccionado.setEnabled(false);

            cj.asignarValorJugado(i, j, jugadorUsa.equalsIgnoreCase("circulos")?2:1);

            if (cj.gano()) {
                lblGanador.setText("El ganador es " + nombre);
            } else if(jugadas==5){
                lblGanador.setText("Hay empate");
            }

            else {
                maquinaUsa = jugadorUsa.equalsIgnoreCase("circulos")?1:2;
                int posicion = cj.proximoMovimiento(maquinaUsa);

                Button seleccionadoMaquina = btnArribaIzquierda;

                switch (posicion){
                    case 1:
                        seleccionadoMaquina = btnArribaIzquierda;
                    break;
                    case 2:
                        seleccionadoMaquina = btnCentroIzquierda;
                    break;
                    case 3:
                        seleccionadoMaquina = btnAbajoIzquierda;
                        break;
                    case 4:
                        seleccionadoMaquina = btnCentroArriba;
                        break;
                    case 5:
                        seleccionadoMaquina = btnCentro;
                        break;
                    case 6:
                        seleccionadoMaquina = btnCentroAbajo;
                        break;
                    case 7:
                        seleccionadoMaquina = btnArribaDerecha;
                        break;
                    case 8:
                        seleccionadoMaquina = btnCentroDerecha;
                        break;
                    case 9:
                        seleccionadoMaquina = btnAbajoDerecha;
                        break;
                }

                if (jugadorUsa.equalsIgnoreCase("circulos"))
                    seleccionadoMaquina.setText("X");
                else
                    seleccionadoMaquina.setText("O");

                seleccionadoMaquina.setEnabled(false);

                if (cj.gano()){
                    lblGanador.setText("El ganador es la máquina");

                }
                tengoElTurno = true;
            }

        }
    }
    private void restart(){
        Intent aux = new Intent(GameActivity.this, MainActivity.class);

        startActivity(aux);
    }
}
