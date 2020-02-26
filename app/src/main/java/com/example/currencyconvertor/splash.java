package com.example.currencyconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView =(ImageView)findViewById(R.id.img);
        Animation rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        imageView.startAnimation(rotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },7000);
    }
}
