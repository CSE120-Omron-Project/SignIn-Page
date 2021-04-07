package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class robotData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_data);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("NEW activity");


    }
}