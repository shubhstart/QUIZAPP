package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class ForgotPasswd extends Activity
{
    EditText edtUsername;
    Button btnRecover;
    String username="";
    DBREG dbr;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_passwd);
        edtUsername=(EditText)findViewById(R.id.edtUsername);
        btnRecover=(Button)findViewById(R.id.btnRecover);
        dbr=new DBREG(this);

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr.openReadable();
                username=edtUsername.getText().toString();
                Cursor cursor=dbr.retrievePassword(username);
                cursor.moveToFirst();
                if(cursor.isAfterLast()==false)
                {
                    Toast.makeText(ForgotPasswd.this,"Password:"+cursor.getString(0)+"\n Role:"+cursor.getString(1),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ForgotPasswd.this,"Invalid Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
