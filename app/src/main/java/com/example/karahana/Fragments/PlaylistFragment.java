package com.example.karahana.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.karahana.R;
import com.example.karahana.Adapters.SongAdapter;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.SongCard;
import com.example.karahana.managers.Models.SongsLibrary;
import com.example.karahana.managers.PartyManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;

public class PlaylistFragment extends Fragment {

    private SongAdapter adapter;
    private RecyclerView recyclerView;
    private PartyCard partyCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        dataInitialize();
        return view;
    }

    private void dataInitialize() {
        partyCard = PartyManager.getInstance().getNewParty();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        ArrayList<SongCard> playlist=PartyManager.getInstance().getSelectedPlaylist();
        adapter = new SongAdapter(this, playlist);
        recyclerView.setAdapter(adapter);
        adapter.setPlaylist(playlist);
      }

    @Override
    public void onPause() {
        super.onPause();
        partyCard.setPlayList(adapter.getSelectedSongs());
        if (partyCard.getPlayList().size()==0){
            Toast.makeText(getContext(), "No selection", Toast.LENGTH_SHORT).show();
        }
    }
}