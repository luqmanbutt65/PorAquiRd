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

import com.example.realestate.Activities.Description;
import com.example.realestate.Model.DashboardData;
import com.example.realestate.R;

import java.util.List;

public class DasboardAdapter extends RecyclerView.Adapter<DasboardAdapter.viewholder> {
        Context context;
        private Activity activity;
    private List<DashboardData> dashboardData;

        public DasboardAdapter(Activity activity, Context context,
                               List<DashboardData> dashboardData) {
            this.context = context;
            this.activity = activity;
            this.dashboardData=dashboardData;


        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View inflate = inflater.inflate(R.layout.dashboard_container, parent, false);

            return new viewholder(inflate);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public int getItemCount() {
            return dashboardData.size();
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {
            holder.setdata(dashboardData.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Description.class);
                    context.startActivity(intent);
                }
            });


        }

        public class viewholder extends RecyclerView.ViewHolder {
            TextView city, town,review ,price,title;
            ImageView mainimg;
            RelativeLayout mainLayout;


            public viewholder(@NonNull View itemView) {
                super(itemView);
                city = (TextView) itemView.findViewById(R.id.city_id);
                town = (TextView) itemView.findViewById(R.id.town_id);
                review = (TextView) itemView.findViewById(R.id.reviews);
                price = (TextView) itemView.findViewById(R.id.price);
                title = (TextView) itemView.findViewById(R.id.title);
                mainLayout = itemView.findViewById(R.id.dashboardlayout);
                mainimg = itemView.findViewById(R.id.main_image);
            }

            void setdata(DashboardData dashboardData) {
                city.setText(dashboardData.getCity());
                town.setText(dashboardData.getLocation());
                review.setText(String.valueOf(dashboardData.getRating()));
                price.setText((String.valueOf(dashboardData.getPrice())));
                title.setText(dashboardData.getTitle());
                mainimg.setImageResource(dashboardData.getImg());


            }

        }
    }

