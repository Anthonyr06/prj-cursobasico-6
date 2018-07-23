package com.example.anthony.idealw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sex extends AppCompatActivity implements View.OnClickListener{

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
                break;
            case R.id.btnFemale:
                break;
        }
    }
}
