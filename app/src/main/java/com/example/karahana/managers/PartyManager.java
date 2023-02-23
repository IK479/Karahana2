package com.example.karahana.managers;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.SongCard;
import com.example.karahana.managers.Models.SongsLibrary;

import java.util.ArrayList;

public class PartyManager {
    private static PartyManager instance;

    public static PartyManager getInstance() {
        if (instance == null) {
            instance = new PartyManager();
        }
        return instance;
    }
//    private PartyManager(){}

    PartyCard newParty;

    public void startNewParty() {
        newParty = new PartyCard();
    }

    public PartyCard getNewParty() {
        return newParty;
    }

    public ArrayList<SongCard> getSelectedPlaylist() {
        SongsLibrary songsLibrary = new SongsLibrary();
        ArrayList<SongCard> playlist = songsLibrary.getLibrary();
        for (SongCard song : playlist) {
            song.setChecked(false);
            for (SongCard selected : playlist) {
                if (song.equals(selected)) {
                    song.setChecked(true);
                    break;
                }
            }
        }

        return playlist;
    }
}
