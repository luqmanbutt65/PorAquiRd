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
import com.example.realestate.Model.Apoinment.Apointments;
import com.example.realestate.Model.Features;
import com.example.realestate.R;

import java.util.List;


public class AppoinmentAdapter extends RecyclerView.Adapter<AppoinmentAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<Apointments> apointments;

    public AppoinmentAdapter(Activity activity,
                             Context context,
                             List<Apointments> apointments) {
        this.context = context;
        this.activity = activity;
        this.apointments = apointments;

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
        return apointments.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(apointments.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, town_text, date_time, apointment_status;
        LinearLayout mainLayout;
        ImageView iv_property;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.description_text);
            town_text = itemView.findViewById(R.id.town_text);
            date_time = itemView.findViewById(R.id.date_time);
            apointment_status = itemView.findViewById(R.id.apointment_approved);
            mainLayout = itemView.findViewById(R.id.featurelayout);
            iv_property= itemView.findViewById(R.id.iv_property);
        }

        void setdata(Apointments apointments) {

            if (apointments != null) {



                String date_val = ((date_val = apointments.getTime()) != null) ? date_val : "N/A";
                date_time.setText(date_val);

                String apointment_val = ((apointment_val = String.valueOf(apointments.getStatus())) != null) ? apointment_val : "N/A";
                apointment_status.setText(apointment_val);

                if (apointment_val!=null) {
                    if (apointment_val.equals("pending")) {

                        apointment_status.setBackgroundResource(R.drawable.pending);
                    }
                    if (apointment_val.equals("approve")) {

                        apointment_status.setBackgroundResource(R.drawable.approved);
                    }
                    if (apointment_val.equals("reject")) {

                        apointment_status.setBackgroundResource(R.drawable.cancel);
                    }

                    if (apointments.getProperties()!=null){

                        String title_val = ((title_val = apointments.getProperties().getTitle())!= null) ? title_val : "N/A";
                        title.setText(title_val);

                        String town_val = ((town_val = apointments.getProperties().getCity()) != null) ? town_val : "N/A";
                        town_text.setText(town_val);
                    }

                }
                else {

                    apointment_status.setText("N/A");
                }
                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + apointments.getProperties().getMain_image()).into(iv_property);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }
    }
}

