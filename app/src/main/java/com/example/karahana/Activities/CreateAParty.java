package com.example.karahana.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.karahana.Fragments.PartyFragmentNew;
import com.example.karahana.Fragments.PlaylistFragment;
import com.example.karahana.Fragments.ShareFragment;
import com.example.karahana.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CreateAParty extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    BottomNavigationView bottomToolBar;
    BottomNavigationView bottomNavigationView;
    PartyFragmentNew partyFragment = new PartyFragmentNew();
    ShareFragment shareFragment = new ShareFragment();
    PlaylistFragment playlistFragment = new PlaylistFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        bottomNavigationView = findViewById(R.id.createAParty_btn_navigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.party);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.party:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.createAParty_prefrencesFragment, partyFragment).commit();
                return true;

            case (R.id.playlist):
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.createAParty_prefrencesFragment, playlistFragment).commit();
                return true;

            case R.id.share:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.createAParty_prefrencesFragment, shareFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreateAParty.this, MainActivity.class);
        startActivity(intent);
    }

}


