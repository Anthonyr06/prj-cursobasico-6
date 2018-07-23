package com.example.anthony.idealw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        if (sex == 'f'){
            txtSex.setText(getString(R.string.resultSex , "Femenino"));
        }else {
            txtSex.setText(getString(R.string.resultSex , "Masculino"));
        }
        txtHeight.setText(getString(R.string.resultHeight, height, heightMeasure));
        txtWeight.setText(getString(R.string.resultWeight, weight, weightMeasure));
        txtImc.setText(getString(R.string.resultImc, imc));


    }

    @Override
    public void onClick(View view) {

    }
}
