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
import com.example.realestate.Activities.Description;
import com.example.realestate.AppConstant;
import com.example.realestate.CustomeClasses.CustomeImageview;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.Model.RejectedAppointmentMessages.RejectedMessages;
import com.example.realestate.R;

import java.util.ArrayList;


public class Mymessages_Adapter extends RecyclerView.Adapter<Mymessages_Adapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<RejectedMessages> rejectedMessagesArrayList;

    public Mymessages_Adapter(Activity activity, Context context,
                              ArrayList<RejectedMessages> rejectedMessagesArrayList) {
        this.context = context;
        this.activity = activity;
        this.rejectedMessagesArrayList = rejectedMessagesArrayList;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.mymessages_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return rejectedMessagesArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        int connectorId = Integer.parseInt(rejectedMessagesArrayList.get(position).getProperty_id());
        holder.setdata(rejectedMessagesArrayList.get(position));
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Description.class);
                intent.putExtra("propertieIDKey", connectorId);
                context.startActivity(intent);


            }
        });


    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, setappointment, time, message, propertyowner, suggestedDate, suggestesTime;
        CustomeImageview propertyimage;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.propertytitle);
            setappointment = (TextView) itemView.findViewById(R.id.setapointmenttime);
            propertyimage = itemView.findViewById(R.id.property_image_plan);
            time = (TextView) itemView.findViewById(R.id.setappointmentAgain);
            suggestedDate = (TextView) itemView.findViewById(R.id.suggestedddate);
            suggestesTime = (TextView) itemView.findViewById(R.id.suggesteddtime);
            message = (TextView) itemView.findViewById(R.id.message);
            propertyowner = (TextView) itemView.findViewById(R.id.propertyowner);
        }

        void setdata(RejectedMessages rejectedMessages) {


            if (rejectedMessages != null) {

                if (rejectedMessages.getPropertyDetail() != null) {

                    String title_val = ((title_val = rejectedMessages.getPropertyDetail().getTitle()) != null) ? title_val : "N/A";
                    title.setText(title_val);
                }


                String setappointment_val = ((setappointment_val = rejectedMessages.getCreated_at()) != null) ? setappointment_val : "N/A";
                setappointment.setText("Purchased at: " + setappointment_val);

//                String time_val = ((time_val = rejectedMessages.getCreated_at()) != null) ? time_val : "N/A";
//                time.setText(time_val);

                String message_val = ((message_val = String.valueOf(rejectedMessages.getMessage())) != null) ? message_val : "N/A";
                message.setText(message_val);

                String propertyowner_val = ((propertyowner_val = rejectedMessages.getProperty_owner()) != null) ? propertyowner_val : "N/A";
                propertyowner.setText(propertyowner_val);


                String date_val = ((date_val = rejectedMessages.getSuggested_date()) != null) ? date_val : "N/A";
                suggestedDate.setText("Suggested  Date: " + date_val);
                String time_val = ((time_val = rejectedMessages.getSuggested_time()) != null) ? time_val : "N/A";
                suggestesTime.setText("Suggested Time: " + time_val);

//
                Glide.with(context).load(AppConstant.IMAGE_PATH + rejectedMessages.getPropertyDetail().getMain_image()).into(propertyimage);

            }


        }

    }

}

 