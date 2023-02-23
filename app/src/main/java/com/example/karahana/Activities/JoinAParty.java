package com.example.karahana.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.karahana.Adapters.EventsAdapter;
import com.example.karahana.Fragments.PartiesListFragment;
import com.example.karahana.Fragments.InfoFragment;
import com.example.karahana.Interfaces.CallBack_eventProtocol;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.PartyList;
import com.example.karahana.R;
import com.example.karahana.managers.PartyManager;

public class JoinAParty extends AppCompatActivity {

    private InfoFragment infoFragment;
    private PartiesListFragment eventsListFragment;

    CallBack_eventProtocol callBack_eventProtocol = new CallBack_eventProtocol() {
        @Override
        public void partyPos(PartyCard party) {
            showPartyDialog(party);
        }
        @Override
        public void partyEnter(PartyCard party) {
             //TODO
             //Toast.makeText(JoinAParty.this, "TODO!!!  Enter to party "+party.getPartyName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Player.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_aparty);


        infoFragment = new InfoFragment();
        eventsListFragment = new PartiesListFragment();

        eventsListFragment.setCallBack_userProtocol(callBack_eventProtocol);
        getSupportFragmentManager().beginTransaction().add(R.id.joinAParty_LAY_partiesList, eventsListFragment).commit();

    }


    public void showPartyDialog(PartyCard party){
        infoFragment.setParty(party);
        infoFragment.show(getSupportFragmentManager(), "InfoFragment");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(JoinAParty.this, MainActivity.class);
        startActivity(intent);
    }

}