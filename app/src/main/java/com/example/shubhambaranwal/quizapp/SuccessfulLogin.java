package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class SuccessfulLogin  extends Activity
{
    String username,passwd;
    TextView txtUser,changePasswd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_login);
        txtUser=(TextView)findViewById(R.id.txtUser);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        passwd=i.getStringExtra("passwd");
        txtUser.setText(username);
    }
    public void onClick(View v)
    {
        Intent i=new Intent(SuccessfulLogin.this,ChangePasswd.class);
        i.putExtra("username",username);
        i.putExtra("passwd",passwd);
        startActivity(i);
    }
    public void onClick1(View v)
    {
        Intent i=new Intent(SuccessfulLogin.this,Choice.class);
        startActivity(i);
    }
}
