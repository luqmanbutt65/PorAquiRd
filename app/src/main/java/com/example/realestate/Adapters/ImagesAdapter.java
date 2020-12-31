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

import com.example.realestate.Adddata;
import com.example.realestate.Model.ImagesData;
import com.example.realestate.R;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    Context context;
    ArrayList<Uri> ImagesArray;
    Adddata adddata;

    public ImagesAdapter(Context context, ArrayList<Uri> ImagesArray, Adddata adddata) {
        this.context = context;
        this.ImagesArray = ImagesArray;
        this.adddata=adddata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_container, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.image(ImagesArray.get(position));
        holder.image.setLongClickable(true);
        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
//                .setIcon(R.drawable.delete)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                            //remove from array list and notifydata set change
                                ImagesArray.remove(position);
                                adddata.removeFromImagearray(position);
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

                return false;
            }
        });

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

        public void image(Uri imagesData) {
            image.setImageURI(imagesData);
        }
    }
    private AlertDialog AskOption()
    {
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
}

