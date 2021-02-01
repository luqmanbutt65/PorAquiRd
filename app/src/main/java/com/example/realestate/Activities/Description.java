package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.realestate.Adapters.DashBoardAdapter;
import com.example.realestate.Adapters.FeatureAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.AppConstant;
import com.example.realestate.BuildConfig;
import com.example.realestate.Model.Features;
import com.example.realestate.Model.REST.Properties.Properties;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.Model.REST.Properties.PropertiesGallery;
import com.example.realestate.Model.REST.Properties.Properties_Data;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.PropertiesSingle.Ownerdetail;
import com.example.realestate.Model.REST.PropertiesSingle.PropertiesSingleResp;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;
import com.example.realestate.customViewPager.SliderAdapterExample;
import com.example.realestate.customViewPager.customViewPager;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Description extends BaseActivity implements customViewPager.Clickslider {
    Context context;
    ImageView cancelslider, share;
    Features features;
    ViewPager viewPager;
    TextView tv_city, tv_location, tv_price, tv_reviews, description, timezone, owner_name, owner_number, reviews1, email;
    DotsIndicator dotsIndicator;
    ProgressDialog homeProgressDialog;
    RecyclerView featurerecycler;
    Button getApointment, getInformation;
    int propertieID = 1;
    RelativeLayout slider, mainpage;
    Bundle extras;
    CircleImageView ownerimage;
    ProgressDialog descriptionProgressDialog;
    ArrayList<PropertiesGallery> propertiesGalleryArrayList;
    SliderView sliderView;
    String ownernumber;
    String propertlink;
    customViewPager.Clickslider clickslider;
    private ArrayList<Properties> propertiesArrayList;
    private ArrayList<PropertiesExtra> propertiesExtraArrayList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        if (isNetworkConnected()) {

        } else {
            networkalert();
        }
        if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", Description.this)) {
            setLocale("");
        } else if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", Description.this)) {
            setLocale("es");

        } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", Description.this)) {
            setLocale("sp");
        }

        cancelslider = findViewById(R.id.cancelslider);
        slider = findViewById(R.id.slider);
        mainpage = findViewById(R.id.mainpage);
        share = findViewById(R.id.share);
        reviews1 = findViewById(R.id.reviews1);
        email = findViewById(R.id.owner_email);

        propertiesExtraArrayList = new ArrayList<>();
        context = Description.this;
        // propertieID=getxtr
        extras = getIntent().getExtras();
        if (extras != null) {
            propertieID = extras.getInt("propertieIDKey");
            GlobalState.getInstance().setCurrent_Property_id(String.valueOf(propertieID));
            String userId = new SharedPreferenceConfig().getidOfUSerFromSP("id", Description.this);
            // and get whatever type user account id is
        }


        descriptionProgressDialog = new ProgressDialog(Description.this);
        descriptionProgressDialog.setMessage("Loading..."); // Setting Message
        descriptionProgressDialog.setCancelable(false);

        getApointment = findViewById(R.id.getapointmentbtn);
        getInformation = findViewById(R.id.info);
        tv_city = findViewById(R.id.city_id);
        tv_location = findViewById(R.id.location_id);
        tv_price = findViewById(R.id.prices);
        tv_reviews = findViewById(R.id.reviews);
        description = findViewById(R.id.tv_description_text);


        owner_name = findViewById(R.id.owner_name);
        owner_number = findViewById(R.id.owner_number);
        ownerimage = findViewById(R.id.owner_image);

        featurerecycler = findViewById(R.id.featuresrecyclerview);

        tv_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, Rating_Activity.class);
                intent.putExtra("propertieIDKey", propertieID);
                context.startActivity(intent);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                propertlink = "https://poraquird.stepinnsolution.com/detail_property" + propertieID;

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this Property\n\n";
                    shareMessage = shareMessage + propertlink;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
        description.setMovementMethod(new ScrollingMovementMethod());

        getpropertydata(propertieID);

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        sliderView = findViewById(R.id.imageSlider);
        timezone = findViewById(R.id.timezone);


        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        context = this;

        getApointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Description.this, BottomsheetApointment.class);
                intent.putExtra("ownerNumber", ownernumber);
                startActivity(intent);

            }
        });

        getInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancelslider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slider.setVisibility(View.INVISIBLE);
                mainpage.setVisibility(View.VISIBLE);

            }
        });

    }


    public void getpropertydata(int id) {
        descriptionProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<PropertiesSingleResp> call = retrofit.create(ApiInterface.class).PROPERTY_CALL(String.valueOf(id));
        call.enqueue(new Callback<PropertiesSingleResp>() {
            @Override
            public void onResponse(Call<PropertiesSingleResp> call, Response<PropertiesSingleResp> response) {
                if (response.isSuccessful()) {
                    PropertiesSingleResp propertiesSingleResp = response.body();
                    if (propertiesSingleResp.getMessage().equals("single property")) {
                        if (propertiesSingleResp != null) {
                            if (propertiesSingleResp.getPropertiesData() != null) {
                                if (propertiesSingleResp.getPropertiesData().getSingleProperty() != null) {
                                    if (propertiesSingleResp.getPropertiesData().getSingleProperty().getPropertiesGalleryArrayList() != null) {
                                        propertiesGalleryArrayList = propertiesSingleResp.getPropertiesData().getSingleProperty().getPropertiesGalleryArrayList();
                                    } else {
                                        propertiesGalleryArrayList = new ArrayList<>();
                                    }


                                    ownernumber = propertiesSingleResp.getPropertiesData().getSingleProperty().getOwner_number();
                                    String city_val = ((city_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getCity()) != null) ? city_val : "N/A";
                                    tv_city.setText(city_val);

                                    String town_val = ((town_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getLocation()) != null) ? town_val : "N/A";
                                    tv_location.setText(town_val);

                                    String review_val = ((review_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getRating()) != null) ? review_val : "N/A";
                                    tv_reviews.setText(review_val);

                                    String pricetype_val = ((pricetype_val = String.valueOf(propertiesSingleResp.getPropertiesData().getSingleProperty().getPrice())) != null) ? pricetype_val : "N/A";

                                    String price_val = ((price_val = String.valueOf(propertiesSingleResp.getPropertiesData().getSingleProperty().getCurrency())) != null) ? price_val : "N/A";
                                    tv_price.setText(pricetype_val + " " + price_val);

                                    String description_val = ((description_val = propertiesSingleResp.getPropertiesData().getSingleProperty().getDescription()) != null) ? description_val : "N/A";
                                    description.setText(description_val);

                                    String vistorcont = ((vistorcont = String.valueOf(propertiesSingleResp.getPropertiesData().getSingleProperty().getVisitors_count())) != null) ? vistorcont : "N/A";
                                    reviews1.setText(vistorcont);

                                    if (propertiesSingleResp.getPropertiesData().getSingleProperty().getOwner_details() != null) {
                                        Ownerdetail ownerdetail = propertiesSingleResp.getPropertiesData().getSingleProperty().getOwner_details();
                                        String ownername_val = ((ownername_val = ownerdetail.getName()) != null) ? ownername_val : "N/A";
                                        owner_name.setText(ownername_val);
                                        String number_val = ((number_val = ownerdetail.getNumber()) != null) ? number_val : "N/A";
                                        owner_number.setText("+1" + number_val);
                                        ownernumber = "+1" + number_val;

                                        String email_val = ((email_val = ownerdetail.getEmail()) != null) ? email_val : "N/A";
                                        email.setText(email_val);
                                        Glide.with(context).load(AppConstant.IMAGE_PATH_USER + ownerdetail.getUser_image()).into(ownerimage);
                                    }

                                    if (propertiesSingleResp.getPropertiesData().getSingleProperty().getCreated_at() != null) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                        Date strDate = null;
                                        String newdate = null;
                                        try {
                                            String temExpirayDate = propertiesSingleResp.getPropertiesData().getSingleProperty().getCreated_at();
                                            strDate = sdf.parse(temExpirayDate);
                                            newdate = DateFormat.getDateTimeInstance().format(strDate);

                                            Log.e("tt", strDate.toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        if (newdate.equals(null)) {
                                            timezone.setText("");
                                        } else {
                                            timezone.setText(newdate);
                                        }

                                    } else {
                                        timezone.setText("");
                                    }


                                    setpagerview(propertiesGalleryArrayList);
                                    sliderView.setSliderAdapter(new SliderAdapterExample(Description.this, propertiesGalleryArrayList));


                                    dotsIndicator.setViewPager(viewPager);

                                    propertiesExtraArrayList = propertiesSingleResp.getPropertiesData().getSingleProperty().getPropertiesExtraArrayList();
                                    if (propertiesExtraArrayList != null) {

                                        featurerecycler.setAdapter(new FeatureAdapter(Description.this, context, propertiesExtraArrayList));

                                    }

                                } else {
                                }
                            } else {
                                tv_city.setText("N/A");
                                tv_location.setText("N/A");
                                tv_price.setText("N/A");
                                tv_reviews.setText("N/A");
                                description.setText("N/A");
                            }
                        } else {
                            tv_city.setText("N/A");
                            tv_location.setText("N/A");
                            tv_price.setText("N/A");
                            tv_reviews.setText("N/A");
                            description.setText("N/A");
                        }


                    } else {

                        tv_city.setText("N/A");
                        tv_location.setText("N/A");
                        tv_price.setText("N/A");
                        tv_reviews.setText("N/A");
                        description.setText("N/A");
                    }


                } else {
                    showToast("Error! Please try again!");
                }
                descriptionProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PropertiesSingleResp> call, Throwable t) {
                showToast(t.getMessage());
                descriptionProgressDialog.dismiss();
            }
        });

    }

    @Override
    public void change(View view) {

        slider.setVisibility(View.VISIBLE);
        mainpage.setVisibility(View.INVISIBLE);

    }

    public void setpagerview(ArrayList<PropertiesGallery> propertiesGalleryArrayList) {
        customViewPager customViewPager = new customViewPager(Description.this, propertiesGalleryArrayList, this);
        viewPager.setAdapter(customViewPager);
    }
}