package com.example.anthony.idealw;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity implements View.OnClickListener {

    char sex;
    double height, weight, wrist;
    String heightMeasure, weightMeasure, wristMeasure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.btnResult).setOnClickListener(this);

        TextView txtPesoIdeal = findViewById(R.id.txtViewResult1);
        TextView txtSex = findViewById(R.id.txtViewResult2);
        TextView txtHeight = findViewById(R.id.txtViewResult3);
        TextView txtWeight = findViewById(R.id.txtViewResult4);
        TextView txtImc = findViewById(R.id.txtViewResult5);
        TextView txtC = findViewById(R.id.txtViewResult7);
        TextView txtWeightNeed = findViewById(R.id.txtViewResult6);
        TextView principalResultWeight = findViewById(R.id.txtViewResult8);

        sex = getIntent().getCharExtra("sex", 'M');
        height = getIntent().getDoubleExtra("height",0);
        heightMeasure = getIntent().getStringExtra("heightMeasure");
        weight = getIntent().getDoubleExtra("weight",0);
        weightMeasure = getIntent().getStringExtra("weightMeasure");
        wrist = getIntent().getDoubleExtra("wrist",0);
        wristMeasure = getIntent().getStringExtra("wristMeasure");

        if(weightMeasure.equals("lb")){
            weight = weight / 2.2046; //pasando a kg
        }

        if(wristMeasure.equals("in")){
            wrist = wrist / 0.39370; //pasando a cm
        }

        double imc = 0;//Indice de Masa Corporal
        double mco = 0; //Masa Corporal Optima

        if(!heightMeasure.equals("m")) {

            height = height / 100; //pasando a m

            imc = weight / (height * height); //metros //kg
            mco = (height * height) * 23; //metros //kg
        }

        height = height * 100; //pasando a cm

        double ic = height / wrist; //Indice de complexion //cm //cm
        String complexion ="";
        double pesoIdeal = mco;

        if(sex == 'M'){
            if(ic > 10.5){
                pesoIdeal = mco - 4; //4kg
                complexion = "Pequeña";
            }else if(ic > 9.5 & ic < 10.5){
                complexion = "Mediana";
            }else if (ic < 9.5){
                pesoIdeal = mco + 4; //4kg
                complexion = "Grande";
            }
        }
        if(sex == 'F'){
            if(ic > 11.5){
                pesoIdeal = mco - 3; //3kg
                complexion = "Pequeña";
            }else if(ic > 10 & ic < 11){
                complexion = "Mediana";
            }else if (ic < 10){
                pesoIdeal = mco + 3; //3kg
                complexion = "Grande";
            }
        }

        DecimalFormat df = new DecimalFormat("#.0");
        String prw = ""; //principal result weight
        int color = R.color.colorPrimaryDark;

        if(imc<19){
            prw = "Peso bajo";
            color = R.color.pesoBajo;
        }else if(imc>19 & imc<25){
            prw = "Peso ideal";
            color = R.color.pesoIdeal;
        }else if(imc>25.1 & imc<30){
            prw = "Sobrepeso";
            color = R.color.sobrepeso;
        }else if(imc>30.1 & imc<35){
            prw = "Obesidad";
            color = R.color.obesidad;
        }else if(imc>35.1 & imc<40){
            prw = "Obesidad severa";
            color = R.color.obesidadSevera;
        }else if(imc>40){
            prw = "Obesidad morbida";
            color = R.color.obesidadMorbida;
        }

        if (sex == 'F'){
            txtSex.setText(getString(R.string.resultSex , "Femenino"));
        }else {
            txtSex.setText(getString(R.string.resultSex , "Masculino"));
        }

        if(weightMeasure.equals("lb")){
            weight = weight * 2.2046; //pasando a lb
            pesoIdeal = pesoIdeal * 2.2046; //pasando a lb
        }

        double weightNeed = 0 ;
        int resultWeightNeed =  R.string.resultWeightNeed2;

        if( !prw.equals("Peso ideal") & !prw.equals("Peso bajo")){
            weightNeed = weight - pesoIdeal;
            resultWeightNeed = R.string.resultWeightNeed1;
        }else if(prw.equals("Peso bajo")){
            weightNeed = pesoIdeal - weight;
            resultWeightNeed = R.string.resultWeightNeed2;
        }else {
            txtWeightNeed.setVisibility(View.INVISIBLE);
        }

        txtPesoIdeal.setText(getString(R.string.resultPesoIdeal, df.format(pesoIdeal), weightMeasure));
        txtHeight.setText(getString(R.string.resultHeight, df.format(height), heightMeasure));
        txtWeight.setText(getString(R.string.resultWeight, df.format(weight), weightMeasure));
        txtImc.setText(getString(R.string.resultImc, df.format(imc)));
        txtC.setText(getString(R.string.resultC,complexion));
        txtWeightNeed.setText(getString(resultWeightNeed, df.format(weightNeed), weightMeasure));

        principalResultWeight.setTextColor(ContextCompat.getColor(this,color));
        principalResultWeight.setText(getString(R.string.principalResultWeight, prw));
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
