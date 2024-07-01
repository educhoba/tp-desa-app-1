package com.example.myapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.AsyncTask;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Inspector;
import com.example.myapplication.models.Usuarios;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class configuracion extends AppCompatActivity {
    private ImageView btn_back;
    private Usuarios usuario;
    private Inspector inspector;
    private String cargo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_config);
        cargo = getIntent().getStringExtra("cargo");

        EditText editTextActualPassword = findViewById(R.id.contrasenia_ant);
        EditText editTextNewPassword = findViewById(R.id.contrasenia_nueva);
        Button buttonChangePassword = findViewById(R.id.Registro_button);
        LinearLayout cerrarSesion = findViewById(R.id.cerrarSesion);

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actualPassword = editTextActualPassword.getText().toString();
                String newPassword = editTextNewPassword.getText().toString();
                if (cargo.equals("Usuario")){
                    usuario = (Usuarios) getIntent().getSerializableExtra("usuario");
                }else{
                    inspector = (Inspector) getIntent().getSerializableExtra("usuario");
                }

                if (actualPassword.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(configuracion.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cargo.equals("Usuario")) {
                    new CambiarContraseniaTask(actualPassword, newPassword).execute(usuario);
                } else {
                    new CambiarContraseniaTask(actualPassword, newPassword).execute(inspector);
                }
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(configuracion.this, MainActivity.class);
                intent.removeExtra("cargo");
                intent.removeExtra("usuario");
                startActivity(intent);

            }
        });
    }
    private class CambiarContraseniaTask extends AsyncTask<Object, Void, Boolean> {
        private String actualPassword;
        private String newPassword;

        public CambiarContraseniaTask(String actualPassword, String newPassword) {
            this.actualPassword = actualPassword;
            this.newPassword = newPassword;
        }

        protected Boolean doInBackground(Object... params) {
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            try {
                if (params[0] instanceof Usuarios) {
                    Usuarios usuario = (Usuarios) params[0];
                    if (!actualPassword.equals(usuario.getContrasenia())) {
                        return false;
                    }
                    usuario.setContrasenia(newPassword);
                    Call<Usuarios> call = apiService.cambiarContraseniaUser(usuario);
                    Response<Usuarios> response = call.execute();
                    return response.isSuccessful();
                } else if (params[0] instanceof Inspector) {
                    Inspector inspector = (Inspector) params[0];
                    if (!actualPassword.equals(inspector.getPassword())) {
                        return false;
                    }
                    inspector.setPassword(newPassword);
                    Call<Inspector> call = apiService.cambiarContraseniaInspec(inspector);
                    Response<Inspector> response = call.execute();
                    return response.isSuccessful();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(configuracion.this, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(configuracion.this, Login1.class);
                startActivity(intent);
            } else {
                Toast.makeText(configuracion.this, "Error al cambiar contraseña", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
