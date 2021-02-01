package com.example.realestate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.AppConstant;
import com.example.realestate.Model.Plans.Plan;
import com.example.realestate.Model.REST.Properties.PropertiesExtra;
import com.example.realestate.R;
import com.example.realestate.SetMapdataInterface;
import com.example.realestate.Utills.Buttonchecked;
import com.example.realestate.Utills.Planclick;

import java.util.ArrayList;
import java.util.List;


public class PlandatacontainerAdapter extends RecyclerView.Adapter<PlandatacontainerAdapter.viewholder> {
    Context context;
    private Activity activity;
    private ArrayList<Plan> planList;
    Planclick planclick;
    Buttonchecked buttonchecked;

    public PlandatacontainerAdapter(Activity activity,
                                    Context context,
                                    ArrayList<Plan> planList, Planclick planclick, Buttonchecked buttonchecked) {
        this.context = context;
        this.activity = activity;
        this.planList = planList;
        this.planclick = planclick;
        this.buttonchecked = buttonchecked;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.plans_container_lay, parent, false);

        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return planList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setdata(planList.get(position));


        if (planList.get(position).isIschecked()) {
            holder.checkBox.setImageResource(R.drawable.clicktick);
            buttonchecked.isPlanSelected(position);
            buttonchecked.onPlanSelect(position);
            //  onPlanCheck(position,true)
        } else {
            holder.checkBox.setImageResource(R.drawable.bgcheckbox);
            //  onPlanCheck(position,false)

        }


        holder.planclicklay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        holder.checkBox.setImageResource(R.drawable.clicktick);
                        buttonchecked.onPlanCheck(position);
                        buttonchecked.onPlanSelect(position);
                buttonchecked.isPlanSelected(position);

                }


//                for (int i = 0; i < planList.size(); i++) {
//
//
//                        holder.checkBox.setImageResource(R.drawable.clicktick);
//                        Log.d("tagd", "if" + i);
//
//
//                        holder.checkBox.setImageResource(R.drawable.bgcheckbox);
//                        Log.d("tagd", "els" + i);
//
//
//                }
//                planclick.onclick();



        });

    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView type;
        ImageView imageView, checkBox;
        RelativeLayout planclicklay;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.plantext);
            checkBox = itemView.findViewById(R.id.check);
            planclicklay = itemView.findViewById(R.id.planclicklay);
        }

        void setdata(Plan plan) {

            if (planList != null) {

                String type_val = ((type_val = plan.getTitle()) != null) ? type_val : "N/A";
                type.setText(type_val);

            }


        }
    }


}

