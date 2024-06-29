package com.example.myapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Inspector;
import com.example.myapplication.models.Usuarios;

import java.io.IOException;
import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login2 extends AppCompatActivity {
    private Button btn;
    private Button btnolvido;
    private String contrasenia;
    private EditText contra;
    private String cargo;
    private Usuarios usuario;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_p2);

        contra = findViewById(R.id.Password);
        btn = findViewById(R.id.login2_button);
        btnolvido = findViewById(R.id.olvido_pass);

        btnolvido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dni = getIntent().getStringExtra("dni");
                new BuscarUsuarioTask().execute(dni);
            }

        });

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

    private class BuscarUsuarioTask extends AsyncTask<String, Void, Usuarios> {
        @Override
        protected Usuarios doInBackground(String... params) {
            String dni = params[0];

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                Response<Usuarios> response = apiService.buscarUsuario(dni).execute();
                if (response.isSuccessful() && response.body() != null) {
                    return response.body();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        protected void onPostExecute(Usuarios usuario) {
            if (usuario != null) {
                new EnviarCorreoTask().execute(usuario);
            } else {
                Toast.makeText(Login2.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class EnviarCorreoTask extends AsyncTask<Usuarios, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Usuarios... params) {
            usuario = params[0];
            contrasenia = generarContrasenaAleatoria(20);
            usuario.setContrasenia(contrasenia);

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Usuarios> updateCall = apiService.cambiarContraseniaUser(usuario);
            try {
                Response<Usuarios> updateResponse = updateCall.execute();
                if (!updateResponse.isSuccessful()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            String subject = "Recuperación de contraseña";
            String body = "Por favor, use el siguiente código para restablecer su contraseña: " + contrasenia;

            Call<Void> call = apiService.enviarCorreoVerificacion(usuario.getEmail(), subject, body);
            try {
                Response<Void> response = call.execute();
                return response.isSuccessful();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                mostrarDialogoVerificacion();
            } else {
                Toast.makeText(Login2.this, "Error al enviar el correo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void mostrarDialogoVerificacion() {
        new AlertDialog.Builder(this)
                .setTitle("Actualizar contraseña")
                .setMessage("Por favor, verifique su casilla de correo. Le hemos enviado un código de verificación.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Login2.this, Login3.class);
                        intent.putExtra("codigo",contrasenia);
                        intent.putExtra("usuario",usuario);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
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






}
