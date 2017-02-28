package com.example.shubhambaranwal.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shubham.baranwal on 6/21/2016.
 */
public class DBREG
{
    //DB fields
    public static final String DB_ROLE="role";
    public static final String DB_USERNAME="username";
    public static final String DB_PASSWORD="password";
    public static final String DB_EMAIL="email";
    public static final String DB_PHONENO="phoneno";

    //DB name table name
    public static final String DB_NAME="SECURITY";
    public static final String DB_TABLE="LOGDETAILS";
    public static final String CREATE_TABLE="create table "+DB_TABLE+" ("+DB_USERNAME+" text primary key,"+DB_PASSWORD+" text,"+DB_EMAIL+" text,"+DB_PHONENO+" integer,"+DB_ROLE+" text)";
    public static final int DB_VERSION=1;
    public SQLiteDatabase db;
    public Context context;
    public SQLHelper helper;
    public DBREG(Context context)
    {
        this.context=context;
        helper=new SQLHelper();
        db=helper.getWritableDatabase();

    }
    public DBREG openReadable() throws android.database.sqlite.SQLiteException
    {
        helper=new SQLHelper();
        db=helper.getReadableDatabase();
        return this;
    }
    public long addInfo(String username,String password,String email,String phoneno,String role)
    {
        ContentValues cv=new ContentValues();
        cv.put(DB_USERNAME,username);
        cv.put(DB_PASSWORD,password);
        cv.put(DB_EMAIL,email);
        cv.put(DB_PHONENO,phoneno);
        cv.put(DB_ROLE,role);
        return db.insert(DB_TABLE,null,cv);
    }
    public Cursor login(String username,String password,String role)
    {
        Cursor cursor=db.query(DB_TABLE,new String[]{DB_USERNAME,DB_PASSWORD,DB_ROLE},DB_USERNAME+"='"+username+"'"+" and "+DB_PASSWORD+"='"+password+"'"+" and "+DB_ROLE+"='"+role+"'",null,null,null,null);
        return cursor;
    }
    public Cursor retrievePassword(String username)
    {
        String[] columns={DB_PASSWORD,DB_ROLE};
        Cursor cursor=db.query(DB_TABLE,columns,DB_USERNAME+"='"+username+"'",null,null,null,null);
        return cursor;
    }
    public int updatePassword(String username,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put(DB_PASSWORD,password);
        return db.update(DB_TABLE,cv,DB_USERNAME+"='"+username+"'",null);
    }
    public class SQLHelper extends SQLiteOpenHelper
    {

        public SQLHelper()
        {
            //To create db in Sqlhelper class

            super(context,DB_NAME,null,DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            //to create table in StudentMaster db
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS"+DB_TABLE);
            Log.d("Upgrade","DATABASE VERSION CHANGED FROM "+oldVersion+"to"+newVersion);
            onCreate(db);
        }
    }
}
