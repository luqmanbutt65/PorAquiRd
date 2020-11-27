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

import java.util.ArrayList;
import java.util.List;


public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<Features> featuresList;
    public FeatureAdapter(Activity activity,
                           Context context,
                           List<Features> featuresList) {
        this.context = context;
        this.activity = activity;
        this.featuresList=featuresList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.features_container, parent, false);

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
        TextView description;
        ImageView imageView;
        LinearLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
                description = itemView.findViewById(R.id.feature_description);
                imageView = itemView.findViewById(R.id.feature_image);
                mainLayout = itemView.findViewById(R.id.featurelayout);
        }

        void setdata(Features features) {
            description.setText(features.getDescription());
            imageView.setImageResource(features.getImg());


        }
    }
}

