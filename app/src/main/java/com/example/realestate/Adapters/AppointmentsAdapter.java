package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
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

import com.example.realestate.Model.AppointmentsData;
import com.example.realestate.Model.DashboardData;
import com.example.realestate.R;

import java.util.List;


public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<AppointmentsData> appointmentsData;

    public AppointmentsAdapter(Activity activity, Context context,
                               List<AppointmentsData> appointmentsData) {
        this.context = context;
        this.activity = activity;
        this.appointmentsData = appointmentsData;

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
        return appointmentsData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(appointmentsData.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView description, town, datetime, status;
        ImageView mainimg;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description_text);
            town = (TextView) itemView.findViewById(R.id.town_text);
            datetime = (TextView) itemView.findViewById(R.id.date_time);
            status = (TextView) itemView.findViewById(R.id.apointment_approved);

            mainLayout = itemView.findViewById(R.id.appointmentlayout);
            mainimg = itemView.findViewById(R.id.iv_property);
        }

        void setdata(AppointmentsData appointmentsData) {
            description.setText(appointmentsData.getDescription());
            town.setText(appointmentsData.getTown());
            datetime.setText(appointmentsData.getDateTime());
            status.setText(appointmentsData.getStatus());
            mainimg.setImageResource(appointmentsData.getImg());


        }

    }
}

