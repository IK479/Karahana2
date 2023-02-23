package com.example.karahana.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.karahana.Interfaces.CallBack_eventProtocol;
import com.example.karahana.Interfaces.CallBack_imgProtocol;
import com.example.karahana.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<Integer> imgStorage;


    private void addImagesToStorage(){
        imgStorage.add(R.drawable.img_party);
        imgStorage.add(R.drawable.img_new_year_party);
        imgStorage.add(R.drawable.img_join_to_party);
    }


    public ImageAdapter(Context context) {
        this.context = context;
        imgStorage = new ArrayList<>();
        addImagesToStorage();
    }

    @Override
    public int getCount() {
        return imgStorage.size();
    }

    @Override
    public Object getItem(int pos) {
        return imgStorage.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imgStorage.get(pos));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(1500, 600));

        return imageView;
        //.setOnClickListener(view1 -> callBack_imgProtocol.imgPos(imgStorage.get())));
    }

}
