package com.example.anthony.idealw;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
    public void onBackPressed()
    {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
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
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
