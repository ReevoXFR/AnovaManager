package com.example.osiris.testapp;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * Created by Osiris on 11/14/2017.
 */

public class Shift {
    private GregorianCalendar startHour;
    private GregorianCalendar EndHour;
    private Timer t;

    public Shift() {
    }

    public void setStartDateAndHour(int day, int month, int year, int hour, int minute) {
        this.startHour.set(year, month, day, hour, minute);
    }

    public void setEndDateAndHour(int day, int month, int year, int hour, int minute) {
        this.EndHour.set(year, month, day, hour, minute);
    }
}
