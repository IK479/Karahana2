package com.example.karahana.managers.Models;
import com.example.karahana.R;
import java.util.ArrayList;

public class SongsLibrary {

    private ArrayList<SongCard> library;

    public SongsLibrary(){
        library = new ArrayList<>();
        setLibrary();
    }

    public ArrayList<SongCard> getLibrary() {
        return library;
    }

    public void setLibrary() {
        library.add(s1);
        library.add(s2);
        library.add(s3);
        library.add(s4);
        library.add(s5);
    }

    SongCard s1 = new SongCard(R.drawable.img_arizona, "Arizona Zervas", "ROXANNE", "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Arizona%20Zervas%20%20ROXANNE%20Official%20Video.mp3?alt=media&token=fd0c10ab-a5e0-47fe-aad1-a83cf52b2bd2");
    SongCard s2 = new SongCard(R.drawable.img_megan, "Megan Thee Stallion", "Savage","https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Megan%20Thee%20Stallion%20%20Savage%20Lyric%20Video.mp3?alt=media&token=7e254cbf-2a85-4342-85f7-12ffac1a766d");
    SongCard s3 = new SongCard(R.drawable.img_astronaut, "Masked Wolf", "Astronaut In The Ocean", "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Masked%20Wolf%20%20Astronaut%20In%20The%20Ocean%20Official%20Music%20Video.mp3?alt=media&token=1712cd28-ae08-46dc-aec8-0985198b17f4");
    SongCard s4 = new SongCard(R.drawable.img_charly, "Charly Black ft.Daddy Yankee", "Party Animal", "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Charly%20Black%20Daddy%20Yankee%20%20Gyal%20You%20A%20Party%20Animal%20RemixAudio.mp3?alt=media&token=55423589-5d0f-4c7a-903d-8012e7d35708");
    SongCard s5 = new SongCard(R.drawable.img_maroon5, "Maroon5 ft.Megan Thee Stallion", " Beautiful Mistakes", "https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Maroon%205%20-%20Beautiful%20Mistakes%20ft.%20Megan%20Thee%20Stallion%20(Official%20Music%20Video).mp3?alt=media&token=a6624167-e439-4cbc-8acf-d79889a2d1e7");
}
