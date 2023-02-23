package com.example.karahana.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.karahana.R;
import com.example.karahana.managers.PartyManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button backButton;
    private ImageButton iButtonCreateParty;
    private ImageButton iButtonJoinAParty;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        click();
    }

    private void click(){
        iButtonCreateParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PartyManager.getInstance().startNewParty();
                Intent intent = new Intent(getApplicationContext(), CreateAParty.class);
                startActivity(intent);
                finish();
            }
        });

        iButtonJoinAParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinAParty.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void findView(){
        iButtonCreateParty = findViewById(R.id.main_img_createParty);
        iButtonJoinAParty = findViewById(R.id.main_img_joinToParty);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

}

