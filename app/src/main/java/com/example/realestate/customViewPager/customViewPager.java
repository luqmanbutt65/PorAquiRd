package com.example.realestate.customViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.R;

import java.util.ArrayList;

public class customViewPager extends PagerAdapter {
    Context context;
ArrayList<PropertiesGallery> propertiesGalleryArrayList;
    LayoutInflater layoutInflater;


    public customViewPager(Context context, ArrayList<PropertiesGallery> propertiesGalleryArrayList) {
        this.context = context;
        this.propertiesGalleryArrayList = propertiesGalleryArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return propertiesGalleryArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_container, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageview);
        Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/"+propertiesGalleryArrayList.get(position).getProperty_images()).into(imageView);
        container.addView(itemView);
        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}