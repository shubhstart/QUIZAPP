package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class SignUp extends Activity
{
    EditText edtUsername,edtEmail,edtPhno,edtPasswd,edtRole;
    Button btnRegister;
    DBREG dbr;
    String username,passwd,phno,email,role;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        edtUsername=(EditText)findViewById(R.id.edtUsername);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPasswd=(EditText)findViewById(R.id.edtPasswd);
        edtPhno=(EditText)findViewById(R.id.edtPhno);
        edtRole=(EditText)findViewById(R.id.edtRole);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        dbr=new DBREG(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=edtUsername.getText().toString();
                passwd=edtPasswd.getText().toString();
                email=edtEmail.getText().toString();
                phno=edtPhno.getText().toString();
                role=edtRole.getText().toString();
                long id=dbr.addInfo(username,passwd,phno,email,role);
                if(id>0)
                {
                    Toast.makeText(SignUp.this,"Registered",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp.this,"Not Registered", Toast.LENGTH_SHORT).show();
                }
                edtUsername.setText("");
                edtPasswd.setText("");
                edtEmail.setText("");
                edtPhno.setText("");
                edtRole.setText("");
                edtUsername.requestFocus();
            }
        });

    }
}
