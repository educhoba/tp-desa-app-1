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

public class Login2 extends AppCompatActivity {
    private Button btn;
    private EditText contra;
    private String cargo;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_p2);

        contra = findViewById(R.id.Password);
        btn = findViewById(R.id.login2_button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String contrasenia = contra.getText().toString();
                if (contrasenia.isEmpty()) {
                    Toast.makeText(Login2.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                new BuscarContraUserTask().execute(contrasenia);
            }
        });
    }

    private class BuscarContraUserTask extends AsyncTask<String, Void, Boolean> {

        private Usuarios user;
        String inputContrasenia;

        @Override
        protected Boolean doInBackground(String... params) {
            inputContrasenia = params[0];
            String dni = getIntent().getStringExtra("dni");
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Usuarios> response = apiService.buscarUsuario(dni).execute();
                if (response.isSuccessful() && response.body() != null) {
                    user = response.body();
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
                if (user.getContrasenia().equals(inputContrasenia)){
                    cargo="Usuario";
                    Intent intent = new Intent(Login2.this, PerfilUser.class);
                    intent.putExtra("cargo",cargo);
                    intent.putExtra("usuario",user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login2.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                }

            } else {
                new BuscarContraInspectorTask().execute(inputContrasenia);

            }
        }
    }

    private class BuscarContraInspectorTask extends AsyncTask<String, Void, Boolean> {

        private Inspector inspector;
        String inputContrasenia;

        @Override
        protected Boolean doInBackground(String... params) {
            inputContrasenia = params[0];
            String dni = getIntent().getStringExtra("dni");
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Inspector> response = apiService.buscarInspector(dni).execute();
                if (response.isSuccessful() && response.body() != null) {
                    inspector = response.body();
                    return true;
                } else {
                    return false;
                }
            }catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean existe) {
            if (existe) {
                if (inspector.getPassword().equals(inputContrasenia)){
                    cargo="Inspector";
                    Intent intent = new Intent(Login2.this, PerfilInspector.class);
                    intent.putExtra("cargo",cargo);
                    intent.putExtra("usuario",inspector);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login2.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(Login2.this,"DNI no encontrado",Toast.LENGTH_SHORT).show();


            }
        }
    }




}
