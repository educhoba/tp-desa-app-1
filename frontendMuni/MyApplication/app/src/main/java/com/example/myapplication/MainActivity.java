package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private ImageView botonEnviar;
    private LinearLayout layoutDetalleServicio;
    private Spinner rubroSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btncomercios = findViewById(R.id.btncomercios);
        Button btnprofesionales = findViewById(R.id.btnprofesionales);
        LinearLayout linearLayoutComercios = findViewById(R.id.comercios);
        LinearLayout linearLayoutProfesionales = findViewById(R.id.profesionales);
        rubroSpinner = findViewById(R.id.rubroSpinner);
        layoutDetalleServicio = findViewById(R.id.detalle);


        new ObtenerRubrosUnicosTask().execute("comercio");

        btncomercios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutComercios.setVisibility(View.VISIBLE);
                linearLayoutProfesionales.setVisibility(View.GONE);
                new ObtenerRubrosUnicosTask().execute("comercio");
            }
        });

        btnprofesionales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutComercios.setVisibility(View.GONE);
                linearLayoutProfesionales.setVisibility(View.VISIBLE);
                new ObtenerRubrosUnicosTask().execute("profesional");
            }
        });


        botonEnviar = findViewById(R.id.button);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }

    private class ObtenerRubrosUnicosTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            String tipo = params[0];

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<List<Servicios>> call = apiService.listarPorTipo(tipo);

            try {
                Response<List<Servicios>> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<Servicios> servicios = response.body();
                    Set<String> rubrosUnicos = new HashSet<>();

                    for (Servicios servicio : servicios) {
                        rubrosUnicos.add(servicio.getRubro());
                    }

                    return new ArrayList<>(rubrosUnicos);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<String> rubrosUnicos) {
            if (rubrosUnicos != null) {
                configurarSpinner(rubrosUnicos);
            } else {
                Toast.makeText(MainActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void configurarSpinner(List<String> rubrosUnicos) {
        rubrosUnicos.add(0, "Elija el rubro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rubrosUnicos) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    ((android.widget.TextView) view).setTextColor(android.graphics.Color.GRAY);
                } else {
                    ((android.widget.TextView) view).setTextColor(android.graphics.Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rubroSpinner.setAdapter(adapter);
    }

    // AsyncTask para realizar alguna tarea en segundo plano
    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(MainActivity.this, Login1.class);
            startActivity(intent);
        }
    }
}