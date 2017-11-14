package com.example.osiris.testapp;

import java.util.ArrayList;

/**
 * Created by DUMNEZO on 11/14/2017.
 */

public class Company {

    private ArrayList<User> employees;
    private User owner;
    private String key, name;

    public Company(User owner, String name, String key) {
        this.owner = owner;
        this.name = name;
        this.key = key;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
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
