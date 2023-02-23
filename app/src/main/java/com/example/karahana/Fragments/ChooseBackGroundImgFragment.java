package com.example.karahana.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.karahana.Adapters.ImageAdapter;
import com.example.karahana.R;

import java.net.URI;

public class ChooseBackGroundImgFragment extends Fragment {
    private GridView gridView;
    private ImageView selectedImg;

    private static final int PICK_IMAGE_REQUEST = 1;
    private URI mImgUri;


    public ChooseBackGroundImgFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_background_img, container, false);

        findViews(view);
        initView();

        return view;
    }


    private void initView() {
        gridView.setAdapter(new ImageAdapter(getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ImageAdapter imageAdapter = new ImageAdapter(getContext());
                selectedImg.setImageResource(imageAdapter.imgStorage.get(pos));
            }
        });
    }

    private void findViews(View view) {
        gridView = view.findViewById(R.id.bgFragment_gridView);
    }
}