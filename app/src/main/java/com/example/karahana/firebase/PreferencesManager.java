package com.example.karahana.firebase;

import android.content.Context;

import com.example.karahana.managers.Models.PartyCard;
import com.google.gson.Gson;

public class PreferencesManager extends PreferencesBase {
    private static PreferencesManager instance;
    public static PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context, "Parties");
        }

        return instance;
    }

    private PreferencesManager(Context context, String prefKey) {
        super(context, prefKey);
    }

    public void saveParties(Parties parties){
        if(parties==null || parties.size()==0){
            putAsync("Parties","");
        }else {
            putAsync("Parties", new Gson().toJson(parties.asArray()));
        }
    }

    public Parties getParties() {
        String j= get("Parties","");
        if(j.length()==0){
            return new Parties(null);
        }

        PartyCard[] res = new Gson().fromJson (j,(new PartyCard[]{}).getClass());
        return new Parties(res);
    }
}
