package com.example.karahana.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.karahana.Activities.Login;
import com.example.karahana.managers.Models.Image;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.R;
import com.example.karahana.managers.Models.appManager;
import com.example.karahana.managers.PartyManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PartyFragmentNew extends Fragment implements TimePickerDialog.OnTimeSetListener,  DatePickerDialog.OnDateSetListener {
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private EditText textEditText;
    private TextView dateEditText;
    private SwitchCompat switchCompat;
    private EditText partyName;
    private TextView timeEditText;
    private EditText passwordEditText;

    private final int SELECT_IMAGE_CODE = 1;
    private final String PICK_TIME_MESSAGE = "Pick Time:";
    private final String PICK_DATE_MESSAGE = "Pick Date:";
    private static final int PICK_IMAGE_REQUEST = 1;
    private final String MISS_VALUE_MESSAGE = "One of the values is missing!";

    private PartyCard partyCard;
    private Image image = new Image();
    public enum Type {PUBLIC, PRIVATE};
    public appManager appManager = new appManager();



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_party, container, false);
        initViews(view);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        partyCard = PartyManager.getInstance().getNewParty();
        setBackGroundImage();
        initListeners();
        updateUI();
    }

    private void updateUI() {
        textEditText.setText(partyCard.getText());
        dateEditText.setText(partyCard.getTime().getFullDate());
        timeEditText.setText(partyCard.getTime().getFullTime());
        passwordEditText.setText(partyCard.getPassword());
    }


    private void initListeners(){
        partyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                partyCard.setPartyName(editable.toString());
            }
        });

        textEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                partyCard.setText(editable.toString());
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    partyCard.setPrivate(true);
                    passwordEditText.setEnabled(true);
                }
                if(!isChecked){
                    passwordEditText.getText().clear();
                    passwordEditText.setEnabled(false);
                }
                updateUI();
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                partyCard.setPassword(editable.toString());
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFrag = new TimePickerFragment();
                timePickerFrag.setListener(PartyFragmentNew.this);
                timePickerFrag.show(getParentFragmentManager(), PICK_TIME_MESSAGE);
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFrag = new DatePickerFragment();
                datePickerFrag.setListener(PartyFragmentNew.this);
                datePickerFrag.show(getParentFragmentManager(), PICK_DATE_MESSAGE);
            }
        });
    }


    private void initViews(View view){
        partyName = view.findViewById(R.id.pFragment_pName);
        timeEditText = view.findViewById(R.id.pFragment_time);
        dateEditText = view.findViewById(R.id.pFragment_date);
        textEditText = view.findViewById(R.id.pFragment_text);
        passwordEditText = view.findViewById(R.id.pFragment_password);
        switchCompat = view.findViewById(R.id.pFragment_switch);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        partyCard.getTime().setYear(year);
        partyCard.getTime().setMonth(month+1);
        partyCard.getTime().setDay(day);
        updateUI();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        partyCard.getTime().setHour(hour);
        partyCard.getTime().setMinutes(minute);
        updateUI();
    }

    public void setBackGroundImage(){
        partyCard.setBgImages(appManager.backgroundImage());
    }

    public PartyCard getPartyCard(){
        return partyCard;
    }

}