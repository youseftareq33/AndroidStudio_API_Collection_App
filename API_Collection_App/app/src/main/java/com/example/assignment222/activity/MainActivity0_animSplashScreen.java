package com.example.assignment222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment222.R;

public class MainActivity0_animSplashScreen extends AppCompatActivity {

    private Animation top, bottom;
    private TextView txt;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity0_splashscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        txt = findViewById(R.id.txt);
        img = findViewById(R.id.img);

        txt.setAnimation(bottom);
        img.setAnimation(top);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity0_animSplashScreen.this, MainActivity1_login.class);
                startActivity(intent);
                finish();
            }
        }, 2600);
    }
}