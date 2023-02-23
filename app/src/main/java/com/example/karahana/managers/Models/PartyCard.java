package com.example.karahana.managers.Models;

import com.example.karahana.R;

import java.io.Serializable;
import java.util.ArrayList;

public final class PartyCard {

    private final int CALENDER_IMAGE = R.drawable.img_calender;

    private int calenderImg;
    private String partyName;
    private MyTime time;
    private String freeText;
    private boolean isPrivate;
    private String password;
    private int bgImage;
    private ArrayList<SongCard> playList;

    public boolean deleted;
    public String  uid;

    public PartyCard(){
        this.calenderImg = CALENDER_IMAGE;
        this.partyName = "";
        this.bgImage = 0;
        this.time = new MyTime(2023,01,01,0,0);
        this.freeText = "";
        this.password = "";
        this.playList = new ArrayList<SongCard>();

    }
    public PartyCard(String partyName, boolean isPrivate, int bgImage, MyTime time, int CALENDER_IMAGE, String freeText, String password) {
        this.playList = playList = new ArrayList<SongCard>();;
        this.calenderImg = CALENDER_IMAGE;
        this.partyName = partyName;
        this.bgImage = bgImage;
        this.isPrivate = isPrivate;
        this.time = time;
        this.freeText = freeText;
        this.password = password;
    }
    public boolean getIsPrivate() {
      return   isPrivate;
    }
    public String isPrivate() {
        if (isPrivate)
            return "PRIVATE";
        else
            return "PUBLIC";
    }
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getText() {
        return freeText;
    }
    public void setText(String text) {
        this.freeText = text;
    }

    public String getPartyName() {
        return partyName;
    }
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }


    public int getCalenderImg() {
        return calenderImg;
    }
    public void setCalenderImg(int calenderImg) {
        this.calenderImg = calenderImg;
    }


    public MyTime getTime() {
        return time;
    }
    public void setTime(MyTime time) {
        this.time = time;
    }


    public int getBgImage() {
        return bgImage;
    }
    public void setBgImages(int bgImage) {
        this.bgImage = bgImage;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<SongCard> getPlayList() {
        return playList;
    }

    public void setPlayList(ArrayList<SongCard> playList) {
        this.playList = playList;
    }
}
