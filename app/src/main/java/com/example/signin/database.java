package com.example.signin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class database extends AppCompatActivity {

    dbHelp db ;
    Button button;

    // These EditTexts are for updatingSerial
    // EditText editName,editSurname,editMarks,editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //uncomment this to delete the table so there wont be any duplicates
        //db.deleteInOmron();

        //inserting the data into the table
        //i4 type robot
        db.insertInOmron("0", "i4-350L/450L/550L", "1", "Front Panel", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "2", "Safety Labels", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "3", "Castings", "3 Months", "10", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "4", "Flyover Harness", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "5", "Joint Movement and J3/J4 Brake", "3 Months", "20", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "6", "J1 and J2 HD", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "7", "Battery", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "8", "Grease Quill", "3 Months", "15", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "9", "Fan", "3 Months", "5", "2021-05-04");
        db.insertInOmron("0", "i4-350L/450L/550L", "10", "J3 and J4 Belts", "Yearly", "20", "2021-05-04");

        //LD-250 type robot
        db.insertInOmron("0", "LD-250", "1", "LD-250 Information", "N/A", "3", "2021-05-04");
        db.insertInOmron("0", "LD-250", "2", "Data Backup", "Daily", "10", "2021-05-04");
        db.insertInOmron("0", "LD-250", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
        db.insertInOmron("0", "LD-250", "4", "Operator Panel", "Daily", "3", "2021-05-04");
        db.insertInOmron("0", "LD-250", "5", "Joystick", "3 Months", "3", "2021-05-04");
        db.insertInOmron("0", "LD-250", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
        db.insertInOmron("0", "LD-250", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
        db.insertInOmron("0", "LD-250", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
        db.insertInOmron("0", "LD-250", "9", "Battery", "6 Months", "3", "2021-05-04");
        db.insertInOmron("0", "LD-250", "10", "Tire & Motor", "6 Months", "25", "2021-05-04");
        db.insertInOmron("0", "LD-250", "11", "Caster", "3 Months", "25", "2021-05-04");
        db.insertInOmron("0", "LD-250", "12", "Time of Flight Sensor", "6 Months", "5", "2021-05-04");
        db.insertInOmron("0", "LD-250", "13", "Screw Retightening", "6 Months", "30", "2021-05-04");
        db.insertInOmron("0", "LD-250", "14", "Safety Commissioning", "Daily", "5", "2021-05-04");
        db.insertInOmron("0", "LD-250", "15", "Docking Station", "3 Months", "15", "2021-05-04");
        db.insertInOmron("0", "LD-250", "16", "Cleaning", "Daily", "5", "2021-05-04");
        db.insertInOmron("0", "LD-250", "17", "Operation Check", "N/A", "15", "2021-05-04");

        //LD type robot
        db.insertInOmron("0", "LD", "1", "LD Information", "N/A", "3", "2021-05-04");
        db.insertInOmron("0", "LD", "2", "Data Backup", "Daily", "10", "2021-05-04");
        db.insertInOmron("0", "LD", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
        db.insertInOmron("0", "LD", "4", "Operator Panel", "Daily", "3", "2021-05-04");
        db.insertInOmron("0", "LD", "5", "Joystick", "3 Months", "3", "2021-05-04");
        db.insertInOmron("0", "LD", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
        db.insertInOmron("0", "LD", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
        db.insertInOmron("0", "LD", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
        db.insertInOmron("0", "LD", "9", "Battery", "6 Months", "3", "2021-05-04");
        db.insertInOmron("0", "LD", "10", "Tire", "6 Months", "15", "2021-05-04");
        db.insertInOmron("0", "LD", "11", "Caster", "3 Months", "10", "2021-05-04");
        db.insertInOmron("0", "LD", "12", "Screw Retightening", "6 Months", "30", "2021-05-04");
        db.insertInOmron("0", "LD", "13", "Safety Commissioning", "Daily", "5", "2021-05-04");
        db.insertInOmron("0", "LD", "14", "Docking Station", "3 Months", "15", "2021-05-04");
        db.insertInOmron("0", "LD", "15", "Cleaning", "Daily", "5", "2021-05-04");
        db.insertInOmron("0", "LD", "16", "Operation Check", "N/A", "15", "2021-05-04");


        button = (Button)findViewById(R.id.button);

        // These EditTexts are for updatingSerial
//        private EditText serialNum ;
//        editTextId = (EditText)findViewById(R.id.editText_id);

        //calls view parts
//        viewParts();

        //button = (Button)findViewById(R.id.button);
        //viewRemainingDays90();

        //BELOW: THEY DO NOT HAVE BUTTONS YET. PLACE THE BUTTON WHEN CONNECTING TO FRONT END.

        // button = (Button)findViewById(R.id.button);

//        updateCheck();
        //calls for Viewing
        //viewRobots();
        //viewSerial();
//        updateSerial();
//        viewSerial();
//        viewRobotAndParts();
//
//
//
//        if(insert == true){
//            Toast.makeText(getApplicationContext(),"INSERTED first DATA 10 ", Toast.LENGTH_LONG).show();
//        }

    }

    //    This handles the button view all
//    public void viewParts(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getAllParts(); //calls db object, get all parts is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("PART: "+ res.getString( 3) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

//    Remainder of 90 days Does not work yet
//    public void viewRemainingDays90(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getRemainingDays90(); //calls db object, get all robots is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("ROBOT: "+ res.getString( 1) + "\n");
//                            buffer.append("PART: "+ res.getString( 3) + "\n");
//                            buffer.append("Remaining Days: "+ res.getString( 6) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

    // This handles the button view all for ROBOTS and PARTS
//    public void viewRobotAndParts(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getAllParts(); //calls db object, get all parts is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("ROBOT: "+ res.getString( 1) + "\n");
//                            buffer.append("PART: "+ res.getString( 3) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

    // This handles viewing all robots
//    public void viewRobots(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getAllRobots(); //calls db object, get all robots is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("ROBOT: "+ res.getString( 1) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

//    // This handles viewing Serial Numbers
//    public void viewSerial(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getAllSerial(); //calls db object, get all robots is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("Serial: "+ res.getString( 0) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

    // This handles updating Serial Numbers and the editTextID for updating.
//    public void updateSerial(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getUpdateSerial(); //calls db object, get all robots is where the query is written
//
//                        boolean isUpdate = db.getUpdateSerial(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(database.this,"Data Updated",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(database.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }

    // This handles updating Check Numbers and the editTextID for updating.
//    public void updateCheck(){
//        button.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res =  db.getUpdateCheck(); //calls db object, get all robots is where the query is written
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("Serial: "+ res.getString( 0) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }


    //this is the pop up that shows up with the data
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }
}