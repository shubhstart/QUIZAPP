package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by shubham.baranwal on 6/19/2016.
 */
public class Splash extends Activity {
    Handler h = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Authentication.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }
}
