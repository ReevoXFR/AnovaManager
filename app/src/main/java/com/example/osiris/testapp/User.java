package com.example.osiris.testapp;

/**
 * Created by Osiris on 3/11/2017.
 */

public class User {

    public String email;
    public String name;
    public String key;

    public User(String name, String email){

        this.email = email;
        this.name = name;
    }

    public User(){

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
}
