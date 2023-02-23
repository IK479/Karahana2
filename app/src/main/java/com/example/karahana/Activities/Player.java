package com.example.karahana.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karahana.R;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player extends AppCompatActivity {

    private TextView title;
    private TextView songDuration;
    private Button manuBTN;
    private Button rateBTN;
    private Button cameraBTN;
    private Button albumBTN;
    private Button playBTN;
    private Button pauseBTN;
    private SeekBar seekBar;
    private ImageView image;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    double finalTime = 0;
    double startTime = 0;
    static int oneTimeOnly = 0;
    Handler handler = new Handler();
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //mediaPlayer = MediaPlayer.create(this, R.raw.song);
        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawer view
        mDrawer = findViewById(R.id.drawer_layout);

        // Drawer Navigation
        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        findView();
        camera();
//        click();
        play_song();

    }

    public void play_song(){
        mediaPlayer  = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/login-register-firebase-e74af.appspot.com/o/Maroon%205%20-%20Beautiful%20Mistakes%20ft.%20Megan%20Thee%20Stallion%20(Official%20Music%20Video).mp3?alt=media&token=a6624167-e439-4cbc-8acf-d79889a2d1e7");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    click();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
            mediaPlayer.prepare();
        }catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //selectDrawerItem(item);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void click() {
        seekBar.setContextClickable(false);
        playBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic();
                playAndPauseAppearance();
            }
        });

        pauseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                playAndPauseAppearance();

            }
        });
    }

    private void camera() {
        if (ContextCompat.checkSelfPermission(Player.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Player.this, new String[]{Manifest.permission.CAMERA}, 101);
        }

        cameraBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }

    @SuppressLint("DefaultLocale")
    private void playMusic(){
        prepareMediaPlayer();
        mediaPlayer.start();
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        if(oneTimeOnly == 0){
            seekBar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        songDuration.setText(String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes
                                ((long) finalTime))
        ));

        seekBar.setProgress((int) startTime);
        handler.postDelayed(UpdateSongTime, 100);
    }


    // Creating the Runnable
    private Runnable UpdateSongTime = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            songDuration.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
            ));
            seekBar.setProgress((int)startTime);
            handler.postDelayed(this, 100);
        }
    };


    private void playAndPauseAppearance(){
        if(mediaPlayer.isPlaying()){
            pauseBTN.setVisibility(View.VISIBLE);
            playBTN.setVisibility(View.INVISIBLE);
        }
        else {
            pauseBTN.setVisibility(View.INVISIBLE);
            playBTN.setVisibility(View.VISIBLE);
        }
    }


    private void findView(){
        title = findViewById(R.id.mediaPlayer_LBL_textView);
        songDuration = findViewById(R.id.mediaPlayer_LBL_songDuration);
        cameraBTN = findViewById(R.id.mediaPlayer_BTN_camera);
        //manuBTN = findViewById(R.id.mediaPlayer_BTN_menu);
        pauseBTN = findViewById(R.id.mediaPlayer_BTN_pause);
        playBTN = findViewById(R.id.mediaPlayer_BTN_play);
        albumBTN = findViewById(R.id.mediaPlayer_BTN_photosAlbum);
        //rateBTN = findViewById(R.id.mediaPlayer_BTN_starRate);
        seekBar = findViewById(R.id.mediaPlayer_seekbar);
    }



}