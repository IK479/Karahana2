package com.example.karahana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karahana.R;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.PartyManager;

public class ShareFragment extends Fragment {

    private PartyCard partyCard = PartyManager.getInstance().getNewParty();
    private Button shareButton;
    private ImageView partyImg;
    private TextView partyName;
    private TextView day;
    private TextView month;
    private TextView isPrivate;
    private TextView time;
    private ImageView calenderImg;
    private Button enterToEvent;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        findViews(view);
        partyInfo();
        return view;
    }

    private void findViews(View view) {
        partyImg = view.findViewById(R.id.partyCard_cardViewImg);
        partyName = view.findViewById(R.id.partyCard_partyName);
        day = view.findViewById(R.id.partyCard_day);
        month = view.findViewById(R.id.partyCard_month);
        time = view.findViewById(R.id.partyCard_time);
        isPrivate = view.findViewById(R.id.partyCard_type);
        calenderImg = view.findViewById(R.id.partyCard_ic_calender);
        enterToEvent = view.findViewById(R.id.partyCard_btn_enterToEvent);
        shareButton = view.findViewById(R.id.sFragment_BTN_share);
    }

    public void partyInfo() {
//        partyImg.setImageResource(partyCard.getBgImage());
        partyName.setText(partyCard.getPartyName());
        day.setText(partyCard.getTime().getDay());
        month.setText(partyCard.getTime().getMonth());
        time.setText(partyCard.getTime().getFullTime());
        isPrivate.setText(partyCard.isPrivate());
        calenderImg.setImageResource(partyCard.getCalenderImg());
    }

    public void shareData(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "username");
        i.putExtra(Intent.EXTRA_TEXT, "randomNum");
        startActivity((Intent.createChooser(i, "Choose a platform")));

    }
}