package com.example.osiris.testapp;

/**
 * Created by Osiris on 3/11/2017.
 */

public class Employee {

    private String username;
    private String name;
    private int working_number;
    private String password;


    public Employee(String username, String password){
        this.username = username;
        this.password = password;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setWorking_number(int working_number){
        this.working_number = working_number;
    }
}
