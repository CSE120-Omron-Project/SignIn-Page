package com.example.signin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class database extends AppCompatActivity {

    private dbHelp db ;
    private Button button;

    private Button btnCheck;
    private Button btnChoice;

    private EditText serialNum;
    private EditText robotModel;
    private EditText partName;
    private EditText dateChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        db = dbHelp.getInstance(this);
        serialNum = findViewById(R.id.selectSerial);
        robotModel = findViewById(R.id.selectRobot);
        partName = findViewById(R.id.selectPart);
        dateChoice = findViewById(R.id.selectDate);

        button = (Button)findViewById(R.id.getDate);
        viewDate();

        btnCheck = (Button)findViewById(R.id.updateCheck);
        todayCheck();

        btnChoice = (Button)findViewById(R.id.updateChoice);
        choiceCheck();

    }

    public void todayCheck(){
        btnCheck.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputSerial = serialNum.getText().toString();
                        String inputRobot = robotModel.getText().toString();
                        String inputPart = partName.getText().toString();

                        db.updateCheckToday(inputSerial, inputRobot, inputPart);
                        Toast.makeText(database.this, "Updated Date to Today!", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void choiceCheck(){
        btnChoice.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputSerial = serialNum.getText().toString();
                        String inputRobot = robotModel.getText().toString();
                        String inputPart = partName.getText().toString();
                        String inputDate = dateChoice.getText().toString();

                        db.updateCheckChoice(inputSerial, inputRobot, inputPart, inputDate);
                        Toast.makeText(database.this, "Updated Date to Selected!", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewDate(){
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputSerial = serialNum.getText().toString();
                        String inputRobot = robotModel.getText().toString();
                        String inputPart = partName.getText().toString();
                        Cursor res =  db.getCheck(inputSerial, inputRobot, inputPart); //calls db object, get all parts is where the query is written

                        if(res.getCount() == 0){
                            //show message
                            showMessage("ERRORS","QUERY DID NOT WORK");
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Date: "+ res.getString( 6) + "\n");

                        }

                        //show message
                        showMessage("Last Date Checked",buffer.toString());

                    }
                }
        );
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