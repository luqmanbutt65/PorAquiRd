package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Model.Connectors.connectorData.Connector_reviews;
import com.example.realestate.Model.Rating.GetRating.User_Reviews;
import com.example.realestate.R;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import static com.example.realestate.AppConstant.IMAGE_PATH;
import static com.example.realestate.AppConstant.IMAGE_PATH_USER;

public class ConnectorReviewAdapter extends RecyclerView.Adapter<ConnectorReviewAdapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<Connector_reviews> connector_reviews = new ArrayList<>();
    private String current_Pro_id;

    public ConnectorReviewAdapter(Activity activity, Context context,
                                  ArrayList<Connector_reviews> connector_reviews) {
        this.context = context;
        this.activity = activity;
        this.connector_reviews = connector_reviews;
        current_Pro_id = GlobalState.getInstance().getCurrent_Property_id();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.connector_comment_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return connector_reviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setdata(connector_reviews.get(position));


    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView name, date, comment;
        RatingBar ratingBar;
        ImageView user_Image;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.connectorName);
            date = (TextView) itemView.findViewById(R.id.connector_datecomment);
            comment = (TextView) itemView.findViewById(R.id.commenttextconnector);
            mainLayout = itemView.findViewById(R.id.mainlayoutcommentconnector);
            user_Image = itemView.findViewById(R.id.conector_image);
            ratingBar = itemView.findViewById(R.id.ratingbarconnector);
        }

        void setdata(Connector_reviews connector_reviews) {


            if (connector_reviews != null) {
                String naameVal = ((naameVal = connector_reviews.getName()) != null) ? naameVal : "N/A";
                name.setText(naameVal);

                String dateVal = ((dateVal = connector_reviews.getTime()) != null) ? dateVal : "N/A";
                date.setText(dateVal);

                Float ratingVal = ((ratingVal = Float.parseFloat(connector_reviews.getRating().trim())) != null) ? ratingVal : 0;
                ratingBar.setRating(ratingVal);


                String comentVal = ((comentVal = connector_reviews.getComments()) != null) ? comentVal : "N/A";
                comment.setText(comentVal);
                Glide.with(context).load(IMAGE_PATH_USER + connector_reviews.getUser_image()).into(user_Image);
            }

        }
    }


}


