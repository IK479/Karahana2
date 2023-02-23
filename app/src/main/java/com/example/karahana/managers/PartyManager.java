package com.example.karahana.managers;
import android.content.Context;
import android.widget.Toast;

import com.example.karahana.Activities.Login;
import com.example.karahana.R;
import com.example.karahana.managers.Models.MyTime;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.PartyList;
import com.example.karahana.managers.Models.SongCard;
import com.example.karahana.managers.Models.SongsLibrary;

import java.util.ArrayList;

public class PartyManager {
    private static PartyManager instance;
    PartyList partiesList;
    public static PartyManager getInstance() {
        if (instance == null) {
            instance = new PartyManager();
        }
        return instance;
    }
    private PartyManager() {
        partiesList = new PartyList();
        addPartyEvent();
    }

    public void addPartyEvent(){
            partiesList.addParty(new PartyCard("New Year Party", false, R.drawable.img_new_year_party,
                    new MyTime(2023, 7, 12, 21, 30), R.drawable.img_calender, "It's gonna be so cool!", ""));
        }


    PartyCard newParty;

    public void startNewParty() {
        newParty = new PartyCard();
        newParty.setPlayList(new SongsLibrary().getLibrary());
    }

    public PartyCard getNewParty() {
        return newParty;
    }

    public boolean addNewParty(Context context){
        ArrayList<SongCard> selectedPlaylist=getOnlySelectedPlaylist(newParty.getPlayList());
        if(newParty.getPartyName().isEmpty()){
            Toast.makeText(context, "Party name is empty!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(selectedPlaylist.size() == 0){
            Toast.makeText(context, "Play list is empty!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        partiesList.addParty(newParty);
        startNewParty();
        return true;
    }
    public ArrayList<SongCard> getOnlySelectedPlaylist(ArrayList<SongCard> list) {
        ArrayList<SongCard> res = new ArrayList<>();
        for (SongCard s:  list ) {
            if(s.isChecked()){
                res.add(s);
            }
        }

        return res;
    }

    public PartyList getPartiesList() {
        return partiesList;
    }
}
