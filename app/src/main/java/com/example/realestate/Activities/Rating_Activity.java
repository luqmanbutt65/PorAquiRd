package com.example.realestate.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Adapters.ReviewAdapter;
import com.example.realestate.ApiClass.ApiClient;
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
    ImageView back_btnReview;
    TextView numofcomments;
    String user_id = "";
    float userrating = 1;
    String comment_data = "";
    RecyclerView commentRecycler;
    ArrayList<User_Reviews> user_reviewsArrayList;
    ProgressDialog ratingProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_);
        if (isNetworkConnected()) {

        } else {
            networkalert();
        }
        if (new SharedPreferenceConfig().getBooleanLanguageFromSP("language", Rating_Activity.this)) {
            setLocale("");
        } else if (new SharedPreferenceConfig().getBooleanLanguagefrenchFromSP("frenchlanguage", Rating_Activity.this)) {
            setLocale("es");

        } else if (new SharedPreferenceConfig().getBooleanLanguagespanishFromSP("spanishlanguage", Rating_Activity.this)) {
            setLocale("sp");
        }

        user_reviewsArrayList = new ArrayList<>();


        ratingProgressDialog = new ProgressDialog(Rating_Activity.this);
        ratingProgressDialog.setMessage("Loading..."); // Setting Message
        ratingProgressDialog.setCancelable(false);
        comment = findViewById(R.id.et_comment);
        comment.setScroller(new Scroller(getApplicationContext()));
        comment.setMaxLines(7);
        comment.setVerticalScrollBarEnabled(true);
        comment.setMovementMethod(new ScrollingMovementMethod());
        ratingBar = findViewById(R.id.rating);
        submit = findViewById(R.id.submit_rating);
        numofcomments = findViewById(R.id.numofcomments);
        commentRecycler = findViewById(R.id.commentRecycler);
        back_btnReview = findViewById(R.id.back_btnReview);
        back_btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rating_Activity.this, Description.class);
                startActivity(intent);
                finish();
            }
        });


        commentRecycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        extras = getIntent().getExtras();
        if (extras != null) {
            // propertieID = extras.getInt("propertieIDKey");
            // and get whatever type user account id is
            user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", Rating_Activity.this);

        }
        propertieID = Integer.parseInt(GlobalState.getInstance().getCurrent_Property_id());
        getRateData(String.valueOf(propertieID));


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                userrating = ratingBar.getRating();


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", Rating_Activity.this)) {

                    comment_data = comment.getText().toString().trim();
                    if (comment_data.isEmpty() || userrating == 0) {
                        showToast("Rating or comment missing");
                    } else {

                        putRatingCommentData(user_id, propertieID, userrating, comment_data);
                        comment.setText("");
                        submit.setVisibility(View.INVISIBLE);
                    }
                } else {
                    showToast("You are not Login");
                }

            }
        });


    }

    public void putRatingCommentData(String user_id, int property_id, float rating, String comment) {
        ratingProgressDialog.show();

        Call<PostRatigResp> call = ApiClient.getRetrofit().create(ApiInterface.class).SEND_RATING_CALL(user_id, String.valueOf(property_id), rating, comment);
        call.enqueue(new Callback<PostRatigResp>() {
            @Override
            public void onResponse(Call<PostRatigResp> call, Response<PostRatigResp> response) {
                if (response.isSuccessful()) {
                    PostRatigResp rating_response = response.body();
                    if (rating_response.getMessage().equals("Rating and Comment Updated Successfully")) {
                        getRateData(String.valueOf(propertieID));
                        showToast("Review  Successfully Added");
                        Intent intent = new Intent(Rating_Activity.this, Description.class);
                        intent.putExtra("propertieIDKey", property_id);
                        startActivity(intent);
                        finish();

                    } else if (rating_response.getMessage().equals("Rating and Comment Added Successfully")) {

                        getRateData(String.valueOf(propertieID));
                        showToast("Review  Successfully Added");
                        Intent intent = new Intent(Rating_Activity.this, Description.class);
                        intent.putExtra("propertieIDKey", property_id);
                        startActivity(intent);
                        finish();

                    } else {

                        showToast("Error Please try again");

                    }


                }
                ratingProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PostRatigResp> call, Throwable t) {
                showToast(t.getMessage());
                ratingProgressDialog.dismiss();
            }
        });

    }


    public void getRateData(String id) {
        ratingProgressDialog.show();

        Call<Rating_Response> call = ApiClient.getRetrofit().create(ApiInterface.class).RATING_DATA_CALL(String.valueOf(id));
        call.enqueue(new Callback<Rating_Response>() {
            @Override
            public void onResponse(Call<Rating_Response> call, Response<Rating_Response> response) {
                if (response.isSuccessful()) {
                    Rating_Response rating_response = response.body();
                    if (rating_response.getMessage().equals("single property")) {
                        if (rating_response.getRatingData().getUser_reviewsArrayList() != null) {
                            if (rating_response.getRatingData().getUser_reviewsArrayList().size() > 0) {
                                numofcomments.setText(rating_response.getRatingData().getUser_reviewsArrayList().size() + " Comments");
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
                ratingProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Rating_Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                ratingProgressDialog.dismiss();
            }
        });

    }

}

