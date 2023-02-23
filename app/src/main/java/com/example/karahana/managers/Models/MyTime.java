package com.example.karahana.managers.Models;

import java.text.DateFormatSymbols;

public class MyTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;


    public MyTime(int year, int month, int day, int hour, int minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        String monthStr = new DateFormatSymbols().getMonths()[month-1];
        return monthStr;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getFullTime() {
        return String.format("%02d:%02d", getHour() , getMinutes());
    }
    public String getFullDate() {
         return String.format("%02d/%02d/%04d", day ,month, year);
    }
}
