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

import com.bumptech.glide.Glide;
import com.example.realestate.AppConstant;
import com.example.realestate.Model.Plans.PlanFeatures;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;


public class PlanFeatureAdapter extends RecyclerView.Adapter<PlanFeatureAdapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<PlanFeatures> planFeaturesArrayList;

    public PlanFeatureAdapter(Activity activity,
                              Context context,
                              ArrayList<PlanFeatures> planFeaturesArrayList) {
        this.context = context;
        this.activity = activity;
        this.planFeaturesArrayList = planFeaturesArrayList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.planfeatures_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return planFeaturesArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(planFeaturesArrayList.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.plantext);

        }

        void setdata(PlanFeatures planFeatures) {

            if (planFeatures != null) {


                String type_val = ((type_val = planFeatures.getTitle()) != null) ? type_val : "N/A";
                title.setText(type_val);


            }


        }
    }
}

