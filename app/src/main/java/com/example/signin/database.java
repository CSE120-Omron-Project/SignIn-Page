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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        db = new dbHelp(this);

        //uncomment this to deletee the table so there wont be any duplicates
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




        button = (Button)findViewById(R.id.button);

        //calls view parts
        viewParts();


//
//
//

//
//        if(insert == true){
//            Toast.makeText(getApplicationContext(),"INSERTED first DATA 10 ", Toast.LENGTH_LONG).show();
//        }

    }

    //This handles the button view all
    public void viewParts(){
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =  db.getAllParts(); //calls db object, get all parts is where the query is written

                        if(res.getCount() == 0){
                            //show message
                            showMessage("ERRORS","QUERY DIDNT WORK");
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("PART: "+ res.getString( 3) + "\n");

                        }

                        //show message
                        showMessage("DATA",buffer.toString());

                    }
                }
        );
    }


    //this is the pop up that shows up with the data
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }
}