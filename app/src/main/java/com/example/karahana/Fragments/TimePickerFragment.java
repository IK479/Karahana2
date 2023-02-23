package com.example.karahana.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private int hour;
    private int minute;
    private Context context;
    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(context, mListener, hour, minute, DateFormat.is24HourFormat(context));

    }

    public void setListener(TimePickerDialog.OnTimeSetListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

    }

}
