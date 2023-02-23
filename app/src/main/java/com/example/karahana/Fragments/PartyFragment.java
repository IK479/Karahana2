package com.example.karahana.Fragments;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.example.karahana.R;

public class PartyFragment extends Fragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    private EditText textEditText;
    private EditText dateEditText;
    private SwitchCompat switchCompat;
    private EditText partyName;
    private EditText timeEditText;
    private EditText passwordEditText;
    private Button chooseImg;

    //private GridView gridView;

    private final int SELECT_IMAGE_CODE = 1;
    private final String PICK_TIME_MESSAGE = "Pick Time:";
    private final String PICK_DATE_MESSAGE = "Pick Date:";
    private static final int PICK_IMAGE_REQUEST = 1;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_party, container, false);
        findView(view);
        click(view);

        return view;
    }


    private void click(View view){
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passwordEditText.setEnabled(true);
                } else {
                    passwordEditText.getText().clear();
                    passwordEditText.setEnabled(false);
                }
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFrag = new TimePickerFragment();
                timePickerFrag.setListener(PartyFragment.this);
                timePickerFrag.show(getParentFragmentManager(), PICK_TIME_MESSAGE);
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFrag = new DatePickerFragment();
                datePickerFrag.setListener(PartyFragment.this);
                datePickerFrag.show(getParentFragmentManager(), PICK_DATE_MESSAGE);
            }
        });

        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ChooseBackGroundImgFragment imgFragment = new ChooseBackGroundImgFragment();
                fragmentTransaction.replace(R.id.createAParty_prefrencesFragment, imgFragment);
                fragmentTransaction.commit();
            }
        });

    }


    private void findView(View view){
       partyName           = view.findViewById(R.id.pFragment_pName);
        timeEditText           = view.findViewById(R.id.pFragment_time);
        dateEditText          = view.findViewById(R.id.pFragment_date);
        textEditText              = view.findViewById(R.id.pFragment_text);
        passwordEditText           = view.findViewById(R.id.pFragment_password);
        switchCompat          = view.findViewById(R.id.pFragment_switch);
       // chooseImg         = view.findViewById(R.id.pFragment_BTN_chooseImg);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        timeEditText.setText(hour + ":" + minute);

    }
}