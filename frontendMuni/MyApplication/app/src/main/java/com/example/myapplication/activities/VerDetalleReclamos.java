package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.widget.TextView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.ImagePagerAdapter;
import com.example.myapplication.models.Desperfectos;
import com.example.myapplication.models.MovimientoDenuncia;
import com.example.myapplication.models.MovimientoReclamo;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.ApiService;
import com.example.myapplication.RetrofitClient;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.Sitios;
import com.google.android.material.tabs.TabLayout;

public class VerDetalleReclamos extends AppCompatActivity {


    private TextView textViewId, textViewSitio, textViewDesperfecto, textViewDescripcion,textViewMovimientos;
    private int reclamoId;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verunreclamo);

        textViewId = findViewById(R.id.textId);
        textViewSitio = findViewById(R.id.textSitio);
        textViewDesperfecto = findViewById(R.id.textDesperfecto);
        textViewDescripcion = findViewById(R.id.textDescripcion);
        textViewMovimientos = findViewById(R.id.textMovimientos);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Obtener el ID del reclamo del Intent
        reclamoId = getIntent().getIntExtra("RECLAMO_ID", -1);

        // Llamar al método para obtener los detalles del reclamo
        if (reclamoId != -1) {
            new FetchReclamoDetailsTask().execute(reclamoId);
        }

    }


    private class FetchReclamoDetailsTask extends AsyncTask<Integer, Void, Reclamos> {
        @Override
        protected Reclamos doInBackground(Integer... params) {
            int id = params[0];
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Reclamos> call = apiService.getReclamoById(id);

            try {
                Response<Reclamos> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Reclamos reclamo) {
            if (reclamo != null) {
                textViewId.setText("Nro de Reclamo: " + reclamo.getIdReclamo());
                textViewDescripcion.setText("Descripción: " + reclamo.getDescripcion());
                textViewMovimientos.setText(listaAString(reclamo.getMovimientos()));
                ImagePagerAdapter adapter = new ImagePagerAdapter(reclamo.getImagenes());
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager, true);

                if(reclamo.getIdSitio() == null)
                    textViewSitio.setText("Sitio: Error");
                else{
                    Integer sitioId = reclamo.getIdSitio();
                    new FetchSitiosDetailsTask().execute(sitioId);
                }
                if(reclamo.getIdDesperfecto() == null)
                    textViewDesperfecto.setText("Desperfecto: Sin desperfecto.");
                else{
                    Integer idDesperfecto = reclamo.getIdDesperfecto();
                    new FetchDesperfectosDetailsTask().execute(idDesperfecto);
                }
            } else {
                Toast.makeText(VerDetalleReclamos.this, "Error al obtener detalles del reclamo", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class FetchSitiosDetailsTask extends AsyncTask<Integer, Void, Sitios> {
        @Override
        protected Sitios doInBackground(Integer... params) {
            int id = params[0];
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Sitios> call = apiService.getSitioPorById(id);

            try {
                Response<Sitios> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Sitios sitio) {
            if (sitio != null) {
                textViewSitio.setText("Sitio: " + sitio.getDescripcion());
            } else {
                textViewSitio.setText("Sitio: Error");
                Toast.makeText(VerDetalleReclamos.this, "Error al obtener detalles del sitio", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class FetchDesperfectosDetailsTask extends AsyncTask<Integer, Void, Desperfectos> {
        @Override
        protected Desperfectos doInBackground(Integer... params) {
            int id = params[0];
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Desperfectos> call = apiService.getDesperfectosPorById(id);

            try {
                Response<Desperfectos> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Desperfectos desperfectos) {
            if (desperfectos != null) {
                textViewDesperfecto.setText("Desperfecto: " + desperfectos.getDescripcion());
            } else {
                textViewDesperfecto.setText("Desperfecto: Error");
                Toast.makeText(VerDetalleReclamos.this, "Error al obtener detalles del desperfecto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String listaAString(List<MovimientoReclamo> lista){
        StringBuilder resultado = new StringBuilder();

        if(lista != null && lista.size() > 0) {
            resultado.append("Últimos movimientos:\n\n");
            int startIndex = Math.max(lista.size() - 2, 0);

            for (int i = startIndex; i < lista.size(); i++) {
                MovimientoReclamo movimiento = lista.get(i);
                resultado.append("Responsable: '").append(movimiento.getResponsable()).append("'\n");
                resultado.append("Causa: '").append(movimiento.getCausa()).append("'\n");
                resultado.append("Fecha: '").append(formatDate(movimiento.getFecha())).append("'\n");
            }
        }else{
            resultado.append("Últimos movimientos: 'Sin movimientos'\n\n");
        }

        // Convertir el StringBuilder a String
        return  resultado.toString();
    }
    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}


