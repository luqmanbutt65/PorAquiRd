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
        import com.example.realestate.Activities.Description;
        import com.example.realestate.Model.DashboardData;
        import com.example.realestate.Model.REST.Properties.Properties;
        import com.example.realestate.R;

        import java.util.ArrayList;



public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<Properties> properties;

    public DashBoardAdapter(Activity activity, Context context,
                           ArrayList<Properties> properties) {
        this.context = context;
        this.activity = activity;
        this.properties=properties;



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
        return properties.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
          holder.setdata(properties.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Description.class);
                context.startActivity(intent);
            }
        });


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView city, town,review ,price,title,bedroom,bath,area;
        ImageView mainimg;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city_id);
            town = (TextView) itemView.findViewById(R.id.town_id);
            review = (TextView) itemView.findViewById(R.id.reviews);
            price = (TextView) itemView.findViewById(R.id.price);
            title = (TextView) itemView.findViewById(R.id.title);
            bedroom = (TextView) itemView.findViewById(R.id.bedroomdasboard);
            bath = (TextView) itemView.findViewById(R.id.bathsdashboard);
            area = (TextView) itemView.findViewById(R.id.areadashboard);
            mainLayout = itemView.findViewById(R.id.dashboardlayout);
            mainimg = itemView.findViewById(R.id.main_image);
        }

        void setdata(com.example.realestate.Model.REST.Properties.Properties properties) {

            city.setText(properties.getCity());
            town.setText(properties.getLocation());
            review.setText(String.valueOf(properties.getRating()));
            price.setText((String.valueOf(properties.getPrice())));
            title.setText(properties.getTitle());
            bedroom.setText(String.valueOf(properties.getBedroom()));
            bath.setText((String.valueOf(properties.getBath())));
            area.setText(String.valueOf(properties.getArea()));
//            mainimg.setImageResource(properties.getMain_image());
            Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/"+properties.getMain_image()).into(mainimg);
            //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg

        }


    }
}

 