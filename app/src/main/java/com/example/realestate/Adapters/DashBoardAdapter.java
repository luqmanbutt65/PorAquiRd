package com.example.realestate.Adapters;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Build;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.RequiresApi;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.example.realestate.Activities.Description;
        import com.example.realestate.CustomeClasses.NumberTextWatcher;
        import com.example.realestate.Model.DashboardData;
        import com.example.realestate.Model.REST.Properties.Properties;
        import com.example.realestate.R;

        import java.text.DecimalFormat;
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

          holder.likeimage_filled.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  holder.likeimage_filled.setVisibility(View.INVISIBLE);
                  holder.like_image.setVisibility(View.VISIBLE);

              }
          });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int propertieId=properties.get(position).getId();

                Intent intent = new Intent(context, Description.class);
                intent.putExtra("propertieIDKey",propertieId);
                context.startActivity(intent);
            }
        });


        holder.like_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.likeimage_filled.setVisibility(View.VISIBLE);
                holder.like_image.setVisibility(View.INVISIBLE);
            }
        });

    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView city, town,review ,price,title,bedroom,bath,area;
        ImageView mainimg,like_image,likeimage_filled;
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
            like_image = itemView.findViewById(R.id.like_image);
            likeimage_filled = itemView.findViewById(R.id.like_image_filled);
            mainimg = itemView.findViewById(R.id.main_image);

        }

        void setdata(com.example.realestate.Model.REST.Properties.Properties properties) {

          //  String status= ((status = invoice.getStatus()) != null) ? status : "paid";
            if(properties!=null){
               String city_val= ((city_val=properties.getCity())!=null) ? city_val :"N/A";
               city.setText(city_val);

               String town_val=((town_val=properties.getLocation())!=null) ? town_val :"N/A";
                town.setText(town_val);

                String review_val=((review_val=properties.getRating())!=null) ? review_val :"N/A";
                review.setText(review_val);

                String price_val=((price_val=String.valueOf(properties.getPrice())) !=null) ? price_val :"N/A";
                price.setText("$ "+price_val);

                String title_val=((title_val=properties.getSale_type())!=null) ? title_val :"N/A";
                title.setText(title_val);

                if(properties.getPropertiesExtra()!=null){
                    String bedroom_val=((bedroom_val=properties.getPropertiesExtra().getBedrooms())!=null) ? bedroom_val :"N/A";
                    bedroom.setText(bedroom_val);

                    String bath_val=((bath_val=properties.getPropertiesExtra().getBathrooms())!=null) ? bath_val :"N/A";
                    bath.setText(bath_val);
                }else {
                     bedroom.setText("N/A");

                     bath.setText("N/A");
                }


                String area_val=((area_val=properties.getArea())) !=null ? area_val :"N/A";
                area.setText(area_val+"M\u00B2");
//            mainimg.setImageResource(properties.getMain_image());
                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/"+properties.getMain_image()).into(mainimg);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }


    }
}

 