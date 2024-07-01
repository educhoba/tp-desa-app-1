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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiService;
import com.example.myapplication.R;
import com.example.myapplication.RetrofitClient;
import com.example.myapplication.models.Denuncias;
import com.example.myapplication.models.Desperfectos;
import com.example.myapplication.models.Imagenes;
import com.example.myapplication.models.Reclamos;
import com.example.myapplication.models.Sitios;
import com.example.myapplication.models.SitiosManuales;
import com.example.myapplication.models.Usuarios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GenerarDenuncia extends AppCompatActivity {

    private Spinner spinnerSitioComercioDenunciado;

    private EditText descripcionDenunciaText;
    private EditText documentoVecinoDenunciadoText;
    //private EditText nombreComercioDenunciadoText;
    private Integer SitioManualIdHardcodeado = 1;
    private CheckBox checkAcepto;

    private RadioGroup radioGroup;
    private LinearLayout sectionVecino;
    private LinearLayout sectionComercio;
    private List<Imagenes> listaImagenesBase64 = new ArrayList<>();
    private static final int REQUEST_CODE_SELECT_IMAGES = 19;//dice que no hay limite pero 20 es un monton

    private Integer SitioSeleccionado = 0;
    Usuarios user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generar_denuncia);

        radioGroup = findViewById(R.id.radioGroup);
        sectionVecino = findViewById(R.id.vecino);
        sectionComercio = findViewById(R.id.comercio);

        spinnerSitioComercioDenunciado = findViewById(R.id.spinner1);

        checkAcepto = findViewById(R.id.checkAcepto);

        descripcionDenunciaText = findViewById(R.id.descripcionDenuncia);
        //nombreComercioDenunciadoText = findViewById(R.id.nombrecom);
        documentoVecinoDenunciadoText = findViewById(R.id.docveci);
        user =  (Usuarios) getIntent().getSerializableExtra("usuario");




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.optionsVecino) {
                    sectionVecino.setVisibility(View.VISIBLE);
                    sectionComercio.setVisibility(View.GONE);
                } else if (checkedId == R.id.optionsComercio) {
                    sectionVecino.setVisibility(View.GONE);
                    sectionComercio.setVisibility(View.VISIBLE);
                }
            }
        });

        Button generarButton = findViewById(R.id.btnGenerar);
        generarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarDenuncia();
            }
        });
        Button buttonImage = findViewById(R.id.buttonImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagenes();
            }
        });
        checkAcepto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkAcepto.setSelected(isChecked);
            }
        });

        new ObtenerSitiosUnicosTask().execute();
    }

    private void generarDenuncia() {
        if(!checkAcepto.isSelected()){
            //chequear algo
            Toast.makeText(GenerarDenuncia.this, "Debe aceptar los TyC", Toast.LENGTH_SHORT).show();
            return;
        }

            // Obtener los datos del formulario según el modo seleccionado
            String tipoDenuncia = obtenerTipoDenuncia();

            // Crear objeto Denuncias
            Denuncias denuncia = new Denuncias();
            denuncia.setAceptaResponsabilidad(1);//(true)
            denuncia.setDocumento(user.getDocumento()); //TODO PONER EL DEL USUARIO

            if (tipoDenuncia.equals("vecino")) {
                if(documentoVecinoDenunciadoText.getText().toString().isEmpty()){
                    //chequear algo
                    Toast.makeText(GenerarDenuncia.this, "Falta el documento.", Toast.LENGTH_SHORT).show();
                    return;
                }
                denuncia.setDenunciado(documentoVecinoDenunciadoText.getText().toString());
            } else if (tipoDenuncia.equals("comercio")) {

                if(SitioSeleccionado < 1){
                    //chequear algo
                    Toast.makeText(GenerarDenuncia.this, "Seleccione un sitio.", Toast.LENGTH_SHORT).show();
                    return;
                }
                denuncia.setIdSitio(SitioSeleccionado);
            }
            else{
                Toast.makeText(GenerarDenuncia.this, "Seleccione un tipo", Toast.LENGTH_SHORT).show();
                return;
            }

            denuncia.setDescripcion(descripcionDenunciaText.getText().toString());
            denuncia.setImagenes(listaImagenesBase64);


            new RegistrarDenunciaTask().execute(denuncia);
    }


    private String obtenerTipoDenuncia() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.optionsComercio) {
            //toast("comercio");
            return "comercio";
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.optionsVecino){
            //toast("vecino");
            return "vecino";
        }
        else{//toast("nada");
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
                Toast.makeText(GenerarDenuncia.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
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
        spinnerSitioComercioDenunciado.setAdapter(adapter);

        // Manejar la selección del Spinner
        spinnerSitioComercioDenunciado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private class RegistrarDenunciaTask extends AsyncTask<Denuncias, Void, Void> {

        @Override
        protected Void doInBackground(Denuncias... denuncias) {
            Denuncias denuncia = denuncias[0];

            // Realizar la llamada Retrofit para registrar el servicio
            Retrofit retrofit = RetrofitClient.getClient();
            ApiService apiService = retrofit.create(ApiService.class);

            Call<Void> call = apiService.registrarDenuncia(denuncia);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GenerarDenuncia.this, "" +
                                        "Denuncia registrada correctamente", Toast.LENGTH_SHORT).show();
                                limpiarFormulario();

                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                String tipoDenuncia = obtenerTipoDenuncia();
                                if (tipoDenuncia.equals("vecino")) {
                                    Toast.makeText(GenerarDenuncia.this, "Error al registrar denuncia. Verifique que el documento sea de un vecino.", Toast.LENGTH_SHORT).show();

                                } else if (tipoDenuncia.equals("comercio")) {
                                    Toast.makeText(GenerarDenuncia.this, "Error al registrar denuncia", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(GenerarDenuncia.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return null;
        }
    }
    private void limpiarFormulario() {
        documentoVecinoDenunciadoText.setText("");
        descripcionDenunciaText.setText("");
        //nombreComercioDenunciadoText.setText("");
        spinnerSitioComercioDenunciado.setSelection(0);
        radioGroup.clearCheck();
        listaImagenesBase64.clear();
    }
    private void toast(String text){
        Toast.makeText(GenerarDenuncia.this, text, Toast.LENGTH_SHORT).show();

    }
}
