package com.example.anthony.idealw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.btnResult).setOnClickListener(this);

        TextView txtSex = findViewById(R.id.txtViewResult1);
        TextView txtHeight = findViewById(R.id.txtViewResult2);
        TextView txtWeight = findViewById(R.id.txtViewResult3);
        TextView txtImc = findViewById(R.id.txtViewResult4);
        TextView principalResultWeight = findViewById(R.id.txtViewResult5);

        char sex = getIntent().getCharExtra("sex", 'm');
        double height = getIntent().getDoubleExtra("Height",0);
        String heightMeasure = getIntent().getStringExtra("HeightMeasure");
        double weight = getIntent().getDoubleExtra("Weight",0);
        String weightMeasure = getIntent().getStringExtra("WeightMeasure");

        if(heightMeasure.equals("cm")){
            height = height / 100;
        }

        if(weightMeasure.equals("lb")){
            weight = weight / 2.2046;
        }

        double imc = weight/(height*height);

        DecimalFormat df = new DecimalFormat("#.00");
        String prw;

        if (sex == 'f'){
            txtSex.setText(getString(R.string.resultSex , "Femenino"));
        }else {
            txtSex.setText(getString(R.string.resultSex , "Masculino"));
        }

        if(imc<18.5){
            prw = "Peso insuficiente";
        }else if(imc>18.5 & imc<24.9){
            prw = "Peso normal(ideal)";
        }else if(imc>25 & imc<26.9){
            prw = "Puede haber sobrepeso grado I";
        }else if(imc>27 & imc<29.9){
            prw = "Sobrepeso tipo I (preobesidad)";
        }else if(imc>30 & imc<34.9){
            prw = "Obesidad tipo I (leve)";
        }else if(imc>35 & imc<39.9){
            prw = "Obesidad tipo II (moderada)";
        }else if(imc>40 & imc<49.9){
            prw = "Obesidad tipo III (mórbida)";
        }else{
            prw = "Obesidad extrema";
        }

        txtHeight.setText(getString(R.string.resultHeight, df.format(height), heightMeasure));
        txtWeight.setText(getString(R.string.resultWeight, df.format(weight), weightMeasure));
        txtImc.setText(getString(R.string.resultImc, df.format(imc)));
        principalResultWeight.setText(getString(R.string.principalResultWeight, prw));
    }

    @Override
    public void onClick(View view) {
        Intent intentM = new Intent(this, MainActivity.class);
        startActivity(intentM);
    }
}
