package com.example.shubhambaranwal.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class QuizActivity1 extends Activity
{
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        DBHelper db=new DBHelper(this);
        quesList=db.getAllQuestions1();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView);
        rda=(RadioButton)findViewById(R.id.radioButton);
        rdb=(RadioButton)findViewById(R.id.radioButton2);
        rdc=(RadioButton)findViewById(R.id.radioButton3);
        rdd=(RadioButton)findViewById(R.id.radioButton4);
        butNext=(Button)findViewById(R.id.button);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                String str=answer.getText().toString();
                Log.d("yourans", currentQ.getANSWER()+" "+str);
                if(currentQ.getANSWER().equals(str))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity1.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        rdd.setText(currentQ.getOPTD());
        rda.setChecked(true);
        qid++;
    }
}
