package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private EditText serialNum;
    private EditText modelNum;
    private EditText date;

    private Button register;
    private Button next;
    private Button initDB;

    //This page's database
    private dbHelp homepagedb;


    //hard coded data to test (LD ROBOT)
    private String testNum = "7540";
    private String testModel = "LD";
    private String testDate = "2021-06-06";


    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Intialization of Database?
        homepagedb = dbHelp.getInstance(this);

        //connects to xml layout, etName is what we called the part "enter name" in the xml
        serialNum = findViewById(R.id.etSerial);
        modelNum= findViewById(R.id.etModel);
        date = findViewById(R.id.etDate);

        register = findViewById(R.id.btnRegister); //maybe change button name later

        //Handles register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //takes user input and converts to string
                String inputSerial = serialNum.getText().toString();
                String inputModel = modelNum.getText().toString();
                String inputDate = date.getText().toString();

                if(inputSerial.isEmpty()|| inputModel.isEmpty() || inputDate.isEmpty()){

                    // Display a message toast to user to enter the details
                    Toast.makeText(HomePage.this, "Please enter Required Inputs!", Toast.LENGTH_LONG).show();
                }
                else{
                    isValid = validate(inputSerial,inputModel,inputDate);

                    if(!isValid){
                        Toast.makeText(HomePage.this, "Model is wrong", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Log.d("Registration", "Registration was successful.");
                        Toast.makeText(HomePage.this, "Register Successful", Toast.LENGTH_LONG).show();

                        /*calling robotData class, have to put code here to print out given
                          serial number and model, if put in robotData.java we would lose the user input bc
                          inputModel and inputSerial are out of scope.
                         */

                        // calling robot xml file COMMENTED OUT TO SEE IF THIS WAS SENDING BACK TO FRONT PAGE
//                        setContentView(R.layout.activity_robot_data);
//
//                        TextView textViewModel = (TextView) findViewById(R.id.textView2);
//                        textViewModel.setText("Robot Model: " + inputModel);
//
//                        TextView textViewSerial = (TextView) findViewById(R.id.textView3);
//                        textViewSerial.setText("Robot Serial: " + inputSerial);
//
//                        TextView textViewDate = (TextView) findViewById(R.id.textView4);
//                        textViewDate.setText("Date Deployed: " + inputDate);

                        Log.d("AttemptSerial", "Attempting to update Serial(and date tbh)");
                        homepagedb.updateSerial(inputSerial, inputModel, inputDate);

                    }
                }



            }
        });

        next = findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, notifications.class);
                startActivity(intent);

            }
        });

//        initDB = findViewById(R.id.btnInitDB);
//        initDB.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //inserting the data into the table
//                //i4 type robot
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "1", "Front Panel", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "2", "Safety Labels", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "3", "Castings", "3 Months", "10", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "4", "Flyover Harness", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "5", "Joint Movement and J3/J4 Brake", "3 Months", "20", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "6", "J1 and J2 HD", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "7", "Battery", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "8", "Grease Quill", "3 Months", "15", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "9", "Fan", "3 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "i4-350L/450L/550L", "10", "J3 and J4 Belts", "Yearly", "20", "2021-05-04");
//
//                //LD-250 type robot
//                homepagedb.insertInOmron("0", "LD-250", "1", "LD-250 Information", "N/A", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "2", "Data Backup", "Daily", "10", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "4", "Operator Panel", "Daily", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "5", "Joystick", "3 Months", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "9", "Battery", "6 Months", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "10", "Tire & Motor", "6 Months", "25", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "11", "Caster", "3 Months", "25", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "12", "Time of Flight Sensor", "6 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "13", "Screw Retightening", "6 Months", "30", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "14", "Safety Commissioning", "Daily", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "15", "Docking Station", "3 Months", "15", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "16", "Cleaning", "Daily", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD-250", "17", "Operation Check", "N/A", "15", "2021-05-04");
//
//                //LD type robot
//                homepagedb.insertInOmron("0", "LD", "1", "LD Information", "N/A", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "2", "Data Backup", "Daily", "10", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "4", "Operator Panel", "Daily", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "5", "Joystick", "3 Months", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "9", "Battery", "6 Months", "3", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "10", "Tire", "6 Months", "15", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "11", "Caster", "3 Months", "10", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "12", "Screw Retightening", "6 Months", "30", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "13", "Safety Commissioning", "Daily", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "14", "Docking Station", "3 Months", "15", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "15", "Cleaning", "Daily", "5", "2021-05-04");
//                homepagedb.insertInOmron("0", "LD", "16", "Operation Check", "N/A", "15", "2021-05-04");
//            }
//        });






        //hyper link code
//        TextView link  = (TextView)findViewById(R.id.textViewLink);
//        link.setMovementMethod(LinkMovementMethod.getInstance());
//        Intent intent = new Intent(HomePage.this, robotData.class);
//        startActivity(intent);


    }

    private boolean validate(String number,String model,String date){
        if(model.equals("i4-350L/450L/550L")|| model.equals("LD-250") || model.equals("LD")){
            return true;
        }
        else{
            return false;
        }

    }
}