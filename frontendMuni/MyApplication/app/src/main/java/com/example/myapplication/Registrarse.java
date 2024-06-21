package com.example.myapplication;

import android.content.Intent;
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
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;


import androidx.appcompat.app.AppCompatActivity;

public class Registrarse extends AppCompatActivity {

    private String contrasenia;
    private Usuarios usuario;

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


                contrasenia = generarContrasenaAleatoria(20);
                usuario = new Usuarios(documento, email, contrasenia);

                new GuardarUsuarioTask().execute(usuario);
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
            String codigo = usuario.getContrasenia();

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registrarUsuario(usuario);
            try {

                Response<Void> response = call.execute();

                if (response.isSuccessful()) {
                    String subject = "Verificación de correo";
                    String body = "Por favor, use el siguiente código para verificar su cuenta:"+codigo;
                    Response<Void> emailResponse = apiService.enviarCorreoVerificacion(usuario.getEmail(), subject, body).execute();
                    if (emailResponse.isSuccessful()) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
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
                mostrarDialogoVerificacion();
            } else {
                Toast.makeText(Registrarse.this, "Error de envio", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void mostrarDialogoVerificacion() {
        new AlertDialog.Builder(this)
                .setTitle("Verificación de correo")
                .setMessage("Por favor, verifique su casilla de correo. Le hemos enviado un código de verificación.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirigir a la actividad de ingreso del código de verificación
                        Intent intent = new Intent(Registrarse.this, Login3.class);
                        intent.putExtra("codigo",contrasenia);
                        intent.putExtra("usuario",usuario);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }







}
