package com.example.myapplication.activities;
import com.example.myapplication.ReclamosAdapter;


import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.ServiciosAdapter;
import com.example.myapplication.models.Reclamo;
import com.example.myapplication.models.Servicios;

public class VerReclamos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReclamosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_reclamos);

        recyclerView = findViewById(R.id.recyclerViewReclamos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new listarReclamosTask().execute();
    }

    private class listarReclamosTask extends AsyncTask<Void, Void, List<Reclamo>> {

        @Override
        protected List<Reclamo> doInBackground(Void... voids) {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<Reclamo>> call = apiService.getReclamos();

            try {
                Response<List<Reclamo>> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    Log.e("listarReclamosTask", "Error en la respuesta: " + response.code());
                }
            } catch (IOException e) {
                Log.e("listarReclamosTask", "Error en la llamada: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Reclamo> reclamos) {
            if (reclamos != null) {
                adapter = new ReclamosAdapter(reclamos);
                recyclerView.setAdapter(adapter);
            } else {
                Log.e("listarReclamosTask", "No se recibieron datos de reclamos");
            }
        }
    }
}
