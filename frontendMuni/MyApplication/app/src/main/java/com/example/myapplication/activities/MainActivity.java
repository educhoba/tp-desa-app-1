package com.example.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.ServiciosAdapter;
import com.example.myapplication.data.ReclamosLocalHelper;
import com.example.myapplication.models.Desperfectos;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.Servicios;


public class MainActivity extends AppCompatActivity {


    private ImageView botonEnviar;
    private Spinner rubroSpinner;
    private RecyclerView recyclerViewServicios;
    private ServiciosAdapter serviciosAdapter;
    private List<Servicios> serviciosList = new ArrayList<>();
    private Boolean reclamoGuardadoCreado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btncomercios = findViewById(R.id.btncomercios);
        Button btnprofesionales = findViewById(R.id.btnprofesionales);
        LinearLayout linearLayoutComercios = findViewById(R.id.comercios);
        LinearLayout linearLayoutProfesionales = findViewById(R.id.profesionales);
        rubroSpinner = findViewById(R.id.rubroSpinner);

        recyclerViewServicios = findViewById(R.id.recyclerViewServicios);
        recyclerViewServicios.setLayoutManager(new LinearLayoutManager(this));
        serviciosAdapter = new ServiciosAdapter(serviciosList);
        recyclerViewServicios.setAdapter(serviciosAdapter);


        new ObtenerRubrosUnicosTask().execute("comercio");
        new ObtenerServiciosTask().execute("comercio");

        btncomercios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutComercios.setVisibility(View.VISIBLE);
                linearLayoutProfesionales.setVisibility(View.GONE);
                new ObtenerRubrosUnicosTask().execute("comercio");
                new ObtenerServiciosTask().execute("comercio");
            }
        });

        btnprofesionales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutComercios.setVisibility(View.GONE);
                linearLayoutProfesionales.setVisibility(View.VISIBLE);
                new ObtenerRubrosUnicosTask().execute("profesional");
                new ObtenerServiciosTask().execute("profesional");

            }
        });


        botonEnviar = findViewById(R.id.button);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realiza la acción deseada aquí, como iniciar otra actividad
                Intent intent = new Intent(MainActivity.this, Login1.class);
                startActivity(intent);
            }
        });

        //probarCall();

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnected() && ni.getType() == ConnectivityManager.TYPE_WIFI) {

            ReclamosLocalHelper helper = new ReclamosLocalHelper(this);
            List<Reclamos> reclamos = helper.getReclamos();
            if(reclamos != null && !reclamos.isEmpty()){
                new AlertDialog.Builder(this)
                        .setTitle("Hay reclamos guardados!")
                        .setMessage("Quiere subirlos ahora?")
                        .setPositiveButton("Si!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                generarReclamosGuardados();

                            }
                        })
                        .setNegativeButton("No, despues.", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        }

    }

    private void generarReclamosGuardados(){
        //Recupero todos los clubes y los cargo en un arrayList
        ReclamosLocalHelper helper = new ReclamosLocalHelper(MainActivity.this);
        List<Reclamos> reclamos = helper.getReclamos();

        for (Reclamos r: reclamos
             ) {
            new RegistrarReclamoTask().execute(r);
        }
    }
    private void probarCall() {
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService service = retrofit.create(ApiService.class);

        Call<List<Desperfectos>> call = service.listarDesperfectos();
        call.enqueue(new Callback<List<Desperfectos>>() {
            @Override
            public void onResponse(Call<List<Desperfectos>> call, Response<List<Desperfectos>> response) {
                if (response.isSuccessful()) {
                    List<Desperfectos> sitiosList = response.body();
                    if (sitiosList != null) {
                        // Process the sitiosList here
                        for (Desperfectos sitio : sitiosList) {
                            Toast.makeText(MainActivity.this,"ID: " + sitio.getIdDesperfecto() + ", Descripcion: " + sitio.getDescripcion(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response unsuccessful. Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Desperfectos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch data: " + t.getMessage(), Toast.LENGTH_SHORT).show();

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

    private class ObtenerServiciosTask extends AsyncTask<String, Void, List<Servicios>> {
        @Override
        protected List<Servicios> doInBackground(String... params) {
            String tipo = params[0];

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<List<Servicios>> call = apiService.listarPorTipo(tipo);

            try {
                Response<List<Servicios>> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    return response.body();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(List<Servicios> servicios) {
            if (servicios != null) {
                serviciosList.clear();
                serviciosList.addAll(servicios);
                serviciosAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Error al obtener los servicios", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class RegistrarReclamoTask extends AsyncTask<Reclamos, Void, Void> {

        @Override
        protected Void doInBackground(Reclamos... reclamos) {
            Reclamos reclamo = reclamos[0];

            // Realizar la llamada Retrofit para registrar el servicio
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registrarReclamo(reclamo);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Reclamo registrado correctamente", Toast.LENGTH_SHORT).show();
                                ReclamosLocalHelper helper = new ReclamosLocalHelper(MainActivity.this);
                                helper.deleteAllReclamos();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Error al registrar el reclamo", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return null;
        }
    }
}