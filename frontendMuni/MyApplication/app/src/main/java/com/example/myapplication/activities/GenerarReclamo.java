package com.example.myapplication.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Desperfectos;
import com.example.myapplication.models.Imagenes;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.Sitios;
import com.example.myapplication.models.SitiosManuales;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GenerarReclamo extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    //private EditText comentariosManual;
    private EditText comentariosAuto;
    //private EditText rubroManual;
    //private EditText desperfectoManual;
    private EditText sitioDireccionManual;
    private RadioGroup radioGroup;
    private LinearLayout sectionAuto;
    private LinearLayout sectionManual;
    private List<Imagenes> listaImagenesBase64 = new ArrayList<>();
    private static final int REQUEST_CODE_SELECT_IMAGES = 7;

    private Integer SitioSeleccionado = 0;
    private Integer DesperfectoSeleccionado = 0;
    private Integer SitioManualIdHardcodeado = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamos);

        radioGroup = findViewById(R.id.radioGroup);
        sectionAuto = findViewById(R.id.options);
        sectionManual = findViewById(R.id.manual);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        //spinner3 = findViewById(R.id.spinner3);

        comentariosAuto = findViewById(R.id.comentariosAuto);
        //comentariosManual = findViewById(R.id.comentariosManual);
        sitioDireccionManual = findViewById(R.id.sitioDireccionManual);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.optionsRadio) {
                    sectionAuto.setVisibility(View.VISIBLE);
                    sectionManual.setVisibility(View.GONE);
                } else if (checkedId == R.id.manualRadio) {
                    sectionAuto.setVisibility(View.GONE);
                    sectionManual.setVisibility(View.VISIBLE);
                }
            }
        });

        Button generarButton = findViewById(R.id.btnGenerar);
        generarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarReclamo();
            }
        });
        Button buttonImage = findViewById(R.id.buttonImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagenes();
            }
        });

        new ObtenerSitiosUnicosTask().execute();
        new ObtenerDesperfectosUnicosTask().execute();
    }

    private void generarReclamo() {
            // Obtener los datos del formulario según el modo seleccionado
            String modoCarga = obtenerModoCarga();
            Integer desperfectoId = -1;
            Integer sitioId = -1;
            String sitioDireccion = "";
            String comentarios = "";

            // Crear objeto Reclamo
            Reclamos reclamo = new Reclamos();
            reclamo.setDocumento("DNI28000046"); //TODO PONER EL DEL USUARIO
            if (modoCarga.equals("opciones")) {
                if(SitioSeleccionado < 1){
                    Toast.makeText(GenerarReclamo.this, "Seleccione un sitio.", Toast.LENGTH_SHORT).show();
                    return;
                }
                reclamo.setIdSitio(SitioSeleccionado);

            } else if (modoCarga.equals("manual")) {
                sitioDireccion = sitioDireccionManual.getText().toString();
                SitiosManuales sm = new SitiosManuales();
                sm.setDescripcion(sitioDireccion);
                List<SitiosManuales> sml = new ArrayList<>();
                reclamo.setIdSitio(1);//TODO: Deshardcodear
                sml.add(sm);
                reclamo.setSitiosManuales(sml);
            }
            else{
                Toast.makeText(GenerarReclamo.this, "Seleccione un modo", Toast.LENGTH_SHORT).show();
                return;
            }

            if(DesperfectoSeleccionado < 1){
                Toast.makeText(GenerarReclamo.this, "Seleccione un desperfecto.", Toast.LENGTH_SHORT).show();
                return;
            }

            reclamo.setIdDesperfecto(DesperfectoSeleccionado);

            comentarios = comentariosAuto.getText().toString();

            reclamo.setDescripcion(comentarios);

            reclamo.setImagenes(listaImagenesBase64);

            new RegistrarReclamoTask().execute(reclamo);
    }

    // Método para obtener el modo de carga seleccionado
    private String obtenerModoCarga() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.optionsRadio) {
            return "opciones";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.manualRadio){
            return "manual";
        }
        else{
            return "";
        }
    }


    private class ObtenerSitiosUnicosTask extends AsyncTask<String, Void, List<Sitios>> {
        @Override
        protected List<Sitios> doInBackground(String... params) {

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<List<Sitios>> call = apiService.listarSitios();

            try {
                Response<List<Sitios>> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<Sitios> body = response.body();

                    return new ArrayList<>(body);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Sitios> sitios) {
            if (sitios != null) {
                sitios.removeIf(e -> e.getIdSitio().equals(SitioManualIdHardcodeado));
                configurarSpinnerSitios(sitios);
            } else {
                Toast.makeText(GenerarReclamo.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void configurarSpinnerSitios(List<Sitios> sitios) {
        // Crear una lista de descripciones para el spinner
        List<String> list = new ArrayList<>();
        list.add(0, "Elija el sitio"); // Agregar primer elemento como opción por defecto

        // Iterar sobre la lista de Sitios y añadir las descripciones al list
        for (Sitios s : sitios) {
            list.add(s.getDescripcion());
        }

        // Crear un ArrayAdapter personalizado para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0; // Permitir seleccionar todos los elementos menos el primero (índice 0)
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;

                // Cambiar el color del texto del primer elemento (opción por defecto)
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }

                return view;
            }
        };

        // Especificar el layout para las opciones desplegables
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinner1.setAdapter(adapter);

        // Manejar la selección del Spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Sitios selectedSitio = sitios.get(position - 1); // Obtener el Sitios seleccionado
                    SitioSeleccionado = selectedSitio.getIdSitio(); // Obtener el ID del Sitios seleccionado

                    // Aquí puedes realizar cualquier acción con el Sitios seleccionado

                    //Toast.makeText(GenerarReclamo.this, "ID Sitios seleccionado: " + SitioSeleccionado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Método requerido pero puedes dejarlo vacío si no necesitas hacer nada específico aquí
            }
        });
    }

    private class ObtenerDesperfectosUnicosTask extends AsyncTask<String, Void, List<Desperfectos>> {
        @Override
        protected List<Desperfectos> doInBackground(String... params) {

            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<List<Desperfectos>> call = apiService.listarDesperfectos();

            try {
                Response<List<Desperfectos>> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<Desperfectos> body = response.body();

                    return new ArrayList<>(body);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Desperfectos> desperfectos) {
            if (desperfectos != null) {
                configurarSpinnerDesperfectos(desperfectos);
            } else {
                Toast.makeText(GenerarReclamo.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void configurarSpinnerDesperfectos(List<Desperfectos> desperfectos) {
        // Crear una lista de descripciones para el spinner
        List<String> list = new ArrayList<>();
        list.add(0, "Elija el desperfecto"); // Agregar primer elemento como opción por defecto

        // Iterar sobre la lista de Sitios y añadir las descripciones al list
        for (Desperfectos s : desperfectos) {
            list.add(s.getDescripcion());
        }

        // Crear un ArrayAdapter personalizado para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0; // Permitir seleccionar todos los elementos menos el primero (índice 0)
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;

                // Cambiar el color del texto del primer elemento (opción por defecto)
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                } else {
                    textView.setTextColor(Color.BLACK);
                }

                return view;
            }
        };

        // Especificar el layout para las opciones desplegables
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinner2.setAdapter(adapter);

        // Manejar la selección del Spinner
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Desperfectos selectedDesperfecto = desperfectos.get(position - 1); // Obtener el Sitios seleccionado
                    DesperfectoSeleccionado = selectedDesperfecto.getIdDesperfecto(); // Obtener el ID del Sitios seleccionado

                    // Aquí puedes realizar cualquier acción con el Sitios seleccionado

                    //Toast.makeText(GenerarReclamo.this, "ID Desperfecto seleccionado: " + DesperfectoSeleccionado, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Método requerido pero puedes dejarlo vacío si no necesitas hacer nada específico aquí
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
                    processImageUri(imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                processImageUri(imageUri);
            }
        }
    }
    private void processImageUri(Uri imageUri) {
        try {
            InputStream imageStream = getContentResolver().openInputStream(imageUri);
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            // Comprimir la imagen y convertirla a Base64
            String imageBase64 = compressAndEncodeImage(selectedImage);

            // Crear un objeto Imagenes y agregarlo a la lista
            Imagenes imagen = new Imagenes();
            imagen.setData(imageBase64);
            listaImagenesBase64.add(imagen);

            Toast.makeText(this, "Imagen añadida", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al seleccionar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para comprimir y convertir la imagen a Base64
    private String compressAndEncodeImage(Bitmap bitmap) {
        Bitmap resizedBitmap = resizeBitmap(bitmap, 800, 600); // Redimensionar según sea necesario

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream); // Comprimir la imagen en formato PNG

        byte[] byteArray = stream.toByteArray();
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);
    }

    // Método para redimensionar la imagen
    private Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        float ratio = Math.min(
                (float) maxWidth / bitmap.getWidth(),
                (float) maxHeight / bitmap.getHeight());
        int width = Math.round((float) ratio * bitmap.getWidth());
        int height = Math.round((float) ratio * bitmap.getHeight());

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    private class RegistrarReclamoTask extends AsyncTask<Reclamos, Void, Void> {

        @Override
        protected Void doInBackground(Reclamos... reclamos) {
            Reclamos reclamo = reclamos[0];

            // Realizar la llamada Retrofit para registrar el servicio
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registrarReclamo(reclamo);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GenerarReclamo.this, "Servicio registrado correctamente", Toast.LENGTH_SHORT).show();
                                limpiarFormulario();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GenerarReclamo.this, "Error al registrar el servicio", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(GenerarReclamo.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return null;
        }
    }
    private void limpiarFormulario() {
        comentariosAuto.setText("");
        spinner1.setSelection(0);
        spinner2.setSelection(0);
        sitioDireccionManual.setText("");
        radioGroup.clearCheck();
        listaImagenesBase64.clear();
    }

}
