package com.example.osiris.testapp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
<<<<<<< Updated upstream
 * Created by Osiris on 3/11/2017.
=======
 * Created by DUMNEZO on 11/14/2017.
>>>>>>> Stashed changes
 */

public class User implements Serializable {

    private String email, name, key, companyOwner;
    private ArrayList<Company> ownerOf;
    private ArrayList<Shift> shifts;
    private String partOf;
    private String companyKey;
    private String companyName;

    public User(){
        shifts = new ArrayList<Shift>();
        ownerOf = new ArrayList<Company>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User(String name, String email){  // in createUser se creeaza user care se baga in firebase

        this.email = email;
        this.name = name;
        shifts = new ArrayList<Shift>();
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email= email;
    }
    public String getName(){

        return name;
    }
    public void setCompanyKey(String key){this.companyKey = key;}
    public String getCompanyKey(){return companyKey;}

    public String getEmail(){
        return email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addCompany(Company company) {
        ownerOf.add(company);
    }

    public ArrayList<Company> getCompanies() {
        return ownerOf;
    }

    public void employTo(String companyKey) {
        this.partOf=companyKey;
    }

    public String getPartOf() {
        return partOf;
    }


    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public void removeShift(Shift shift) {
        shifts.remove(shift);
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public void setCompanyOwner(String key) {
        companyOwner = key;
    }

    public void removeAllShifts(){shifts = null;}

    public String getCompanyOwner() {
        return companyOwner;
    }

}
