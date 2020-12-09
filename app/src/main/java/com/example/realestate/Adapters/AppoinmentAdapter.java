package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Model.Features;
import com.example.realestate.R;

import java.util.List;


public class AppoinmentAdapter extends RecyclerView.Adapter<AppoinmentAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<Features> featuresList;

    public AppoinmentAdapter(Activity activity,
                             Context context,
                             List<Features> featuresList) {
        this.context = context;
        this.activity = activity;
        this.featuresList = featuresList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.apointmentcontainer, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return featuresList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(featuresList.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, town_text, date_time, apointment_status;
        LinearLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.description_text);
            town_text = itemView.findViewById(R.id.town_text);
            date_time = itemView.findViewById(R.id.date_time);
            apointment_status = itemView.findViewById(R.id.apointment_approved);
            mainLayout = itemView.findViewById(R.id.featurelayout);
        }

        void setdata(Features features) {

//            imageView.setImageResource(features.getImg());


        }
    }
}

