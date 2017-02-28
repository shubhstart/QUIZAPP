package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class Authentication extends Activity
{
    EditText edtText,edtText2;
    Spinner spinner;
    String username,passwd,role;
    Button btnLogin,btnCancle;
    DBREG dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authen);
        edtText=(EditText)findViewById(R.id.editText);
        edtText2=(EditText)findViewById(R.id.editText2);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnCancle=(Button)findViewById(R.id.btnCancle);
        spinner=(Spinner)findViewById(R.id.spinner);
        dbr=new DBREG(this);
        dbr.openReadable();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role=spinner.getAdapter().getItem(position).toString();
                Toast.makeText(Authentication.this,"YOU SELECTED:"+role, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                username=edtText.getText().toString();
                passwd=edtText2.getText().toString();



                Cursor cursor=dbr.login(username,passwd,role);
                cursor.moveToFirst();
                if(cursor.getCount()>0)
                {

                    Toast.makeText(Authentication.this,"Username:"+cursor.getString(0)+"\nPassword:"+cursor.getString(1)+"\nRole:"+cursor.getString(2),Toast.LENGTH_LONG).show();

                        Intent i = new Intent(Authentication.this, SuccessfulLogin.class);
                        i.putExtra("username", username);
                        i.putExtra("passwd", passwd);
                        startActivity(i);
                        edtText.setText("");
                        edtText2.setText("");
                        edtText.requestFocus();



                }
                else
                {
                    Toast.makeText(Authentication.this,"INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
                    edtText.setText("");
                    edtText2.setText("");
                    edtText.requestFocus();
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText.setText("");
                edtText2.setText("");
                edtText.requestFocus();
            }
        });

    }
    public void onClick(View v)
    {
        Intent i=new Intent(Authentication.this,ForgotPasswd.class);
        startActivity(i);
    }

    public void onClick1(View v)
    {
        Intent i=new Intent(Authentication.this,SignUp.class);
        startActivity(i);
    }
}
