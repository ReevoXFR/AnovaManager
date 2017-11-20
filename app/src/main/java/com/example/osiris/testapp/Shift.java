package com.example.osiris.testapp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * Created by Osiris on 11/14/2017.
 */

public class Shift {
    private GregorianCalendar startHour;
    private GregorianCalendar EndHour;


    public Shift() {
        startHour = new GregorianCalendar();
        EndHour = new GregorianCalendar();
    }

//    public Shift(int day, int month, int year, int hour, int minute) {
//        startHour.set(year, month, day, hour, minute);
//    }

    public void setStartDateAndHour(int year, int month, int day, int hour, int minute) {
        this.startHour.set(year, month, day, hour, minute);
    }

    public void setEndDateAndHour(int year, int month, int day, int hour, int minute) {
        this.EndHour.set(year, month, day, hour, minute);
    }

    public GregorianCalendar getStartHour() {
        return startHour;
    }

    public GregorianCalendar getEndHour() {
        return EndHour;
    }

}
