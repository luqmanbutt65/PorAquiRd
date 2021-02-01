package com.example.realestate.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.realestate.Activities.Description;
import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Activities.Rating_Activity;
import com.example.realestate.Adapters.ConnectorReviewAdapter;
import com.example.realestate.Adapters.Connectors_Adapter;
import com.example.realestate.Adapters.ReviewAdapter;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Connectors.Connector_response;
import com.example.realestate.Model.Connectors.Connectors;
import com.example.realestate.Model.Connectors.connectorData.ConnectorData_Data;
import com.example.realestate.Model.Connectors.connectorData.Connector_reviews;
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


public class ConnectorReviews extends Fragment {
    RecyclerView connectorRecyclerview;
    ArrayList<Connector_reviews> connectorReviewsArrayList;
    RatingBar ratingBar;
    float connectorrating;
    EditText commentdata;
    Button submit;
    ProgressDialog connectorcommentdiolog;

    public ConnectorReviews() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connector_reviews, container, false);
        connectorcommentdiolog = new ProgressDialog(getContext());
        connectorcommentdiolog.setMessage("Loading...");
        connectorcommentdiolog.setCancelable(false);

        submit = view.findViewById(R.id.submit_connector_rating);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPreferenceConfig().getBooleanFromSP("isLogin", getContext())) {

                    String connectorsid = new SharedPreferenceConfig().geteconnectoridOfUSerFromSP("connector_id", getContext());
                    String userId = new SharedPreferenceConfig().getidOfUSerFromSP("id", getContext());
                    String commentValu = commentdata.getText().toString().trim();

                    if (commentValu.isEmpty() || connectorrating == 0) {
                        Toast.makeText(getContext(), "Rating or comment missing", Toast.LENGTH_SHORT).show();
                    } else {
                        putRatingCommentData(userId, connectorsid, connectorrating, commentValu);


                    }

                } else {
                    Toast.makeText(getContext(), "You are not Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        commentdata = view.findViewById(R.id.et_comment1);
        String commentValu = commentdata.getText().toString().trim();
        ratingBar = view.findViewById(R.id.connectorrating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                connectorrating = ratingBar.getRating();


            }
        });
//

        connectorReviewsArrayList = new ArrayList<>();
        connectorRecyclerview = view.findViewById(R.id.connector_commentRecycler);
        connectorRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ConnectorData_Data connectorData_data = GlobalState.getInstance().getConnectorData_data();

        if (connectorData_data != null) {
            connectorReviewsArrayList = connectorData_data.getConnector_reviewsArrayList();

            if (connectorReviewsArrayList != null) {
                if (connectorReviewsArrayList.size() > 0) {
//                    numofcomments.setText(connectorReviewsArrayList.size() + " Reviews");
                    connectorRecyclerview.setAdapter(new ConnectorReviewAdapter(getActivity(), getContext(), connectorReviewsArrayList));
                }
            } else {
                Toast.makeText(getContext(), "array null", Toast.LENGTH_SHORT).show();
            }
        }

        return view;
    }


    public void putRatingCommentData(String user_id, String connector_id, float rating, String comment) {
        connectorcommentdiolog.show();

        Call<PostRatigResp> call = ApiClient.getRetrofit().create(ApiInterface.class).PUT_COMMENT_CALL(user_id, connector_id, rating, comment);
        call.enqueue(new Callback<PostRatigResp>() {
            @Override
            public void onResponse(Call<PostRatigResp> call, Response<PostRatigResp> response) {
                if (response.isSuccessful()) {
                    PostRatigResp rating_response = response.body();
                    if (rating_response.getMessage().equals("Rating Added Successfully")) {
//                        getRateData(String.valueOf(propertieID));
//                        showToast("Review  Successfully Added");
                        Toast.makeText(getContext(), "Review  Successfully Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    } else if (rating_response.getMessage().equals("Rating Updated Successfully")) {


                        Toast.makeText(getContext(), "Review Updated ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (rating_response.getMessage().equals("You cant give ratings to the agent because you have no connection")) {

                        Toast.makeText(getContext(), "You cant give ratings to the agent because you have no connection", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Error Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
                connectorcommentdiolog.dismiss();

            }

            @Override
            public void onFailure(Call<PostRatigResp> call, Throwable t) {
//                showToast();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                connectorcommentdiolog.dismiss();
            }
        });

    }


}