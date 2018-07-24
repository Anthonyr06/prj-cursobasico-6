package com.example.anthony.idealw;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnMain).setOnClickListener(this);
        video = findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.running);
        video.setVideoURI(uri);

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        video.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!video.isPlaying()) {
            video.resume();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, Sex.class);
        startActivity(intent);

        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
