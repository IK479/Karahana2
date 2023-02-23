package com.example.karahana.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karahana.Interfaces.CallBack_eventProtocol;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.PartyList;
import com.example.karahana.R;

public class InfoFragment extends DialogFragment {

    private CallBack_eventProtocol CallBack_eventProtocol;
    private RecyclerView recyclerView;
    private PartyList partyList;
    private PartyCard partyCard;

    private TextView name;
    private TextView date;
    private TextView time;
    private TextView type;
    private TextView freeText;
    private Button exit;

    public InfoFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partyinfo, container, false);
        findViews(view);
        partyInfo();
        click();

        return view;
    }



    private void click() {
         exit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getDialog().dismiss();
             }
         });
    }


    private void findViews(View view){
        name = view.findViewById(R.id.infoFragment_LBL_partyName);
        date = view.findViewById(R.id.infoFragment_LBL_date);
        time = view.findViewById(R.id.infoFragment_LBL_time);
        type = view.findViewById(R.id.infoFragment_LBL_type);
        freeText = view.findViewById(R.id.infoFragment_LBL_freeText);
        exit = view.findViewById(R.id.infoFragment_BTN_exit);
    }

    @SuppressLint("SetTextI18n")
    public void partyInfo(){
        name.setText("Party Name: " + partyCard.getPartyName());
        date.setText("Date: " + partyCard.getTime().getDay() + " " + partyCard.getTime().getMonth() + ", " + partyCard.getTime().getYear());
        time.setText("Time: " + partyCard.getTime().getHour() + ":" + partyCard.getTime().getMinutes());
        type.setText("Type: " + partyCard.isPrivate());
        freeText.setText("Free Text: " + partyCard.getText());
    }

    public void setParty(PartyCard party){
        this.partyCard = party;
    }

}
