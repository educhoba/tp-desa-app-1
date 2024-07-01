package com.example.myapplication.activities;
import com.example.myapplication.ReclamosAdapter;


import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Inspector;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.Usuarios;

import org.w3c.dom.Text;

public class VerReclamos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReclamosAdapter adapter;
    String cargo;
    Usuarios usuario;
    Inspector inspector;
    TextView textNombreUsuario;

    CheckBox checkFiltrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_reclamos);

        recyclerView = findViewById(R.id.recyclerViewReclamos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cargo = getIntent().getStringExtra("cargo");
        checkFiltrar = findViewById(R.id.checkFiltrar);
        textNombreUsuario = findViewById(R.id.nombre_usuario);

        if (cargo.equals("Usuario")){
            usuario =  (Usuarios) getIntent().getSerializableExtra("usuario");
            checkFiltrar.setVisibility(RecyclerView.VISIBLE);
            checkFiltrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkFiltrar.setSelected(isChecked);
                    new listarReclamosTask().execute();
                }
            });

        }else{
            inspector =  (Inspector) getIntent().getSerializableExtra("usuario");
            textNombreUsuario.setText("Inspector");
        }

        new listarReclamosTask().execute();
    }

    private class listarReclamosTask extends AsyncTask<Void, Void, List<Reclamos>> {

        @Override
        protected List<Reclamos> doInBackground(Void... voids) {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<List<Reclamos>> call = null;

            if (cargo.equals("Usuario")){
                if(checkFiltrar.isChecked()){
                    call= apiService.getReclamos();
                }
                else{
                    call= apiService.listarReclamosPorDocumento(usuario.getDocumento());
                }
            }else{
                call= apiService.listarReclamosPorLegajo(inspector.getLegajo());
            }

            try {
                Response<List<Reclamos>> response = call.execute();
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
        protected void onPostExecute(List<Reclamos> reclamos) {
            if (reclamos != null) {
                adapter = new ReclamosAdapter(reclamos);
                recyclerView.setAdapter(adapter);
            } else {
                Log.e("listarReclamosTask", "No se recibieron datos de reclamos");
            }
        }
    }
}
