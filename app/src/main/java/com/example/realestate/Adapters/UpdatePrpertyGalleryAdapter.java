package com.example.realestate.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Adddata;
import com.example.realestate.Activities.UpdateData;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.R;

import java.util.ArrayList;

public class UpdatePrpertyGalleryAdapter extends RecyclerView.Adapter<UpdatePrpertyGalleryAdapter.ViewHolder> {

    Context context;
    ArrayList<PropertiesGallery> propertiesGalleryArrayList;

    UpdateData updateData;

    public UpdatePrpertyGalleryAdapter(Context context, ArrayList<PropertiesGallery> ImagesArray, UpdateData updateData) {
        this.context = context;
        this.propertiesGalleryArrayList = ImagesArray;
        this.updateData = updateData;
    }

    @Override
    public UpdatePrpertyGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_container, viewGroup, false);
        return new UpdatePrpertyGalleryAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(UpdatePrpertyGalleryAdapter.ViewHolder holder, final int position) {
        holder.image(propertiesGalleryArrayList.get(position));
        holder.image.setLongClickable(true);

        holder.deletephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
//                .setIcon(R.drawable.delete)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //remove from array list and notifydata set change
                                propertiesGalleryArrayList.remove(position);
                                updateData.removeFromImagearray(position);
                                notifyDataSetChanged();
                                //dismiss dialog
                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        })
                        .create();
                myQuittingDialogBox.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return propertiesGalleryArrayList.size();
    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
//                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {


                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, deletephoto;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgview1);
            deletephoto = itemView.findViewById(R.id.deletphoto);

            linearLayout = itemView.findViewById(R.id.imagelayout);
        }

        public void image(PropertiesGallery imagesData) {


            switch (imagesData.getTtype()) {
                case 0:
                    Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + imagesData.getProperty_images()).into(image);
                    break;
                case 1:
                    //  imagesData.getProperty_images();
                    Uri tempUri = Uri.parse(imagesData.getProperty_images());
                    image.setImageURI(tempUri);
                    break;
            }
            //    lana f=lgie


        }
    }
}

