package com.example.karahana.managers.Models;

import android.util.Log;

import com.example.karahana.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Image {

     int random;
     ArrayList<Integer> bgImages;

    public Image() {
        bgImages = new ArrayList<>();
        bgImages.add(R.drawable.img_party);
        bgImages.add(R.drawable.img_new_year_party);
        bgImages.add(R.drawable.img_join_to_party);
    }

    public Integer getBgImages() {
        random = new Random().nextInt((2 - 0 + 1) + 0);
        return bgImages.get(random);
    }



}
