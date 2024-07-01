package com.example.myapplication.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
import com.example.myapplication.models.Usuarios;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class VerDenuncias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DenunciasAdapter adapter;
    private CheckBox checkFiltrar;
    Usuarios user;
    String dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_denuncias);


        recyclerView = findViewById(R.id.recyclerViewDenuncias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user =  (Usuarios) getIntent().getSerializableExtra("usuario");
        dni = user.getDocumento();

        checkFiltrar = findViewById(R.id.checkFiltrar);


        checkFiltrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkFiltrar.setSelected(isChecked);
                new listarDenunciasTask().execute();
            }
        });

        new listarDenunciasTask().execute();
    }

    private class listarDenunciasTask extends AsyncTask<Void, Void, List<Denuncias>> {

        @Override
        protected List<Denuncias> doInBackground(Void... voids) {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<Denuncias>> call = null;

            if(checkFiltrar.isChecked()){
                call= apiService.getDenuncias();
            }
            else{
                call= apiService.listarDenunciasPorDocumento(dni);
            }

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
                adapter = new DenunciasAdapter(denuncias,dni);
                recyclerView.setAdapter(adapter);
            } else {
                Log.e("listarDenunciasTask", "No se recibieron datos de reclamos");
            }
        }
    }
}
