package com.example.realestate.Adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Fragments.OthersApointmentsFragment;
import com.example.realestate.Model.Apoinment.Apointments;
import com.example.realestate.Model.Features;
import com.example.realestate.Model.Notification;
import com.example.realestate.R;

import java.util.List;

public class Notificationsadapter extends RecyclerView.Adapter<Notificationsadapter.viewholder> {
    Context context;
    ProgressDialog myapointmentProgressDialog;

    private Activity activity;
    private List<Apointments> apointments;


    public Notificationsadapter(Activity activity,
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
        View inflate = inflater.inflate(R.layout.notification_container, parent, false);
        myapointmentProgressDialog = new ProgressDialog(context);
        myapointmentProgressDialog.setMessage("Loading..."); // Setting Message
        myapointmentProgressDialog.setCancelable(false);
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
        RelativeLayout mainLayout;
        ImageView iv_property1;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.description_text1);
            town_text = itemView.findViewById(R.id.town_text1);
            date_time = itemView.findViewById(R.id.date_time1);
            mainLayout = itemView.findViewById(R.id.other_appointmentlayout);
            iv_property1 = itemView.findViewById(R.id.iv_property1);
        }

        void setdata(Apointments apointments) {


            if (apointments != null) {


                String date_val = ((date_val = apointments.getTime()) != null) ? date_val : "N/A";
                date_time.setText(date_val);


                if (apointments.getProperties() != null) {

                    String title_val = ((title_val = apointments.getProperties().getTitle()) != null) ? title_val : "N/A";
                    title.setText(title_val);

                    String town_val = ((town_val = apointments.getProperties().getCity()) != null) ? town_val : "N/A";
                    town_text.setText(town_val);
                } else {
                    title.setText("N/A");
                    town_text.setText("N/A");

                }
                if (apointments.getProperties().getMain_image() != null) {
                    Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" +
                            apointments.getProperties().getMain_image()).into(iv_property1);
                    //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg

                } else {

                    Glide.with(context).load("https://i0.wp.com/www.complexsql.com/wp-content/uploads/2018/11/null.png?resize=300%2C300").into(iv_property1);
                }

            }


        }
    }
}

