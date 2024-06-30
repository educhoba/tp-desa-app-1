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

import com.example.myapplication.models.Reclamos;
import com.example.myapplication.ApiService;
import com.example.myapplication.RetrofitClient;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Reclamos;

public class VerDetalleReclamos extends AppCompatActivity {


    private TextView textViewId, textViewSitio, textViewDesperfecto, textViewDescripcion;
    private int reclamoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verunreclamo);

        textViewId = findViewById(R.id.textId);
        textViewSitio = findViewById(R.id.textSitio);
        textViewDesperfecto = findViewById(R.id.textDesperfecto);
        textViewDescripcion = findViewById(R.id.textDescripcion);

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
                textViewId.setText("ID: " + reclamo.getIdReclamo());
                textViewSitio.setText("Sitio: " + reclamo.getIdSitio());
                textViewDesperfecto.setText("Desperfecto: " + reclamo.getIdDesperfecto());
                textViewDescripcion.setText("Descripción: " + reclamo.getDescripcion());
            } else {
                Toast.makeText(VerDetalleReclamos.this, "Error al obtener detalles del reclamo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


