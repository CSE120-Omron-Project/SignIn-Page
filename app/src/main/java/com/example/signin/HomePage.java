package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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




    //hard coded data to test (LD ROBOT)
    private String testNum = "7540";
    private String testModel = "LD";
    private String testDate = "4621";

    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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

                if(inputSerial.isEmpty()|| inputModel.isEmpty()){

                    // Display a message toast to user to enter the details
                    Toast.makeText(HomePage.this, "Please enter serial and model number!", Toast.LENGTH_LONG).show();
                }
                else{
                    isValid = validate(inputSerial,inputModel,inputDate);

                    if(!isValid){
                        Toast.makeText(HomePage.this, "Serial or Model is wrong", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(HomePage.this, "Login successful", Toast.LENGTH_LONG).show();

                        /*calling robotData class, have to put code here to print out given
                          serial number and model, if put in robotData.java we would lose the user input bc
                          inputModel and inputSerial are out of scope.
                         */

                        // calling robot xml file
                        setContentView(R.layout.activity_robot_data);

                        TextView textViewModel = (TextView) findViewById(R.id.textView2);
                        textViewModel.setText("Robot Model: " + inputModel);



                        TextView textViewSerial = (TextView) findViewById(R.id.textView3);
                        textViewSerial.setText("Robot Serial: " + inputSerial);

                        TextView textViewDate = (TextView) findViewById(R.id.textView4);
                        textViewDate.setText("Date Deployed: " + inputDate);




                    }
                }



            }
        });

        next = findViewById(R.id.btnNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, robotData.class);
                startActivity(intent);

            }
        });






        //hyper link code
//        TextView link  = (TextView)findViewById(R.id.textViewLink);
//        link.setMovementMethod(LinkMovementMethod.getInstance());
//        Intent intent = new Intent(HomePage.this, robotData.class);
//        startActivity(intent);


    }

    private boolean validate(String number,String model,String date){
        if(number.equals(testNum) && model.equals(testModel) && date.equals(testDate) ){
            return true;
        }
        else{
            return false;
        }

    }
}