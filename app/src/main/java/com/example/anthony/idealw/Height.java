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

public class Height extends AppCompatActivity implements View.OnClickListener{

    EditText edit;
    Spinner spn;
    String spnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        findViewById(R.id.btnHeight).setOnClickListener(this);
        spn = findViewById(R.id.spnHeight);
        edit = findViewById(R.id.editTxtHeight);

        String[] measure = {"cm","m"};
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
    public void onClick(View view) {

        if(edit.getText().toString().isEmpty() || edit.getText().toString().equals(".")){

            Toast.makeText(this, "Introduzca su altura", Toast.LENGTH_SHORT)
                    .show();

        }else {

            double height = Double.parseDouble(edit.getText().toString());

            char sex = getIntent().getCharExtra("sex", 'M');

            Intent intentW = new Intent(this, Weight.class);
            intentW.putExtra("sex",sex);
            intentW.putExtra("height", height);
            intentW.putExtra("heightMeasure", spnSelected);
            startActivity(intentW);
            finish();
        }
    }
}
