package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.security.SecureRandom;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import androidx.appcompat.app.AppCompatActivity;

public class Registrarse extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);

        EditText editTextDNI = findViewById(R.id.Username);
        EditText editTextEmail = findViewById(R.id.Mail);
        Button buttonRegistrar = findViewById(R.id.Registro_button);




        buttonRegistrar.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                String documento = editTextDNI.getText().toString();
                String email = editTextEmail.getText().toString();

                if (documento.isEmpty() || email.isEmpty()) {
                    Toast.makeText(Registrarse.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }


                String contrasenia = generarContrasenaAleatoria(20);
                Usuarios nuevoUsuario = new Usuarios(documento, email, contrasenia);

                new GuardarUsuarioTask().execute(nuevoUsuario);
            }
        });


    }

    private String generarContrasenaAleatoria(int longitud) {
        final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder contrasenia = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            contrasenia.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return contrasenia.toString();
    }

    private class GuardarUsuarioTask extends AsyncTask<Usuarios, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Usuarios... usuarios) {
            Usuarios usuario = usuarios[0];

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<Void> call = apiService.registrarUsuario(usuario);
            try {

                Response<Void> response = call.execute();


                if (response.isSuccessful()) {
                    return true;
                } else {
                    // Ocurrió un error al registrar el usuario
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Registrarse.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Registrarse.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }





}
