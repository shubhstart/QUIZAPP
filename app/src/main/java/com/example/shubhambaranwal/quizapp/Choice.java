package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class Choice extends Activity {

ImageButton btn,btn1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        btn=(ImageButton)findViewById(R.id.imageButton);
        btn1=(ImageButton)findViewById(R.id.imageButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Choice.this, QuizActivity.class);
                startActivity(i);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Choice.this, QuizActivity1.class);
                startActivity(i);

            }
        });

    }
}
