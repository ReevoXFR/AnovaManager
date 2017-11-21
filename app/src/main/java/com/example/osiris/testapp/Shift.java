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
   // public GregorianCalendar startHour;
   // public GregorianCalendar EndHour;
    public int startHour, endHour, startMin, endMin, sdd,smm,syy, edd,emm,eyy;


    public Shift() {
        //startHour = new GregorianCalendar();
        //EndHour = new GregorianCalendar();
    }

//    public Shift(int day, int month, int year, int hour, int minute) {
//        startHour.set(year, month, day, hour, minute);
//    }

    public void setStartDateAndHour(int year, int month, int day, int hour, int minute) {
        sdd = day;
        smm=month;
        syy=year;
        startHour=hour;
        startMin=minute;

    }

    public void setEndDateAndHour(int year, int month, int day, int hour, int minute) {
        edd = day;
        emm=month;
        eyy=year;
        endHour=hour;
        endMin=minute;
    }

//    @Exclude
//    public GregorianCalendar getStartHour() {
//        return startHour;
//    }
//
//    @Exclude
//    public GregorianCalendar getEndHour() {
//        return EndHour;
//    }

}
