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

        TextView txtPesoIdeal = findViewById(R.id.txtViewResult1);
        TextView txtSex = findViewById(R.id.txtViewResult2);
        TextView txtHeight = findViewById(R.id.txtViewResult3);
        TextView txtWeight = findViewById(R.id.txtViewResult4);
        TextView txtImc = findViewById(R.id.txtViewResult5);
        TextView txtC = findViewById(R.id.txtViewResult6);
        TextView principalResultWeight = findViewById(R.id.txtViewResult7);

        char sex = getIntent().getCharExtra("sex", 'm');
        double height = getIntent().getDoubleExtra("height",0);
        String heightMeasure = getIntent().getStringExtra("heightMeasure");
        double weight = getIntent().getDoubleExtra("weight",0);
        String weightMeasure = getIntent().getStringExtra("weightMeasure");
        double wrist = getIntent().getDoubleExtra("wrist",0);
        String wristMeasure = getIntent().getStringExtra("wristMeasure");

        if(weightMeasure.equals("lb")){
            weight = weight / 2.2046; //pasando a kg
        }

        if(wristMeasure.equals("in")){
            wrist = wrist / 0.39370; //pasando a cm
        }

        double imc, mco;

        if(heightMeasure.equals("m")){

            imc = weight/(height*height); //Indice de Masa Corporal //metros //kg
            mco = (height*height)*25; //Masa Corporal Optima //metros //kg

            height = height * 100; //pasando a cm

        }else {
            height = height / 100; //pasando a m

            imc = weight/(height*height); //Indice de Masa Corporal //metros //kg
            mco = (height*height)*25; //Masa Corporal Optima //metros //kg

            height = height * 100; //pasando a cm
        }

        double ic = height / wrist; //Indice de complexion
        String complexion ="";
        double pesoIdeal = mco;

        if((ic > 10.5 & sex == 'm') | (ic > 11.5 & sex == 'f')){
            if (sex == 'm'){
                pesoIdeal = mco - 4; //4kg
            }else {
                pesoIdeal = mco - 3; //3kg
            }
            complexion = "Pequeña";

        }else if((ic > 9.5 & ic < 10.5 & sex == 'm') | (ic > 10 & ic < 11 & sex == 'f')){

            complexion = "Mediana";

        }else if ((ic < 9.5 & sex == 'm') | (ic < 10 & sex == 'f')){

            if (sex == 'm'){
                pesoIdeal = mco + 4; //4kg
            }else {
                pesoIdeal = mco + 3; //3kg
            }
            complexion = "Grande";
        }

        DecimalFormat df = new DecimalFormat("#.0");
        String prw = ""; //principal result weight

        if(imc<19){
            prw = "Peso bajo";
        }else if(imc>19 & imc<25){
            prw = "Peso ideal";
        }else if(imc>25.1 & imc<30){
            prw = "Sobrepeso";
        }else if(imc>30.1 & imc<35){
            prw = "Obesidad";
        }else if(imc>35.1 & imc<40){
            prw = "Obesidad severa";
        }else if(imc>40){
            prw = "Obesidad morbida";
        }

        if (sex == 'f'){
            txtSex.setText(getString(R.string.resultSex , "Femenino"));
        }else {
            txtSex.setText(getString(R.string.resultSex , "Masculino"));
        }

        txtPesoIdeal.setText(getString(R.string.resultPesoIdeal, df.format(pesoIdeal), weightMeasure));
        txtHeight.setText(getString(R.string.resultHeight, df.format(height), heightMeasure));
        txtWeight.setText(getString(R.string.resultWeight, df.format(weight), weightMeasure));
        txtImc.setText(getString(R.string.resultImc, df.format(imc)));
        txtC.setText(getString(R.string.resultC,complexion));

        principalResultWeight.setText(getString(R.string.principalResultWeight, prw));
    }

    @Override
    public void onBackPressed() {
        Intent intentW = new Intent(this, wrist.class);
        startActivity(intentW);
        finish();
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
