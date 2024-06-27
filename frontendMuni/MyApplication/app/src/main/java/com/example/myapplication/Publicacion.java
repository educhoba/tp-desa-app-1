package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;
import androidx.annotation.Nullable;
import retrofit2.Retrofit;


public class Publicacion extends AppCompatActivity {

    private EditText editTextNombre, editTextDireccion, editTextHorario, editTextTelefono, editTextRubro, editTextDescripcion;
    private RadioGroup radioGroup;
    private Button buttonGenerar, buttonImage;

    private List<Imagenes> listaImagenes = new ArrayList<>();
    private static final int REQUEST_CODE_SELECT_IMAGES = 5;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicaciones);

        editTextNombre = findViewById(R.id.Nombre);
        editTextDireccion = findViewById(R.id.Direccion);
        editTextHorario = findViewById(R.id.horario);
        editTextTelefono = findViewById(R.id.telefono);
        editTextRubro = findViewById(R.id.rubro);
        editTextDescripcion = findViewById(R.id.descripcion);
        radioGroup = findViewById(R.id.radioGroup);
        buttonGenerar = findViewById(R.id.buttonGenerar);
        buttonImage = findViewById(R.id.button_image);


        buttonGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarPublicacion();
            }
        });

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagenes();
            }
        });


    }

    private void seleccionarImagenes() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGES);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_IMAGES && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    Imagenes imagen = new Imagenes();
                    imagen.setData(imageUri.toString());
                    listaImagenes.add(imagen);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                Imagenes imagen = new Imagenes();
                imagen.setData(imageUri.toString());
                listaImagenes.add(imagen);
            }
        }
    }

    private void generarPublicacion () {
        // Obtener datos del formulario
        String nombre = editTextNombre.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String horario = editTextHorario.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String rubro = editTextRubro.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String tipo = obtenerTipoSeleccionado();

        // Validar que todos los campos estén completos
        if (nombre.isEmpty() || direccion.isEmpty() || horario.isEmpty() || telefono.isEmpty() || rubro.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
            return;
        }

        Servicios servicio = new Servicios();
        servicio.setTipo(tipo);
        servicio.setNombre(nombre);
        servicio.setDireccion(direccion);
        servicio.setHorario(horario);
        servicio.setTelefono(telefono);
        servicio.setRubro(rubro);
        servicio.setDescripcion(descripcion);
        servicio.setImagenes(listaImagenes);

        // Ejecutar AsyncTask para realizar la solicitud de registro
        new RegistrarServicioTask().execute(servicio);

    }

    private String obtenerTipoSeleccionado() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        if (radioButton != null) {
            return radioButton.getText().toString();
        }
        return "";
    }


    private class RegistrarServicioTask extends AsyncTask<Servicios, Void, Void> {

        @Override
        protected Void doInBackground(Servicios... servicios) {
            Servicios servicio = servicios[0];

            // Realizar la llamada Retrofit para registrar el servicio
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registrarServicio(servicio);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Manejar la respuesta exitosa
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Publicacion.this, "Servicio registrado correctamente", Toast.LENGTH_SHORT).show();
                                limpiarFormulario();
                            }
                        });
                    } else {
                        // Manejar errores en la respuesta
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Publicacion.this, "Error al registrar el servicio", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Manejar fallos en la solicitud
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Publicacion.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return null;
        }
    }

    private void limpiarFormulario() {
        editTextNombre.setText("");
        editTextDireccion.setText("");
        editTextHorario.setText("");
        editTextTelefono.setText("");
        editTextRubro.setText("");
        editTextDescripcion.setText("");
        radioGroup.clearCheck();
        listaImagenes.clear();
    }

}

