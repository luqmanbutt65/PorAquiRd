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
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.R;

import java.util.List;

public class MyprojectAdapter extends RecyclerView.Adapter<MyprojectAdapter.viewholder> {
        Context context;
        private Activity activity;
    private List<MyprojectData> myprojectData;

        public MyprojectAdapter(Activity activity, Context context,
                                List<MyprojectData> myprojectData) {
            this.context = context;
            this.activity = activity;
            this.myprojectData=myprojectData;


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
            return myprojectData.size();
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {
            holder.setdata(myprojectData.get(position));

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

            void setdata(MyprojectData myprojectData) {
                city.setText(myprojectData.getCity());
                town.setText(myprojectData.getLocation());
                review.setText(String.valueOf(myprojectData.getRating()));
                price.setText((String.valueOf(myprojectData.getPrice())));
                title.setText(myprojectData.getTitle());
                mainimg.setImageResource(myprojectData.getImg());


            }

        }
    }

