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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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