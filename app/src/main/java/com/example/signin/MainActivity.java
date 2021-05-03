package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;

    //hard coded data to test
    private String userName = "Omron";
    private String passWord = "120";

    boolean isValid = false;

    private dbHelp logindb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logindb = dbHelp.getInstance(this);

        //connects to xml layout, etName is what we called the part "enter name" in the xml
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

        //handles what happens when login is clicked
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //takes user input and converts to string
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty()|| inputPassword.isEmpty()){

                    // Display a message toast to user to enter the details
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                }
                else{
                    isValid = validate(inputName,inputPassword);

                    if(!isValid){
                        Toast.makeText(MainActivity.this, "PassWord or name is wrong", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();

//                        //inserting the data into the table
//                        //i4 type robot
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "1", "Front Panel", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "2", "Safety Labels", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "3", "Castings", "3 Months", "10", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "4", "Flyover Harness", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "5", "Joint Movement and J3/J4 Brake", "3 Months", "20", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "6", "J1 and J2 HD", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "7", "Battery", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "8", "Grease Quill", "3 Months", "15", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "9", "Fan", "3 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "i4-350L/450L/550L", "10", "J3 and J4 Belts", "Yearly", "20", "2021-05-04");
//
//                        //LD-250 type robot
//                        logindb.insertInOmron("0", "LD-250", "1", "LD-250 Information", "N/A", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "2", "Data Backup", "Daily", "10", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "4", "Operator Panel", "Daily", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "5", "Joystick", "3 Months", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "9", "Battery", "6 Months", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "10", "Tire & Motor", "6 Months", "25", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "11", "Caster", "3 Months", "25", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "12", "Time of Flight Sensor", "6 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "13", "Screw Retightening", "6 Months", "30", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "14", "Safety Commissioning", "Daily", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "15", "Docking Station", "3 Months", "15", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "16", "Cleaning", "Daily", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD-250", "17", "Operation Check", "N/A", "15", "2021-05-04");
//
//                        //LD type robot
//                        logindb.insertInOmron("0", "LD", "1", "LD Information", "N/A", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "2", "Data Backup", "Daily", "10", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "3", "Wi-fi Environment", "6 Months", "30", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "4", "Operator Panel", "Daily", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "5", "Joystick", "3 Months", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "6", "user Emergency Stop", "Daily", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "7", "Safety Scanning Laser", "6 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "8", "Low Level Laser", "6 Months", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "9", "Battery", "6 Months", "3", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "10", "Tire", "6 Months", "15", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "11", "Caster", "3 Months", "10", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "12", "Screw Retightening", "6 Months", "30", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "13", "Safety Commissioning", "Daily", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "14", "Docking Station", "3 Months", "15", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "15", "Cleaning", "Daily", "5", "2021-05-04");
//                        logindb.insertInOmron("0", "LD", "16", "Operation Check", "N/A", "15", "2021-05-04");

                        //code to new activity goes here
                        //uses source (mainActivity) and destination(HomePage) to go to new activity
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }


                }


            }
        });



    }

    private boolean validate(String name,String pass){
        if(name.equals(userName) && pass.equals(passWord)){
            return true;
        }
        else{
            return false;
        }

    }

}