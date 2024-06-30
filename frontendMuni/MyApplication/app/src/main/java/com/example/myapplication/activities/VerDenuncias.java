package com.example.myapplication.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ApiService;
import com.example.myapplication.DenunciasAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ReclamosAdapter;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Denuncias;
import com.example.myapplication.models.Reclamos;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class VerDenuncias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DenunciasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_denuncias);


        recyclerView = findViewById(R.id.recyclerViewDenuncias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new listarDenunciasTask().execute();
    }

    private class listarDenunciasTask extends AsyncTask<Void, Void, List<Denuncias>> {

        @Override
        protected List<Denuncias> doInBackground(Void... voids) {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<Denuncias>> call = apiService.getDenuncias();

            try {
                Response<List<Denuncias>> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    Log.e("listarDenunciasTask", "Error en la respuesta: " + response.code());
                }
            } catch (IOException e) {
                Log.e("listarDenunciasTask", "Error en la llamada: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Denuncias> denuncias) {
            if (denuncias != null) {
                adapter = new DenunciasAdapter(denuncias);
                recyclerView.setAdapter(adapter);
            } else {
                Log.e("listarDenunciasTask", "No se recibieron datos de reclamos");
            }
        }
    }
}
