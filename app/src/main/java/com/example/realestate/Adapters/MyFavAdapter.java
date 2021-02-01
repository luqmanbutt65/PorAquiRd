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
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Sqldata.DataBaseHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFavAdapter extends RecyclerView.Adapter<MyFavAdapter.viewholder> {
    public ClickEventHandler clickHandler;
    Context context;
    private Activity activity;
    private List<Properties> properties;

    public MyFavAdapter(Activity activity, Context context,
                        List<Properties> properties, ClickEventHandler clickHandler) {
        this.context = context;
        this.activity = activity;
        this.properties = properties;
        this.clickHandler = clickHandler;
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
                    properties.remove(position);
                    notifyDataSetChanged();
                    clickHandler.handleClick(properties.size());
                    getpropertydata(user_Id, propertieId);
                }
            });
            holder.like_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    holder.likeimage_filled.setVisibility(View.VISIBLE);
                    holder.like_image.setVisibility(View.INVISIBLE);
                    // getpropertydata(user_Id, propertieId);

                }
            });

        } else {
            //when data is offline

            holder.likeimage_filled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.likeimage_filled.setVisibility(View.INVISIBLE);
                    holder.like_image.setVisibility(View.VISIBLE);

                    DataBaseHelper db = new DataBaseHelper(context);

                    String sid = String.valueOf(properties.get(position).getId());
                    int result = db.delete(sid);
                    if (result > 0) {
                        Toast.makeText(context, "Disliked", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, "SomeThingWrong", Toast.LENGTH_LONG).show();

                    }


                }
            });


            holder.like_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    holder.likeimage_filled.setVisibility(View.VISIBLE);
                    holder.like_image.setVisibility(View.INVISIBLE);

                    DataBaseHelper db = new DataBaseHelper(context);

                    //Here we need to add condition
                    long result = db.INSERT_Channels(properties.get(position));
                    if (result != -1) {
                        Toast.makeText(context, "INSERT", Toast.LENGTH_LONG).show();

                        return;
                    } else {
                        Toast.makeText(context, "NOT INSERT", Toast.LENGTH_LONG).show();
                    }

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

    public interface ClickEventHandler {
        public void handleClick(int count);
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView city, town, review, price, title, title1, bedroom, bath, area;
        ImageView mainimg, like_image, likeimage_filled;
        //        CircleImageView mainimg;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city_id);
            title1 = (TextView) itemView.findViewById(R.id.title_id);
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
            mainimg = itemView.findViewById(R.id.main_image11);
        }

        void setdata(Properties properties) {
            if (properties != null) {
                likeimage_filled.setVisibility(View.VISIBLE);
                like_image.setVisibility(View.INVISIBLE);

                String city_val = ((city_val = properties.getCity()) != null) ? city_val : "N/A";
                city.setText(city_val);
                String title_val = ((title_val = properties.getTitle()) != null) ? title_val : "N/A";
                title1.setText(title_val);
                String titlesale_val = ((titlesale_val = properties.getSale_type()) != null) ? titlesale_val : "N/A";
                title.setText(titlesale_val);

                String town_val = ((town_val = properties.getLocation()) != null) ? town_val : "N/A";
                town.setText(town_val);

                String review_val = ((review_val = properties.getRating()) != null) ? review_val : "N/A";
                review.setText(review_val);

                if (properties.getCurrency() != null) {

                    String type = properties.getCurrency();
                    String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
                    price.setText(type + " " + price_val);
                } else {

                    String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
                    price.setText(" " + price_val);

                }


                if (properties.getPropertiesExtraArrayList() != null) {

                    if (properties.getPropertiesExtraArrayList().size() > 0) {
                        for (int i = 0; i < properties.getPropertiesExtraArrayList().size(); i++) {
                            if (i == 1) {
                                if (properties.getPropertiesExtraArrayList().get(i).getType().equals("bedrooms")) {
                                    bedroom.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity()+ " " +properties.getPropertiesExtraArrayList().get(i).getType()  );
                                } else {
                                    bedroom.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity()+ " " +properties.getPropertiesExtraArrayList().get(i).getType()  );
                                }
                            } else if (i == 0) {
                                if (properties.getPropertiesExtraArrayList().get(i).getType().equals("bathrooms")) {
                                    bath.setText( properties.getPropertiesExtraArrayList().get(i).getQuantity()+ " " +properties.getPropertiesExtraArrayList().get(i).getType() );
                                } else {
                                    bath.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity()+ " " +properties.getPropertiesExtraArrayList().get(i).getType()  );

                                }
                            }
                        }
                    } else {
                        bedroom.setText("N/A");

                        bath.setText("N/A");
                    }


                } else {
                    bedroom.setText("N/A");

                    bath.setText("N/A");
                }

                String area_val = ((area_val = properties.getArea())) != null ? area_val : "N/A";
                area.setText(area_val + "M\u00B2");
//            mainimg.setImageResource(properties.getMain_image());
                if (properties.getMain_image() != null) {

                    Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + properties.getMain_image()).into(mainimg);
                    //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
                } else {

                    Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/").into(mainimg);

                }

            }


        }

    }

}


