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
import com.example.realestate.Model.Rating.GetRating.User_Reviews;
import com.example.realestate.R;
import com.example.realestate.Utills.GlobalState;

import java.util.ArrayList;

import static com.example.realestate.AppConstant.IMAGE_PATH;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<User_Reviews> user_reviews = new ArrayList<>();
    private String current_Pro_id;

    public ReviewAdapter(Activity activity, Context context,
                         ArrayList<User_Reviews> user_reviews) {
        this.context = context;
        this.activity = activity;
        this.user_reviews = user_reviews;
        current_Pro_id = GlobalState.getInstance().getCurrent_Property_id();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.comment_container, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return user_reviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setdata(user_reviews.get(position));


    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView name, date, comment;
        RatingBar ratingBar;
        ImageView user_Image;
        RelativeLayout mainLayout;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.userName);
            date = (TextView) itemView.findViewById(R.id.datecomment);
            comment = (TextView) itemView.findViewById(R.id.commenttext);
            mainLayout = itemView.findViewById(R.id.mainlayoutcomment);
            user_Image = itemView.findViewById(R.id.user_image);
            ratingBar = itemView.findViewById(R.id.ratingbar);
        }

        void setdata(User_Reviews user_reviews) {


            if (user_reviews != null) {
                if (user_reviews.getUser_detail() != null) {
                    String naameVal = ((naameVal = user_reviews.getUser_detail().getName()) != null) ? naameVal : "N/A";
                    name.setText(naameVal);

                    ratingBar.setRating(user_reviews.getRating());

                    String comentVal = ((comentVal = user_reviews.getComments()) != null) ? comentVal : "N/A";
                    comment.setText(comentVal);
                    Glide.with(context).load(IMAGE_PATH + user_reviews.getUser_detail().getUser_image()).into(user_Image);
                }
            }
        }
    }


}


