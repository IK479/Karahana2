package com.example.karahana.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.karahana.Adapters.EventsAdapter;
import com.example.karahana.Interfaces.CallBack_eventProtocol;
import com.example.karahana.managers.Models.MyTime;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.PartyList;
import com.example.karahana.R;

public class PartiesListFragment extends Fragment {

    private Button eventEnterButton;
    private RecyclerView recyclerView;
    private EventsAdapter eventAdapter;
    private PartyList partiesList;
    private CallBack_eventProtocol callBack_eventProtocol;


    public void setCallBack_userProtocol(CallBack_eventProtocol callBack_userProtocol){
        this.callBack_eventProtocol = callBack_userProtocol;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partieslist, container, false);

        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    public void findViews(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        eventEnterButton = view.findViewById(R.id.partyCard_btn_enterToEvent);
    }

    public void initViews(){
        partiesList = new PartyList();
        addPartyEvent();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        eventAdapter = new EventsAdapter(getContext(), partiesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(eventAdapter);
        eventAdapter.setCallBack_eventProtocol(callBack_eventProtocol);

    }

    public void addPartyEvent(){
        partiesList.addParty(new PartyCard("New Year Party", false, R.drawable.img_new_year_party,
                new MyTime(2023, 7, 12, 21, 30), R.drawable.img_calender, "It's gonna be so cool!", ""));
    }

}