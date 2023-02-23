package com.example.karahana.managers.Models;


import java.net.URL;
import java.util.ArrayList;

public class PartyList {

    private ArrayList<PartyCard> partiesList = new ArrayList<>();

    public PartyList() {}

    public ArrayList<PartyCard> getPartiesList() {
        return partiesList;
    }

    public PartyList setPartiesList(ArrayList<PartyCard> partiesList) {
        this.partiesList = partiesList;
        return this;
    }

    public PartyList addParty(PartyCard party){
        partiesList.add(party);
        return this;
    }

}
