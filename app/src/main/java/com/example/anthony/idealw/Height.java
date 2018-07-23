package com.example.anthony.idealw;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Height extends AppCompatActivity implements View.OnClickListener{

    EditText edit;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        findViewById(R.id.btnHeight).setOnClickListener(this);
        spn = findViewById(R.id.spnHeight);
        edit = findViewById(R.id.editTxtHeight);


        String[] measure = {"cm","m","ft"};

        spn.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measure));

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Toast.makeText(adapterView.getContext(),
                        (String) adapterView.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });
    }


    @Override
    public void onClick(View view) {

        if(edit.getText().toString().isEmpty()){

            Toast.makeText(this, "Ingrese su altura", Toast.LENGTH_SHORT)
                    .show();

        }else {

            float height = Float.parseFloat(edit.getText().toString());

            Intent intentR = new Intent(this, Result.class);
            intentR.putExtra("Height", height);

        }
    }
}
