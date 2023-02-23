package com.example.karahana.firebase;


import com.example.karahana.managers.Models.PartyCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Parties extends HashMap<String, PartyCard> {
    public Parties(PartyCard[] arr){
        if(arr==null){
            return;
        }

        for(PartyCard g:arr){
            this.put(g.uid,g);
        }
    }

    public PartyCard[] asArray(){
        Collection<PartyCard> values = values();

        PartyCard[] res=new PartyCard[values.size()];
        res=values.toArray(res);

        return res;
    }
}