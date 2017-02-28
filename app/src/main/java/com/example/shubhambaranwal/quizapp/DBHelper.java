package com.example.shubhambaranwal.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham.baranwal on 6/19/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_OPTD= "optd"; //option d

   //second table
   private static final String TABLE_QUEST1 = "quest1";
    // tasks Table Columns names
    private static final String KEY_ID1 = "id1";
    private static final String KEY_QUES1 = "question1";
    private static final String KEY_ANSWER1 = "answer1"; //correct option
    private static final String KEY_OPTA1= "opta1"; //option a
    private static final String KEY_OPTB1= "optb1"; //option b
    private static final String KEY_OPTC1= "optc1"; //option c
    private static final String KEY_OPTD1= "optd1"; //option d





    private SQLiteDatabase dbase;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT )";
        db.execSQL(sql);
        addQuestions();


        String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST1 + " ( "
                + KEY_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES1
                + " TEXT, " + KEY_ANSWER1+ " TEXT, "+KEY_OPTA1 +" TEXT, "
                +KEY_OPTB1 +" TEXT, "+KEY_OPTC1+" TEXT, "+KEY_OPTD1+" TEXT )";
        db.execSQL(sql1);
        addQuestions1();


//db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("Choose the operator which cannot be overloaded.","/", "()", "::", "%", "::");
        this.addQuestion(q1);
        Question q2=new Question("Choose the respective delete operator usage for the expression ‘ptr=new int[100]’. ", "delete ptr;", "delete ptr[];", "delete[] ptr;", "[]delete ptr;", "delete[] ptr;");
        this.addQuestion(q2);
        Question q3=new Question(" We can use this pointer in static member function of the class." ,"TRUE", "FALSE","CAN'T SAY","NONE OF THESE", "FALSE");
        this.addQuestion(q3);
        Question q4=new Question("The programs machine instructions are store in __ memory segment.", "DATA", "STACK", "HEAP","CODE","CODE");
        this.addQuestion(q4);
        Question q5=new Question("What is the full form of STL?","Standard template library.","System template library.","Standard topics library.","None of the above.","Standard template library.");
        this.addQuestion(q5);
    }

    private void addQuestions1()
    {
        Question q1=new Question("What is the size of byte variable?","8 bit", "16 bit", "32 bit", "64 bit", "8 bit");
        this.addQuestion1(q1);
        Question q2=new Question("What is the size of char variable? ", "8 bit", "16 bit", "32 bit", "64 bit", "16 bit");
        this.addQuestion1(q2);
        Question q3=new Question(" What is the default value of double variable?" ,"0.0d", "0.0f","0","NOT DEFINED", "0.0d");
        this.addQuestion1(q3);
        Question q4=new Question("Which is the way in which a thread can enter the waiting state?", "Invoke its sleep() method.", "invoke object's wait method.", "Invoke its suspend() method.","All of the above.","All of the above.");
        this.addQuestion1(q4);
        Question q5=new Question("This is the parent of Error and Exception classes.","Throwable","Catchable","MainError","MainException","Throwable");
        this.addQuestion1(q5);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST1);
// Create tables again
        onCreate(db);

    }
    // Adding new question
    public void addQuestion(Question quest) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public void addQuestion1(Question quest) {
//SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES1, quest.getQUESTION());
        values.put(KEY_ANSWER1, quest.getANSWER());
        values.put(KEY_OPTA1, quest.getOPTA());
        values.put(KEY_OPTB1, quest.getOPTB());
        values.put(KEY_OPTC1, quest.getOPTC());
        values.put(KEY_OPTD1, quest.getOPTD());
// Inserting Row
        dbase.insert(TABLE_QUEST1, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }



    public List<Question> getAllQuestions1() {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST1;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return quest list
        return quesList;
    }

    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
