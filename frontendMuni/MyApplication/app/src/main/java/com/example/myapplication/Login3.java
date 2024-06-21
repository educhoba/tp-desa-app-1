package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


import androidx.appcompat.app.AppCompatActivity;

public class Login3 extends AppCompatActivity {
    private String codigoEnviado;
    private Usuarios usuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_p3);

        Intent intent = getIntent();
        codigoEnviado = intent.getStringExtra("codigo");
        usuario = (Usuarios) intent.getSerializableExtra("usuario");

        EditText editTextCodigo = findViewById(R.id.Password);
        EditText editTextNuevaContrasenia = findViewById(R.id.Username);
        Button buttonConfirmar = findViewById(R.id.Actualice_contraButton);

        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoIngresado = editTextCodigo.getText().toString();
                String nuevaContrasenia = editTextNuevaContrasenia.getText().toString();

                if (codigoIngresado.isEmpty() || nuevaContrasenia.isEmpty()) {
                    Toast.makeText(Login3.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (codigoIngresado.equals(codigoEnviado)) {
                    usuario.setContrasenia(nuevaContrasenia);
                    new CambiarContraseniaTask().execute(usuario);
                } else {
                    Toast.makeText(Login3.this, "Código incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class CambiarContraseniaTask extends AsyncTask<Usuarios, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Usuarios... usuarios) {
            Usuarios usuario = usuarios[0];

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Usuarios> call = apiService.cambiarContraseniaUser(usuario);
            try {
                Response<Usuarios> response = call.execute();
                return response.isSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Login3.this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login3.this, Login1.class);
                startActivity(intent);
            } else {
                Toast.makeText(Login3.this, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
