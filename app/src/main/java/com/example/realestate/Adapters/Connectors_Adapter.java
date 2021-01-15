package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Connector_Description;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Connectors_Adapter extends RecyclerView.Adapter<Connectors_Adapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<Connectors> connectorsArrayList;

    public Connectors_Adapter(Activity activity, Context context,
                              ArrayList<Connectors> connectorsArrayList) {
        this.context = context;
        this.activity = activity;
        this.connectorsArrayList = connectorsArrayList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.connectors_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return connectorsArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        int connectorId = Integer.parseInt(connectorsArrayList.get(position).getConnectorId());
        holder.setdata(connectorsArrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Connector_Description.class);
                intent.putExtra("connectorid", connectorId);
                context.startActivity(intent);


            }
        });


    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView name, email;
        CircleImageView mainimg;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.connector_name);
            email = (TextView) itemView.findViewById(R.id.connector_email);
            mainLayout = itemView.findViewById(R.id.dashboardlayout);
            mainimg = itemView.findViewById(R.id.connector_image);
        }

        void setdata(Connectors connectors) {

            //  String status= ((status = invoice.getStatus()) != null) ? status : "paid";
            if (connectors != null) {

                String name_val = ((name_val = connectors.getName()) != null) ? name_val : "N/A";
                name.setText(name_val);

                String email_val = ((email_val = connectors.getEmail()) != null) ? email_val : "N/A";
                email.setText(email_val);

//                String review_val = ((review_val = properties.getRating()) != null) ? review_val : "N/A";
//                review.setText(review_val);
//
//                String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
//                price.setText("$ " + price_val);
//
//                String title_val = ((title_val = properties.getSale_type()) != null) ? title_val : "N/A";
//                title.setText(title_val);

//
                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/user_images/" + connectors.getUser_image()).into(mainimg);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }


    }
}

 