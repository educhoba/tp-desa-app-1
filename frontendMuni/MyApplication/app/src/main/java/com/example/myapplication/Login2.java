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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_p2);

        EditText contra = findViewById(R.id.Password);
        btn = findViewById(R.id.login2_button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String contrasenia = contra.getText().toString();
                if (contrasenia.isEmpty()) {
                    Toast.makeText(Login2.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                new BuscarContraTask().execute(contrasenia);
            }
        });
    }

    // AsyncTask para realizar alguna tarea en segundo plano
    private class BuscarContraTask extends AsyncTask<String, Void, Boolean> {

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
                if (user.getContrasenia().equals(inputContrasenia)){
                    Intent intent = new Intent(Login2.this, PerfilUser.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login2.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(Login2.this, "Error de Servidor", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
