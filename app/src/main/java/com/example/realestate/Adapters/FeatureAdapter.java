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
import com.example.realestate.Model.Connectors.connectorData.ConnectorData_response;
import com.example.realestate.Model.Features;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.R;

import java.util.ArrayList;
import java.util.List;


public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<PropertiesExtra> propertiesExtraList;

    public FeatureAdapter(Activity activity,
                          Context context,
                          List<PropertiesExtra> propertiesExtraList) {
        this.context = context;
        this.activity = activity;
        this.propertiesExtraList = propertiesExtraList;

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
        return propertiesExtraList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(propertiesExtraList.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView type, quantity;
        ImageView imageView;
        LinearLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.feature_type);
            quantity = itemView.findViewById(R.id.feature_quantity);
            imageView = itemView.findViewById(R.id.feature_image);
            mainLayout = itemView.findViewById(R.id.featurelayout);
        }

        void setdata(PropertiesExtra propertiesExtraList) {

            if (propertiesExtraList != null) {

                String type_val = ((type_val = propertiesExtraList.getType()) != null) ? type_val : "N/A";
                type.setText(type_val);

                String quantity_val = ((quantity_val = propertiesExtraList.getQuantity()) != null) ? quantity_val : "N/A";
                quantity.setText(quantity_val);

                Glide.with(context).load(AppConstant.IMAGE_PATH_extra + propertiesExtraList.getImage()).into(imageView);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg

            }


        }
    }
}

