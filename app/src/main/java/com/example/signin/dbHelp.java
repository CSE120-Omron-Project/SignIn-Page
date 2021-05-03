
package com.example.signin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dbHelp extends SQLiteOpenHelper {


    //if u want to select only 1 part of the table u have to give it the right index.
    private static final String TABLE_NAME = "omron";
    private static final int DATABASE_VERSION = 1;
    private static final String COL_SERIAL_NUMBER = "serialNumber"; //index 0
    private static final String COL_ROBOT = "robot"; //1
    private static final String COL_PROCEDURE = "procedure"; //2
    private static final String COL_PART = "part"; //3
    private static final String COL_PERIOD = "period"; //4
    private static final String COL_TIME = "time";//5
    private static final String COL_CHECK = "check";//6

    private static dbHelp sInstance;

    public dbHelp(Context context) {
        //name of db is omron
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    // Called when the database connection is being configured
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    //used the first time a db is accessed. creates a new db
    //will not be called if a database already exists on disk with the same name
    @Override
    public void onCreate(SQLiteDatabase db) {


        //first create a new table
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + "(" + "[COL_SERIAL_NUMBER]" + " INT, " + "[COL_ROBOT]" + " TEXT, " + "[COL_PROCEDURE]" + " INT, " + "[COL_PART]" + " TEXT, " +"[COL_PERIOD]" + " TEXT, "+ "[COL_TIME] " + " INT, " + "[COL_CHECK] " + " TEXT) ";

        db.execSQL(createTableStatement);

    }

    //is called when db version is changed.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE " + TABLE_NAME);
            onCreate(db);
        }
    }

    //use only to delete table, deletes all values
    public void deleteInOmron(){
        SQLiteDatabase db = this.getWritableDatabase();
        String del = "DELETE FROM " + TABLE_NAME;
        db.execSQL(del);



    }

    //insert data into omron table
    public boolean insertInOmron (String serialNum, String robot,String procedure,String part,String period, String time, String check){

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

    public static synchronized dbHelp getInstance(Context context){
        if (sInstance == null){
            sInstance = new dbHelp(context.getApplicationContext());
        }
        return sInstance;
    }



//
    public Cursor getAllParts(){ //gets all the parts from our db
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;
    }


//    // Remaining Days not working yet
//    public Cursor getRemainingDays90() { //gets all the parts from our db
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT DATEDIFF(DATE_ADD(SELECT strftime('%Y%j', '`check`'), INTERVAL 90 DAY), CURDATE()) FROM" + TABLE_NAME + "WHERE `PERIOD` = `3 Months`", null);
//        return res;
//    }

//Returns Robot/Parts that have 3 Months and are within a certain amount of days away from maintenance
    public Cursor getUrgent(){
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD] = ? ORDER BY ABS(JULIANDAY('" +adate +"') - " + "JULIANDAY(DATE([COL_CHECK], '+3 months'))) < 7"", new String[]{"3 Months"});
        Cursor res = db.rawQuery("SELECT DISTINCT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD]=? AND JULIANDAY(DATE([COL_CHECK], '+3 month')) - " + "JULIANDAY('now') < 7", new String[]{"3 Months"});
        return res;
    }

    public Cursor getMedium(){
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD] = ? ORDER BY ABS(JULIANDAY('" +adate +"') - " + "JULIANDAY(DATE([COL_CHECK], '+3 months'))) < 7"", new String[]{"3 Months"});
        Cursor res = db.rawQuery("SELECT DISTINCT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD]=? AND JULIANDAY(DATE([COL_CHECK], '+3 month')) - " + "JULIANDAY('now') < 21 AND JULIANDAY(DATE([COL_CHECK], '+3 month')) - " + "JULIANDAY('now') > 7", new String[]{"3 Months"});
        return res;
    }

    public Cursor getLow() {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD] = ? ORDER BY ABS(JULIANDAY('" +adate +"') - " + "JULIANDAY(DATE([COL_CHECK], '+3 months'))) < 7"", new String[]{"3 Months"});
        Cursor res = db.rawQuery("SELECT DISTINCT * FROM " + TABLE_NAME +" WHERE [COL_PERIOD]=? AND JULIANDAY(DATE([COL_CHECK], '+3 month')) - " + "JULIANDAY('now') < 30 AND JULIANDAY(DATE([COL_CHECK], '+3 month')) - " + "JULIANDAY('now') > 21", new String[]{"3 Months"});
        return res;
    }

//
//     WIP: insertRow using db.insert() , returns boolean to database.java @insertRow call
    // int serialNum, String robot,int procedure,String part,String period, int time, String check
//    public boolean getInsertRow(String robot) { //gets Update Serial from our db
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();

//        values.put("[COL_SERIAL_NUMBER]", serialNum);
//        values.put("[COL_ROBOT]",robot);
//        values.put("[COL_PROCEDURE]",procedure);
//        values.put("[COL_PART]",part);
//        values.put("[COL_PERIOD]",period);
//        values.put("[COL_TIME]",time);
//        values.put("[COL_CHECK]",check);
//
//        long result = db.insert(OMRON,null,values);
//
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }

//     updateCheck does not work yet. (Version #1)
//    public Cursor getUpdateCheck(int serialNum, String robot,int procedure,String part,String period, int time, String check) { //gets Update Serial from our db
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put("[COL_CHECK]",check);
//
//        Cursor date = db.rawQuery("SELECT * FROM " + OMRON, null);
                    // db.update(OMRON,values,"serialNum = ?",new String[] {check}); this is from update
//        db.update(OMRON,values,"check = ?",date);

//
//        return true;
//    }

    //     updateCheck does not work yet. Testing Version #2 of SQLite Query
//    public void getUpdateCheck(String check) { //gets Update Serial from our db
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put("[COL_CHECK]",check);
//
//        String updateCheckSQL = "UPDATE " + OMRON + "SET " + check + " = "+ 4 + ";";
//        db.execSQL(updateCheckSQL);
//
//
//    }

//    public Cursor getAllSerial() { //gets all the Serial numbers from our db
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM " + OMRON, null);
//        return res;
//    }
    public void updateSerial(String serial, String robot, String check){
        Log.d("Updating", "updateSerial was called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("[COL_SERIAL_NUMBER]", serial);
        contentValues.put("[COL_ROBOT]", robot);
        contentValues.put("[COL_CHECK]", check);
        Log.d("ContentValues", "ContentValues filled, performing update?");
//        String strSQL = "UPDATE " +TABLE_NAME + " SET COL_SERIAL_NUMBER = " + serial + ", COL_CHECK = " + check + " WHERE COL_ROBOT = " + robot;
//        db.execSQL(strSQL);
        db.update(TABLE_NAME, contentValues, "[COL_ROBOT]=?", new String[] {robot});
        Log.d("UpdateComplete", "Update Complete");
    }

    public Cursor getAllRobots() { //gets all the Robots from our db
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }





}
