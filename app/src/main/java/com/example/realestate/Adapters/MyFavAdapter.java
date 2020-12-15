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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Description;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Like.LikeResponse;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFavAdapter extends RecyclerView.Adapter<MyFavAdapter.viewholder> {
    Context context;
    private Activity activity;
    private List<Properties> properties;

    public MyFavAdapter(Activity activity, Context context,
                        List<Properties> properties) {
        this.context = context;
        this.activity = activity;
        this.properties = properties;
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

        if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", context)) {

            int propertieId = properties.get(position).getId();
            String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", context);

            holder.likeimage_filled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.likeimage_filled.setVisibility(View.INVISIBLE);
                    holder.like_image.setVisibility(View.VISIBLE);
                    getpropertydata(user_Id, propertieId);

                }
            });
            holder.like_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    holder.likeimage_filled.setVisibility(View.VISIBLE);
                    holder.like_image.setVisibility(View.INVISIBLE);
                    getpropertydata(user_Id, propertieId);
                }
            });

        }

        int propertieId = properties.get(position).getId();
        holder.setdata(properties.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Description.class);
                intent.putExtra("propertieIDKey", propertieId);
                context.startActivity(intent);
            }
        });


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView city, town, review, price, title, bedroom, bath, area;
        ImageView mainimg, like_image, likeimage_filled;
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

        void setdata(Properties properties) {
            if (properties != null) {
                String city_val = ((city_val = properties.getCity()) != null) ? city_val : "N/A";
                city.setText(city_val);

                String town_val = ((town_val = properties.getLocation()) != null) ? town_val : "N/A";
                town.setText(town_val);

                String review_val = ((review_val = properties.getRating()) != null) ? review_val : "N/A";
                review.setText(review_val);

                String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
                price.setText("$ " + price_val);

                String title_val = ((title_val = properties.getSale_type()) != null) ? title_val : "N/A";
                title.setText(title_val);

                if (properties.getPropertiesExtra() != null) {
                    String bedroom_val = ((bedroom_val = properties.getPropertiesExtra().getBedrooms()) != null) ? bedroom_val : "N/A";
                    bedroom.setText(bedroom_val + " Bedroom");

                    String bath_val = ((bath_val = properties.getPropertiesExtra().getBathrooms()) != null) ? bath_val : "N/A";
                    bath.setText(bath_val + " Bath");
                } else {
                    bedroom.setText("N/A");

                    bath.setText("N/A");
                }


                String area_val = ((area_val = properties.getArea())) != null ? area_val : "N/A";
                area.setText(area_val + "M\u00B2");
//            mainimg.setImageResource(properties.getMain_image());
                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + properties.getMain_image()).into(mainimg);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }

    }
    public void getpropertydata(String user_id, int property_id) {
//        descriptionProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<LikeResponse> call = retrofit.create(ApiInterface.class).LIKEPROPERTY_CALL(user_id, String.valueOf(property_id));
        call.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                if (response.isSuccessful()) {
                    LikeResponse likeResponse = response.body();
                    if (likeResponse.getMessage().equals("Liked")) {


                    } else if (likeResponse.getMessage().equals("Unliked")) {


                    } else {
                        Toast.makeText(context, "Error Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
//                descriptionProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
//                descriptionProgressDialog.dismiss();
            }
        });

    }
}

