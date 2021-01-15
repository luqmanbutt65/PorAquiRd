package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Connector_Description;
import com.example.realestate.Fragments.PurchasedPlans;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.Model.Plans.Purchasedplan.PurchasedPlan;
import com.example.realestate.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Purchasedplan_Adapter extends RecyclerView.Adapter<Purchasedplan_Adapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<PurchasedPlan> purchasedPlanArrayList;

    public Purchasedplan_Adapter(Activity activity, Context context,
                                 ArrayList<PurchasedPlan> purchasedPlanArrayList) {
        this.context = context;
        this.activity = activity;
        this.purchasedPlanArrayList = purchasedPlanArrayList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.purchasedplan_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return purchasedPlanArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setdata(purchasedPlanArrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, Connector_Description.class);
//                intent.putExtra("connectorid", connectorId);
//                context.startActivity(intent);


            }
        });


    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, planprice, purchasedate, validTill;
//        CircleImageView userImage;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.plantitle);
            planprice = (TextView) itemView.findViewById(R.id.planprice);
//            userImage = itemView.findViewById(R.id.user_image_plan);
            purchasedate = (TextView) itemView.findViewById(R.id.purchasedate);
            validTill = (TextView) itemView.findViewById(R.id.tilldate);
        }

        void setdata(PurchasedPlan purchasedPlan) {


            if (purchasedPlan != null) {

                String title_val = ((title_val = purchasedPlan.getStatus()) != null) ? title_val : "N/A";
                title.setText(title_val);

                String planprice_val = ((planprice_val = purchasedPlan.getPrice()) != null) ? planprice_val : "N/A";
                planprice.setText("$ " + planprice_val);

                String purchasedate_val = ((purchasedate_val = purchasedPlan.getCreated_at()) != null) ? purchasedate_val : "N/A";
                purchasedate.setText("Purchase Date " + purchasedate_val);

                String validTill_val = ((validTill_val = String.valueOf(purchasedPlan.getUpdated_at())) != null) ? validTill_val : "N/A";
                validTill.setText("Valid till " + validTill_val);


//                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/user_images/" + connectors.getUser_image()).into(userImage);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }


    }
}

 