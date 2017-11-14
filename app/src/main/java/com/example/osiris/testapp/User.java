package com.example.osiris.testapp;

import java.util.ArrayList;

/**
<<<<<<< Updated upstream
 * Created by Osiris on 3/11/2017.
=======
 * Created by DUMNEZO on 11/14/2017.
>>>>>>> Stashed changes
 */

public class User {

    private String email, name, key;
    private ArrayList<Company> ownerOf;
    private Company partOf;



    public User(String name, String email){  // in createUser se creeaza user care se baga in firebase

        this.email = email;
        this.name = name;
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

    public void employTo(Company company) {
        partOf=company;
    }

    public Company getPartOf() {
        return partOf;
    }

}
