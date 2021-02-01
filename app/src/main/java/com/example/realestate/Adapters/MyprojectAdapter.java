package com.example.realestate.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Description;
import com.example.realestate.Activities.UpdateData;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.CustomeClasses.CustomeImageview;
import com.example.realestate.Model.Delete_Property.DeletePropertyResponse;
import com.example.realestate.Model.Like.LikeResponse;
import com.example.realestate.Model.MyProject.MyProperties_Response;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyprojectAdapter extends RecyclerView.Adapter<MyprojectAdapter.viewholder> {
    public ClickEventHandler clickHandler;
    Context context;
    ProgressDialog deletprogress;
    private Activity activity;
    private List<Properties> properties;

    public MyprojectAdapter(Activity activity, Context context,
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
        View inflate = inflater.inflate(R.layout.myproject_container, parent, false);

        deletprogress = new ProgressDialog(context);
        deletprogress.setMessage("Loading..."); // Setting Message
        deletprogress.setCancelable(false);
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

//            int propertieId = properties.get(position).getId();
//            String user_Id = new SharedPreferenceConfig().getidOfUSerFromSP("id", context);


        }

        int propertieId = properties.get(position).getId();
        holder.setdata(properties.get(position));

        holder.itemView.setLongClickable(true);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                properties.remove(position);
                notifyDataSetChanged();
                DeleteProperty(propertieId);
                clickHandler.recount(properties.size());
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateData.class);
                i.putExtra("propertieIDKey", propertieId);
                context.startActivity(i);
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Description.class);
                intent.putExtra("propertieIDKey", propertieId);
                context.startActivity(intent);

            }
        });

    }

    public void DeleteProperty(int property_id) {
        deletprogress.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<DeletePropertyResponse> call = retrofit.create(ApiInterface.class).DELET_PROPERTY_CALL(property_id);
        call.enqueue(new Callback<DeletePropertyResponse>() {
            @Override
            public void onResponse(Call<DeletePropertyResponse> call, Response<DeletePropertyResponse> response) {
                if (response.isSuccessful()) {
                    DeletePropertyResponse deletePropertyResponse = response.body();
                    if (deletePropertyResponse.getMessage().equals("Porperty Deleted Successfully")) {

                        Toast.makeText(context, "Property Deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error Please try again", Toast.LENGTH_SHORT).show();
                    }

                }
                deletprogress.dismiss();
            }

            @Override
            public void onFailure(Call<DeletePropertyResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                deletprogress.dismiss();
            }
        });

    }


    public interface ClickEventHandler {
        public void recount(int count);
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView city, town, review, price, title, title1, bedroom, bath, area;
        CustomeImageview mainimg;
        ImageView delete, update;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city_id);
            town = (TextView) itemView.findViewById(R.id.town_id);
            review = (TextView) itemView.findViewById(R.id.reviews);
            price = (TextView) itemView.findViewById(R.id.price);
            title = (TextView) itemView.findViewById(R.id.title);
            title1 = (TextView) itemView.findViewById(R.id.title_id);
            delete = itemView.findViewById(R.id.deletebtn);
            update = itemView.findViewById(R.id.editbtn);
            bedroom = (TextView) itemView.findViewById(R.id.bedroomdasboard);
            bath = (TextView) itemView.findViewById(R.id.bathsdashboard);
            area = (TextView) itemView.findViewById(R.id.areadashboard);
            mainLayout = itemView.findViewById(R.id.dashboardlayout);
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

                if (properties.getCurrency() != null) {

                    String type = properties.getCurrency();
                    String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
                    price.setText(type + " " + price_val);
                } else {

                    String price_val = ((price_val = String.valueOf(properties.getPrice())) != null) ? price_val : "N/A";
                    price.setText(" " + price_val);

                }

                String title_val = ((title_val = properties.getSale_type()) != null) ? title_val : "N/A";
                title.setText(title_val);
                String title1_val = ((title1_val = properties.getTitle()) != null) ? title1_val : "N/A";
                title1.setText(title1_val);


                if (properties.getPropertiesExtraArrayList() != null) {

                    if (properties.getPropertiesExtraArrayList().size() > 0) {
                        for (int i = 0; i < properties.getPropertiesExtraArrayList().size(); i++) {
                            if (i == 1) {
                                if (properties.getPropertiesExtraArrayList().get(i).getType().equals("bedrooms")) {
                                    bedroom.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity() + " " + properties.getPropertiesExtraArrayList().get(i).getType());
                                } else {
                                    bedroom.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity() + " " + properties.getPropertiesExtraArrayList().get(i).getType());
                                }
                            } else if (i == 0) {
                                if (properties.getPropertiesExtraArrayList().get(i).getType().equals("bathrooms")) {
                                    bath.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity() + " " + properties.getPropertiesExtraArrayList().get(i).getType());
                                } else {
                                    bath.setText(properties.getPropertiesExtraArrayList().get(i).getQuantity() + " " + properties.getPropertiesExtraArrayList().get(i).getType());

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
                Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" + properties.getMain_image()).into(mainimg);
                //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg
            }


        }


    }
}


