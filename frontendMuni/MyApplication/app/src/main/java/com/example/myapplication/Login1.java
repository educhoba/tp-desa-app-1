package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login1 extends AppCompatActivity {

    private Button buttonIngresar;
    private Button buttonRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_p1);


        EditText editTextDNI = findViewById(R.id.Username);
        buttonIngresar = findViewById(R.id.login1Button);
        buttonRegistrarse = findViewById(R.id.registrarButton);

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login1.this, Registrarse.class);
                startActivity(intent);
            }
        });

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String documento = editTextDNI.getText().toString();
                if (documento.isEmpty()) {
                    Toast.makeText(Login1.this, "Ingrese su DNI", Toast.LENGTH_SHORT).show();
                    return;
                }

                new BuscarDniTask().execute(documento);
            }
        });
    }

    private class BuscarDniTask extends AsyncTask<String, Void, Boolean> {
        private Vecinos vecino;
        EditText editTextDNI = findViewById(R.id.Username);
        String documento = editTextDNI.getText().toString();
        @Override
        protected Boolean doInBackground(String... params) {
            String documento = params[0];
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Vecinos> response = apiService.buscarDNI(documento).execute();
                if (response.isSuccessful() && response.body() != null) {
                    vecino = response.body();
                    return true;

                }
                else {
                    throw new IOException("Error de servidor");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean existe) {
            if (existe) {
                new VerificarUserTask().execute(documento);
            }else{
                new VerificarInspectorTask().execute(documento);
            }

        }


    }

    private class VerificarUserTask extends AsyncTask<String, Void, Boolean> {
        private Usuarios usuario;

        @Override
        protected Boolean doInBackground(String... params) {
            String documento = params[0];
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Usuarios> response = apiService.buscarUsuario(documento).execute();
                if (response.isSuccessful() && response.body() != null) {
                    usuario = response.body();
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean existe) {
            if (existe) {
                if (usuario.getContrasenia() == null) {
                    Intent intent = new Intent(Login1.this, Registrarse.class);
                    startActivity(intent);
                }else{
                    String dni = usuario.getDocumento();
                    Intent intent = new Intent(Login1.this, Login2.class);
                    intent.putExtra("dni",dni);
                    startActivity(intent);
                }

            } else {
                Intent intent = new Intent(Login1.this, Registrarse.class);
                startActivity(intent);
            }
        }

    }

    private class VerificarInspectorTask extends AsyncTask<String, Void, Boolean> {
        private Inspector inspector;

        @Override
        protected Boolean doInBackground(String... params) {
            String documento = params[0];
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Inspector> response = apiService.buscarInspector(documento).execute();
                if (response.isSuccessful() && response.body() != null) {
                    inspector = response.body();
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean existe) {
            if (existe) {
                String documento = inspector.getDocumento();
                Intent intent = new Intent(Login1.this, Login2.class);
                intent.putExtra("dni",documento);
                startActivity(intent);

            } else {
                Toast.makeText(Login1.this, "DNI no encontrado", Toast.LENGTH_SHORT).show();

            }
        }

    }

}

