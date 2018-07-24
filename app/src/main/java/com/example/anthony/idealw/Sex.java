package com.example.anthony.idealw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sex extends AppCompatActivity implements View.OnClickListener{

    char sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);

        findViewById(R.id.btnMale).setOnClickListener(this);
        findViewById(R.id.btnFemale).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMale:
                sex = 'M';
                break;
            case R.id.btnFemale:
                sex = 'F';
                break;
        }
        Intent intentH = new Intent(this, Height.class);
        intentH.putExtra("sex", sex);
        startActivity(intentH);
        finish();
    }
}
