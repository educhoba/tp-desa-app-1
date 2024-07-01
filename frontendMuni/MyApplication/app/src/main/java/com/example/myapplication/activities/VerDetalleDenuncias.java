package com.example.myapplication.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.ApiService;
import com.example.myapplication.ImagePagerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Denuncias;
import com.example.myapplication.models.Reclamos;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Response;

public class VerDetalleDenuncias extends AppCompatActivity {


    private TextView textViewId, textViewSitio, textViewDescripcion, textViewEstado,textViewMovimientos;
    private int denunciaId;
    ViewPager viewPager;
    TabLayout tabLayout;
    String dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verunadenuncia);

        textViewId = findViewById(R.id.textId);
        textViewSitio = findViewById(R.id.textSitio);
        textViewEstado = findViewById(R.id.textEstado);
        textViewDescripcion = findViewById(R.id.textDescripcion);
        textViewMovimientos = findViewById(R.id.textMovimientos);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Obtener el ID de la denuncia del Intent
        denunciaId = getIntent().getIntExtra("DENUNCIA_ID", -1);
        //Obtener el dni de la persona logueada
        dni = getIntent().getStringExtra("dnilogueado");

        // Llamar al método para obtener los detalles de la denuncia
        if (denunciaId != -1) {
            new FetchDenunciaDetailsTask().execute(denunciaId);
        }

    }


    private class FetchDenunciaDetailsTask extends AsyncTask<Integer, Void, Denuncias> {
        @Override
        protected Denuncias doInBackground(Integer... params) {
            int id = params[0];
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Denuncias> call = apiService.getDenunciaById(id);

            try {
                Response<Denuncias> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Denuncias denuncia) {
            if (denuncia != null) {
                if (dni.equals(denuncia.getDocumento())){
                    textViewId.setText("Nro de denuncia: " + denuncia.getIdDenuncias());
                    textViewSitio.setText("Sitio: " + denuncia.getIdSitio());
                    textViewDescripcion.setText("Descripción: " + denuncia.getDescripcion());
                    textViewMovimientos.setText("Movimientos: " + denuncia.getMovimientos());
                    textViewEstado.setText("Estado: " + denuncia.getEstado());
                    ImagePagerAdapter adapter = new ImagePagerAdapter(denuncia.getImagenes());
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager, true);
                } else {
                    textViewId.setText("Nro de denuncia: " + denuncia.getIdDenuncias());
                    textViewSitio.setText("Sitio: " + denuncia.getIdSitio());
                    textViewDescripcion.setText("Descripción: " + denuncia.getDescripcion());
                    textViewMovimientos.setText("Movimientos: " + denuncia.getMovimientos());
                    ImagePagerAdapter adapter = new ImagePagerAdapter(denuncia.getImagenes());
                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager, true);
                }

            } else {
                Toast.makeText(VerDetalleDenuncias.this, "Error al obtener detalles del reclamo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


