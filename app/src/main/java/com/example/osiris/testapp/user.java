package com.example.osiris.testapp;

/**
 * Created by Osiris on 3/11/2017.
 */

public class user {

    public String email;
    public String name;
    public String key;

    public user(String name, String email){

        this.email = email;
        this.name = name;
    }

    public user(){
        this.email = email;


    }

    public void setName(String name)
    {
        this.email = name;
    }
    public String getName(){
        return email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
