package com.example.signin;

public class robotModel {

    private int serialNumber =0;
    private String robot ="";
    private int procedure =0;
    private String part ="";
    private int time =0;
    private String check ="";

    //constructor


    public robotModel(int serialNumber, String robot, int procedure, String part, int time, String check) {
        this.serialNumber = serialNumber;
        this.robot = robot;
        this.procedure = procedure;
        this.part = part;

        this.time = time;
        this.check = check;

    }

    public robotModel(){
    }


    //need toString to print contents of object
    @Override
    public String toString() {
        return "robotModel{" +
                "serialNumber=" + serialNumber +
                ", robot='" + robot + '\'' +
                ", procedure=" + procedure +
                ", part='" + part + '\'' +
                ", time=" + time +
                ", check='" + check + '\'' +
                '}';
    }

    //getters
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getRobot() {
        return robot;
    }

    public int getProcedure() {
        return procedure;
    }

    public String getPart() {
        return part;
    }

    public int getTime() {
        return time;
    }

    public String getCheck() {
        return check;
    }


    //setters
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public void setProcedure(int procedure) {
        this.procedure = procedure;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
