package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;


import androidx.appcompat.app.AppCompatActivity;

public class GenerarReclamo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamos);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        LinearLayout sectionAuto = findViewById(R.id.options);
        LinearLayout sectionManual = findViewById(R.id.manual);

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



    }
}
