package com.example.realestate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Model.ImagesData;
import com.example.realestate.R;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    Context context;
    ArrayList<ImagesData> ImagesArray;

    public ImagesAdapter(Context context, ArrayList<ImagesData> ImagesArray) {
        this.context = context;
        this.ImagesArray = ImagesArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_container, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.image(ImagesArray.get(position));

    }

    @Override
    public int getItemCount() {
        return ImagesArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgview1);
            linearLayout = itemView.findViewById(R.id.imagelayout);
        }

        public void image(ImagesData imagesData) {
            image.setImageURI(imagesData.getUri());
        }
    }
}

