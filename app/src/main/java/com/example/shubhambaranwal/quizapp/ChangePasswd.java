package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class ChangePasswd extends Activity
{
    EditText edtUsername,edtOldpasswd,edtNewpasswd,edtConfirmpasswd;
    Button btnChangepasswd,btnCancle;
    String username,oldpasswd,newpasswd,confirmpasswd;
    String username1,passwd1;
    DBREG dbr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_passwd);
        edtUsername=(EditText)findViewById(R.id.edtUsername);
        edtOldpasswd=(EditText)findViewById(R.id.edtOldpasswd);
        edtNewpasswd=(EditText)findViewById(R.id.edtNewpasswd);
        edtConfirmpasswd=(EditText)findViewById(R.id.edtConfirmpasswd);
        btnChangepasswd=(Button)findViewById(R.id.btnChangepasswd);
        btnCancle=(Button)findViewById(R.id.btnCancle);
        Intent i=getIntent();
        username1=i.getStringExtra("username");
        passwd1=i.getStringExtra("passwd");
        dbr=new DBREG(this);
        btnChangepasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=edtUsername.getText().toString();
                oldpasswd=edtOldpasswd.getText().toString();
                newpasswd=edtNewpasswd.getText().toString();
                confirmpasswd=edtConfirmpasswd.getText().toString();
                if(newpasswd.equals(confirmpasswd)) {
                    if (username.equals(username1) && oldpasswd.equals(passwd1))
                    {
                        int id=dbr.updatePassword(username,confirmpasswd);
                        if(id>0)
                        {
                            Toast.makeText(ChangePasswd.this, "Password Changed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ChangePasswd.this,"Password not changed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ChangePasswd.this, "Password can't be changed", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ChangePasswd.this,"New Password and Re-entered Password not Matched",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUsername.setText("");
                edtOldpasswd.setText("");
                edtNewpasswd.setText("");
                edtConfirmpasswd.setText("");
                edtUsername.requestFocus();
            }
        });

    }

}
