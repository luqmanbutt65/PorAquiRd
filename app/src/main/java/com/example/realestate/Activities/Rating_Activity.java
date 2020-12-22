package com.example.realestate.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Adapters.ReviewAdapter;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Rating.GetRating.RatingData;
import com.example.realestate.Model.Rating.GetRating.Rating_Response;
import com.example.realestate.Model.Rating.GetRating.User_Reviews;
import com.example.realestate.Model.Rating.PostRating.PostRatigResp;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rating_Activity extends BaseActivity {
    int propertieID = 1;
    Bundle extras;
    EditText comment;
    RatingBar ratingBar;
    Button submit;
    TextView numofcomments;
    String user_id = "";
    float userrating = 1;
    String comment_data = "";
    RecyclerView commentRecycler;
    ArrayList<User_Reviews> user_reviewsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_);

        user_reviewsArrayList = new ArrayList<>();
        comment = findViewById(R.id.et_comment);
        ratingBar = findViewById(R.id.rating);
        submit = findViewById(R.id.submit_rating);
        numofcomments = findViewById(R.id.numofcomments);
        commentRecycler = findViewById(R.id.commentRecycler);
        commentRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        extras = getIntent().getExtras();
        if (extras != null) {
            // propertieID = extras.getInt("propertieIDKey");
            // and get whatever type user account id is
            user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", Rating_Activity.this);

        }
        propertieID = Integer.parseInt(GlobalState.getInstance().getCurrent_Property_id());
        getRateData(String.valueOf(propertieID));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                userrating = ratingBar.getRating();


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comment_data = comment.getText().toString();
                if (!comment_data.isEmpty()) {
                    putRatingCommentData(user_id, propertieID, userrating, comment_data);
                    comment.setText("");
                    submit.setVisibility(View.INVISIBLE);
                } else {
                    showToast("Enter the Review....");
                }

            }
        });


    }

    public void putRatingCommentData(String user_id, int property_id, float rating, String comment) {
//        descriptionProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<PostRatigResp> call = retrofit.create(ApiInterface.class).SEND_RATING_CALL(user_id, String.valueOf(property_id), rating, comment);
        call.enqueue(new Callback<PostRatigResp>() {
            @Override
            public void onResponse(Call<PostRatigResp> call, Response<PostRatigResp> response) {
                if (response.isSuccessful()) {
                    PostRatigResp rating_response = response.body();
                    if (rating_response.getMessage().equals("Rating and Comment Updated Successfully")) {
                        getRateData(String.valueOf(propertieID));
                        showToast("Review  Successfully Added");

                    } else {
                        showToast("Error Please try again");
                    }


                }
//                descriptionProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PostRatigResp> call, Throwable t) {
                showToast(t.getMessage());
//                descriptionProgressDialog.dismiss();
            }
        });

    }


    public void getRateData(String id) {
//        homeProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Rating_Response> call = retrofit.create(ApiInterface.class).RATING_DATA_CALL(String.valueOf(id));
        call.enqueue(new Callback<Rating_Response>() {
            @Override
            public void onResponse(Call<Rating_Response> call, Response<Rating_Response> response) {
                if (response.isSuccessful()) {
                    Rating_Response rating_response = response.body();
                    if (rating_response.getMessage().equals("single property")) {
                        if (rating_response.getRatingData().getUser_reviewsArrayList() != null) {
                            if (rating_response.getRatingData().getUser_reviewsArrayList().size() > 0) {
                                numofcomments.setText(rating_response.getRatingData().getUser_reviewsArrayList().size()+" Comments");
                                commentRecycler.setAdapter(new ReviewAdapter(Rating_Activity.this, getApplicationContext(), rating_response.getRatingData().getUser_reviewsArrayList()));


                            }
                        }
                    } else {
                        numofcomments.setText("0 Comments");

                        Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    }


                } else {


                    Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
//                homeProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Rating_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                homeProgressDialog.dismiss();
            }
        });

    }

}

