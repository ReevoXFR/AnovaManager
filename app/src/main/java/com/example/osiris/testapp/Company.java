package com.example.osiris.testapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DUMNEZO on 11/14/2017.
 */

public class Company implements Serializable {

    private ArrayList<User> employees;
    private String ownerKey;
    private String key, name;

    public Company(String name, String ownerKey) {
        this.name = name;
        this.ownerKey = ownerKey;
        employees = new ArrayList<>();

    }
    public Company(){

    }

    public void setOwnerKey(User owner) {
        this.ownerKey = ownerKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return ownerKey;
    }

    public String getName() {
        return name;
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }

    public void removeEmployee(User employee) {
        employees.remove(employee);
    }

    public ArrayList<User> getEmployees() {
        ArrayList<User> temp = new ArrayList<User>();
        for(int i=0;i<employees.size();i++) {
            temp.add(employees.get(i));
        }
        return temp;
    }
}
