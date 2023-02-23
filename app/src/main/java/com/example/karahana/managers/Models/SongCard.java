package com.example.karahana.managers.Models;

import java.util.UUID;

public class SongCard {
    private UUID uid;
    private int artistImg;
    private String artist;
    private String songName;
    private String url;
    private Boolean isChecked = false;
    private Boolean play = true;

    public SongCard(){ this.uid=UUID.randomUUID();
    }

    public SongCard(int artistImg, String artist, String song, String url) {
        this.uid=UUID.randomUUID();
        this.artistImg = artistImg;
        this.artist = artist;
        this.songName = song;
        this.url = url;
        this.isChecked = true;
    }

    public int getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(int artistImg) {
        this.artistImg = artistImg;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }


    public Boolean IsPlay() {
        return play;
    }
    public void setPlay(Boolean play) {
        this.play = play;
    }

    public boolean equals(SongCard songCard){
        return songCard != null && songCard.uid==uid;
    }
}
