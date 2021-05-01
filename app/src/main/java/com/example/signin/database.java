package com.example.signin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class database extends AppCompatActivity {

    dbHelp db ;
    Button button;

    // These EditTexts are for insertingRow
//    EditText editRobot; // editSerialNum, , editProcedure, editPart, editPeriod, editTime, editCheck;
//    Button btnAddRow;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        db = new dbHelp(this);

        //uncomment this to delete the table so there wont be any duplicates
       // db.deleteInOmron();

        //inserting the data into the table
        boolean insert = db.insertInOmron(0,"i4-350L/450L/550L",1,"Front Panel","3 Months",5,"2021-05-04");
        boolean insert2 = db.insertInOmron(0,"i4-350L/450L/550L",2,"Safety Labels","3 Months",5,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",3,"Castings","3 Months",10,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",4,"Flyover Harness","3 Months",5,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",5,"Joint Movement and J3/J4 Brake","3 Months",20,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",6,"J1 and J2 HD","3 Months",5,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",7,"Battery","3 Months",5,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",8,"Grease Quill","3 Months",15,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",9,"Fan","3 Months",5,"2021-05-04");
        db.insertInOmron(0,"i4-350L/450L/550L",10,"J3 and J4 Belts","Yearly",20,"2021-05-04");

        db.insertInOmron(0,"LD-250",1,"LD-250 Information","N/A",3,"2021-05-04");
        db.insertInOmron(0,"LD-250",2,"Data Backup","Daily",10,"2021-05-04");
        db.insertInOmron(0,"LD-250",3,"Wi-fi Environment","6 Months",30,"2021-05-04");
        db.insertInOmron(0,"LD-250",4,"Operator Panel","Daily",3,"2021-05-04");
        db.insertInOmron(0,"LD-250",5,"Joystick","3 Months",3,"2021-05-04");
        db.insertInOmron(0,"LD-250",6,"user Emergency Stop","Daily",3,"2021-05-04");
        db.insertInOmron(0,"LD-250",7,"Safety Scanning Laser","6 Months",5,"2021-05-04");
        db.insertInOmron(0,"LD-250",8,"Low Level Laser","6 Months",5,"2021-05-04");
        db.insertInOmron(0,"LD-250",9,"Battery","6 Months",3,"2021-05-04");
        db.insertInOmron(0,"LD-250",10,"Tire & Motor","6 Months",25,"2021-05-04");
        db.insertInOmron(0,"LD-250",11,"Caster","3 Months",25,"2021-05-04");
        db.insertInOmron(0,"LD-250",12,"Time of Flight Sensor","6 Months",5,"2021-05-04");
        db.insertInOmron(0,"LD-250",13,"Screw Retightening","6 Months",30,"2021-05-04");
        db.insertInOmron(0,"LD-250",14,"Safety Commissioning","Daily",5,"2021-05-04");
        db.insertInOmron(0,"LD-250",15,"Docking Station","3 Months",15,"2021-05-04");
        db.insertInOmron(0,"LD-250",16,"Cleaning","Daily",5,"2021-05-04");
        db.insertInOmron(0,"LD-250",17,"Operation Check","N/A",15,"2021-05-04");


        db.insertInOmron(0,"LD",1,"LD Information","N/A",3,"2021-05-04");
        db.insertInOmron(0,"LD",2,"Data Backup","Daily",10,"2021-05-04");
        db.insertInOmron(0,"LD",3,"Wi-fi Environment","6 Months",30,"2021-05-04");
        db.insertInOmron(0,"LD",4,"Operator Panel","Daily",3,"2021-05-04");
        db.insertInOmron(0,"LD",5,"Joystick","3 Months",3,"2021-05-04");
        db.insertInOmron(0,"LD",6,"user Emergency Stop","Daily",3,"2021-05-04");
        db.insertInOmron(0,"LD",7,"Safety Scanning Laser","6 Months",5,"2021-05-04");
        db.insertInOmron(0,"LD",8,"Low Level Laser","6 Months",5,"2021-05-04");
        db.insertInOmron(0,"LD",9,"Battery","6 Months",3,"2021-05-04");
        db.insertInOmron(0,"LD",10,"Tire","6 Months",15,"2021-05-04");
        db.insertInOmron(0,"LD'",11,"Caster","3 Months",10,"2021-05-04");
        db.insertInOmron(0,"LD",12,"Screw Retightening","6 Months",30,"2021-05-04");
        db.insertInOmron(0,"LD",13,"Safety Commissioning","Daily",5,"2021-05-04");
        db.insertInOmron(0,"LD",14,"Docking Station","3 Months",15,"2021-05-04");
        db.insertInOmron(0,"LD",15,"Cleaning","Daily",5,"2021-05-04");
        db.insertInOmron(0,"LD",16,"Operation Check","N/A",15,"2021-05-04");



       // button = (Button)findViewById(R.id.button);


        // These EditTexts are for insertingRow however They are error'd red since they editTexts are not created yet.
//        editSerialNum = findViewById(R.id.editSerialNum);
//        editRobot = (EditText)findViewById(R.id.editRobot);
//        editProcedure = (EditText)findViewById(R.id.editProcedure);
//        editPart = (EditText)findViewById(R.id.editPart);
//        editPeriod = (EditText)findViewById(R.id.editPeriod);
//        editTime = (EditText)findViewById(R.id.editTime);
//        editCheck = (EditText)findViewById(R.id.editCheck);
//        btnAddRow = (Button) findViewById(R.id.insertionButton1); // button to finalize insertion of row
//        insertRow();


        //This calls viewParts()
//        viewParts();
//        button = (Button)findViewById(R.id.button);

        //BELOW: THEY DO NOT HAVE BUTTONS YET. PLACE THE BUTTON WHEN CONNECTING TO FRONT END.



//        updateCheck();
//        calls for Viewing
//        viewRobots();
//        viewSerial();
//        updateSerial();
//        viewSerial();
//        viewRobotAndParts();
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


//    WIP: Remainder for 90 day Interval
        // Essential for Omron Minimum Viable Product
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

    // This handles insertion of Entire Row.
//    public void insertRow(){
//        btnAddRow.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean isInserted = db.getInsertRow(
//                               // Integer.parseInt(editSerialNum.getText().toString()),
//                                editRobot.getText().toString(),
//                                Integer.parseInt(editProcedure.getText().toString()),
//                                editPart.getText().toString(),
//                                editPeriod.getText().toString(),
//                                Integer.parseInt(editTime.getText().toString()),
//                                editCheck.getText().toString()
//                        );
//
//                        if(isInserted == true)
//                            Toast.makeText(database.this,"Row Data is inserted",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(database.this,"Row Data is not inserted",Toast.LENGTH_LONG).show();
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