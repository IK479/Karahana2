package com.example.karahana.managers.Models;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class appManager {

    MediaPlayer mediaPlayer;
    Image storage = new Image();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference();

    public appManager() {}


    public void Player(SongCard song){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(song.getUrl());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }


    public int backgroundImage(){
       return storage.getBgImages();
    }


}
