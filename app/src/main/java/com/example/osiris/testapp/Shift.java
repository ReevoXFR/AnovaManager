package com.example.osiris.testapp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * Created by Osiris on 11/14/2017.
 */

public class Shift implements Serializable {
    public int startHour, endHour, startMin, endMin, sdd, smm, syy, edd, emm, eyy;
    public String userKey, companyName;


    public Shift() {
    }

    public void setStartDateAndHour(int year, int month, int day, int hour, int minute) {
        sdd = day;
        smm = month;
        syy = year;
        startHour = hour;
        startMin = minute;

    }

    public void setEndDateAndHour(int year, int month, int day, int hour, int minute) {
        edd = day;
        emm = month;
        eyy = year;
        endHour = hour;
        endMin = minute;
    }

    public void setUserKey(String key) {
        userKey = key;
    }

    public void setCompanyName(String name) {
        companyName = name;
    }

    public String toString() {
        String s = "";
        s =  sdd + "/" + smm + "/" + syy +" " + startHour + ":"+ startMin + "  -  " + edd + "/" + emm + "/" + eyy + " " + endHour + ":" + endMin;
        return s;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public int getEndMin() {
        return endMin;
    }

    public int getSmm() {
        return smm;
    }

    public int getSyy() {
        return syy;
    }

    public int getEdd() {
        return edd;
    }

    public int getEmm() {
        return emm;
    }

    public int getEyy() {
        return eyy;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getSdd() {
        return sdd;
    }
}
