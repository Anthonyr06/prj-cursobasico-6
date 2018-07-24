package com.example.anthony.idealw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class wrist extends AppCompatActivity implements View.OnClickListener {

    EditText edit;
    Spinner spn;
    String spnSelected;

    char sex;
    double height, weight, wrist;
    String heightMeasure, weightMeasure, wristMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrist);

        findViewById(R.id.btnWrist).setOnClickListener(this);
        spn = findViewById(R.id.spnWrist);
        edit = findViewById(R.id.editTxtWrist);

        String[] measure = {"cm","in"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinners_layout, measure);
        spn.setAdapter(adapter);

        spn.setSelection(0);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                spnSelected = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }

        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public void onClick(View view) {
        if(edit.getText().toString().isEmpty() || edit.getText().toString().equals(".")){

            Toast.makeText(this, "Introduzca su medida", Toast.LENGTH_SHORT)
                    .show();

        }else {

            double wrist = Double.parseDouble(edit.getText().toString());

            char sex = getIntent().getCharExtra("sex", 'm');
            double height = getIntent().getDoubleExtra("height",0);
            String heightMeasure = getIntent().getStringExtra("heightMeasure");
            double weight = getIntent().getDoubleExtra("weight",0);
            String weightMeasure = getIntent().getStringExtra("weightMeasure");

            Intent intentR = new Intent(this, Result.class);
            intentR.putExtra("sex",sex);
            intentR.putExtra("height", height);
            intentR.putExtra("heightMeasure", heightMeasure);
            intentR.putExtra("weight", weight);
            intentR.putExtra("weightMeasure", weightMeasure);
            intentR.putExtra("wrist",wrist);
            intentR.putExtra("wristMeasure", spnSelected);
            startActivity(intentR);
            finish();

            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    }
}
