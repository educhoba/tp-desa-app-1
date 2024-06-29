package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.Reclamo;

public class GenerarReclamo extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private EditText comentariosManual;
    private EditText comentariosAuto;
    private EditText rubroManual;
    private EditText desperfectoManual;
    private EditText sitioDireccionManual;
    private RadioGroup radioGroup;
    private LinearLayout sectionAuto;
    private LinearLayout sectionManual;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamos);

        radioGroup = findViewById(R.id.radioGroup);
        sectionAuto = findViewById(R.id.options);
        sectionManual = findViewById(R.id.manual);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        comentariosAuto = findViewById(R.id.comentariosManual);
        comentariosManual = findViewById(R.id.comentariosManual);
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



    }

    private void generarReclamo() {
        // Obtener los datos del formulario según el modo seleccionado
        String modoCarga = obtenerModoCarga();
        String rubro = "";
        String desperfecto = "";
        String sitioDireccion = "";
        String comentarios = "";

        if (modoCarga.equals("opciones")) {
            rubro = spinner1.getSelectedItem().toString();
            desperfecto = spinner2.getSelectedItem().toString();
            sitioDireccion = spinner3.getSelectedItem().toString();
            comentarios = comentariosAuto.getText().toString();
        } else if (modoCarga.equals("manual")) {
            rubro = rubroManual.getText().toString();
            desperfecto = desperfectoManual.getText().toString();
            sitioDireccion = sitioDireccionManual.getText().toString();
            comentarios = comentariosManual.getText().toString();
        }

        // Crear objeto Reclamo
        Reclamo reclamo = new Reclamo();
        reclamo.setDescripcion(comentarios);
        //reclamo.setIdDesperfecto(desperfecto);

        // Aquí debes llamar a tu método Retrofit para enviar el reclamo al backend
        enviarReclamo(reclamo);
    }

    // Método para obtener el modo de carga seleccionado
    private String obtenerModoCarga() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.optionsRadio) {
            return "opciones";
        } else {
            return "manual";
        }
    }

    // Método para enviar el reclamo al backend usando Retrofit
    private void enviarReclamo(Reclamo reclamo) {
        // Aquí deberías implementar la lógica para enviar reclamo mediante Retrofit
        // Por ejemplo, puedes mostrar un Toast indicando que se ha generado el reclamo
        Toast.makeText(this, "Reclamo generado y enviado al servidor", Toast.LENGTH_SHORT).show();
    }
}
