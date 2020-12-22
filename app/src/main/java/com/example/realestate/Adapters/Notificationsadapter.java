package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Model.Features;
import com.example.realestate.Model.Notification;
import com.example.realestate.R;

import java.util.List;

public class Notificationsadapter extends RecyclerView.Adapter<Notificationsadapter.viewholder> {
    Context context;
    private Activity activity;
    private List<Notification> notification;

    public Notificationsadapter(Activity activity,
                                Context context,
                                List<Notification> notification) {
        this.context = context;
        this.activity = activity;
        this.notification = notification;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.notification_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return notification.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(notification.get(position));


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, description, notificationtime;
        LinearLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            description = itemView.findViewById(R.id.description_text);
            notificationtime = itemView.findViewById(R.id.notification_time);
            mainLayout = itemView.findViewById(R.id.notificationlayout);
        }

        void setdata(Notification notification) {
            title.setText(notification.getTitle());
            description.setText(notification.getDescription());
            notificationtime.setText(notification.getTime());


        }
    }
}

