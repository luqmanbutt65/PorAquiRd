package com.example.realestate.customViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private ArrayList<PropertiesGallery> propertiesGalleryArrayList = new ArrayList<>();

    public SliderAdapterExample(Context context, ArrayList<PropertiesGallery> propertiesGalleryArrayList) {
        this.context = context;
        this.propertiesGalleryArrayList = propertiesGalleryArrayList;
    }

    public void renewItems(ArrayList<PropertiesGallery> propertiesGalleryArrayList) {
        this.propertiesGalleryArrayList = propertiesGalleryArrayList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.propertiesGalleryArrayList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(PropertiesGallery propertiesGallery) {
        this.propertiesGalleryArrayList.add(propertiesGallery);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        PropertiesGallery propertiesGallery = propertiesGalleryArrayList.get(position);

        Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + propertiesGalleryArrayList.get(position).getProperty_images()).into(viewHolder.imageViewBackground);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return propertiesGalleryArrayList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);

            this.itemView = itemView;
        }
    }

}