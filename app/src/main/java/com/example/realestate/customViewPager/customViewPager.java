package com.example.realestate.customViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.R;

import java.util.ArrayList;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

public class customViewPager extends PagerAdapter {
    Context context;
    ArrayList<PropertiesGallery> propertiesGalleryArrayList;
    LayoutInflater layoutInflater;
    public Clickslider clickslider;

    public customViewPager(Context context, ArrayList<PropertiesGallery> propertiesGalleryArrayList, Clickslider clickslider) {
        this.context = context;
        this.propertiesGalleryArrayList = propertiesGalleryArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickslider = clickslider;
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
        ImageViewZoom imageView = itemView.findViewById(R.id.imageview);
        Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + propertiesGalleryArrayList.get(position).getProperty_images()).into(imageView);
        container.addView(itemView);
        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickslider.change(v);
            }
        });


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


    public interface Clickslider {
        public void change(View view);
    }
}