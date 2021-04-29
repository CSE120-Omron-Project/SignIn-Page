package com.example.signin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelp extends SQLiteOpenHelper {


    //if u want to select only 1 part of the table u have to give it the right index.
    public static final String OMRON = "omron";
    public static final String COL_SERIAL_NUMBER = "serialNumber"; //index 0
    public static final String COL_ROBOT = "robot"; //1
    public static final String COL_PROCEDURE = "procedure"; //2
    public static final String COL_PART = "part"; //3
    public static final String COL_PERIOD = "period"; //4
    public static final String COL_TIME = "time";//5
    public static final String COL_CHECK = "check";//6

    public dbHelp(@Nullable Context context) {
        //name of db is robot.db

        super(context, COL_ROBOT + ".db", null, 1);

    }

    //used the first time a db is accessed. creates a new db
    @Override
    public void onCreate(SQLiteDatabase db) {


        //first create a new table
        String createTableStatement = "CREATE TABLE " + OMRON + "(" + "[COL_SERIAL_NUMBER]" + " INT, " + "[COL_ROBOT]" + " TEXT, " + "[COL_PROCEDURE]" + " INT, " + "[COL_PART]" + " TEXT, " +"[COL_PERIOD]" + " TEXT, "+ "[COL_TIME] " + " INT, " + "[COL_CHECK] " + " TEXT) ";

        db.execSQL(createTableStatement);



    }

    //is called when db version is changed.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + OMRON);


    }

    //use only to delete table, deletes all values
    public void deleteInOmron(){
        SQLiteDatabase db = this.getWritableDatabase();
        String del = "DELETE FROM " + OMRON;
        db.execSQL(del);



    }

    //insert data into omron table
    public boolean insertInOmron (int serialNum, String robot,int procedure,String part,String period, int time, String check){

        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("[COL_SERIAL_NUMBER]", serialNum);
        values.put("[COL_ROBOT]",robot);
        values.put("[COL_PROCEDURE]",procedure);
        values.put("[COL_PART]",part);
        values.put("[COL_PERIOD]",period);
        values.put("[COL_TIME]",time);
        values.put("[COL_CHECK]",check);
        long result = db.insert("OMRON",null,values);

        if(result == -1){
            return  false;

        }
        else{
            return true;
        }
    }

    public Cursor getAllParts(){ //gets all the parts from our db
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + OMRON,null);

        return res;
    }







}
