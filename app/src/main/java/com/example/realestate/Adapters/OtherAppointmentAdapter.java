package com.example.realestate.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.realestate.Activities.Description;
import com.example.realestate.ApiClass.ApiClient;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Fragments.OthersApointmentsFragment;
import com.example.realestate.Model.Apoinment.Apointment_Rply;
import com.example.realestate.Model.Apoinment.Apointments;
import com.example.realestate.Model.AppointmentsData;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OtherAppointmentAdapter extends RecyclerView.Adapter<OtherAppointmentAdapter.viewholder> {
    Context context;
    ProgressDialog myapointmentProgressDialog;
    int appointment_Id;
    Button accept, reject, changedate, submitchangedate;
    EditText changedDatetime;
    String current_appointment_id = "";
    int propertieId;
    OthersApointmentsFragment othersApointmentsFragment;
    private Activity activity;
    private List<Apointments> apointments;
    EditText Message;
    public MyFavAdapter.ClickEventHandler clickHandler;
    boolean isclicked = false;

    public OtherAppointmentAdapter(Activity activity,
                                   Context context,
                                   List<Apointments> apointments, OthersApointmentsFragment othersApointmentsFragment) {
        this.context = context;
        this.activity = activity;
        this.apointments = apointments;
        this.othersApointmentsFragment = othersApointmentsFragment;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.other_apointmentcontainer, parent, false);

        myapointmentProgressDialog = new ProgressDialog(context);
        myapointmentProgressDialog.setMessage("Logining..."); // Setting Message
        myapointmentProgressDialog.setCancelable(false);
        return new viewholder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getItemCount() {
        return apointments.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        appointment_Id = apointments.get(position).getId();
        if (apointments.get(position).getProperties() != null) {
            propertieId = apointments.get(position).getProperties().getId();
            holder.setdata(apointments.get(position));
        } else {
            holder.mainLayout.setVisibility(View.GONE);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppointmentReply();
                if (isclicked) {
                    apointments.remove(apointments.get(position));
                    notifyDataSetChanged();
                }


//
            }
        });

        GlobalState.getInstance().setCurrent_Property_id(String.valueOf(current_appointment_id));
    }

    public void AcceptAppointment(int appointment_id) {
        myapointmentProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Apointment_Rply> call = retrofit.create(ApiInterface.class).APOINTMENT_APPROVE_CALL(appointment_id);
        call.enqueue(new Callback<Apointment_Rply>() {
            @Override
            public void onResponse(Call<Apointment_Rply> call, Response<Apointment_Rply> response) {
                if (response.isSuccessful()) {
                    Apointment_Rply get_apointment_response = response.body();
                    if (get_apointment_response.getMessage().equals("Appointment Status Approved")) {
                        Toast.makeText(context, "Appointment Accepted", Toast.LENGTH_SHORT).show();
                        updateOtherApointmentsData();
                    } else {

                        Toast.makeText(context, "Error Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
                myapointmentProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Apointment_Rply> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                myapointmentProgressDialog.dismiss();
            }
        });

    }

    private void updateOtherApointmentsData() {
        String user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", context);

        othersApointmentsFragment.putApointmentData(user_id);
    }

    public void RejectAppointment(int appointment_id, String message) {
        myapointmentProgressDialog.show();

        isclicked = false;
        Call<Apointment_Rply> call = ApiClient.getRetrofit().create(ApiInterface.class).APOINTMENT_REJECT_CALL(appointment_id, message);
        call.enqueue(new Callback<Apointment_Rply>() {
            @Override
            public void onResponse(Call<Apointment_Rply> call, Response<Apointment_Rply> response) {
                if (response.isSuccessful()) {
                    Apointment_Rply get_apointment_response = response.body();
                    if (get_apointment_response.getMessage().equals("Appointment Status Rejected")) {
                        Toast.makeText(context, "Appointment Rejected", Toast.LENGTH_SHORT).show();
                        isclicked = true;
                        updateOtherApointmentsData();
                    } else {

                        Toast.makeText(context, "Error Please try again", Toast.LENGTH_SHORT).show();
                    }


                }
                myapointmentProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Apointment_Rply> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                myapointmentProgressDialog.dismiss();
            }
        });

    }

    public void AppointmentReply() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.customedilouge_appointment_reply, null);


        accept = dialogView.findViewById(R.id.accept);
        reject = dialogView.findViewById(R.id.reject);
        changedate = dialogView.findViewById(R.id.changeappointmentdate);
        submitchangedate = dialogView.findViewById(R.id.submitChangeDate);
        changedDatetime = dialogView.findViewById(R.id.et_change_time);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptAppointment(appointment_Id);
                dialogBuilder.dismiss();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rejectionmessage();
                dialogBuilder.dismiss();


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void rejectionmessage() {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.reject_appointment_reply, null);


        Button reject1 = dialogView.findViewById(R.id.reject);
        Message = dialogView.findViewById(R.id.rejectionmessage);


        reject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = Message.getText().toString();
                if (message.equals("")) {
                    Toast.makeText(context, "Please enter rejection Message First !", Toast.LENGTH_SHORT).show();
                } else {
                    RejectAppointment(appointment_Id, message);


                    dialogBuilder.dismiss();
                }


            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, town_text, date_time, apointment_status;
        RelativeLayout mainLayout;
        ImageView iv_property1;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.description_text1);
            town_text = itemView.findViewById(R.id.town_text1);
            date_time = itemView.findViewById(R.id.date_time1);
            apointment_status = itemView.findViewById(R.id.apointment_approved1);
            mainLayout = itemView.findViewById(R.id.other_appointmentlayout);
            iv_property1 = itemView.findViewById(R.id.iv_property1);
        }

        void setdata(Apointments apointments) {


            if (apointments != null) {


                String date_val = ((date_val = apointments.getTime()) != null) ? date_val : "N/A";
                date_time.setText(date_val);

                String apointment_val = ((apointment_val = String.valueOf(apointments.getStatus())) != null) ? apointment_val : "N/A";
                apointment_status.setText(apointment_val);

                if (apointment_val != null) {
                    if (apointment_val.equals("pending")) {

                        apointment_status.setBackgroundResource(R.drawable.pending);
                    }
                    if (apointment_val.equals("approve")) {

                        apointment_status.setBackgroundResource(R.drawable.approved);
                    }
                    if (apointment_val.equals("reject")) {

                        apointment_status.setBackgroundResource(R.drawable.cancel);
                    }
                }

                if (apointments.getProperties() != null) {

                    String title_val = ((title_val = apointments.getProperties().getTitle()) != null) ? title_val : "N/A";
                    title.setText(title_val);

                    String town_val = ((town_val = apointments.getProperties().getCity()) != null) ? town_val : "N/A";
                    town_text.setText(town_val);
                } else {
                    title.setText("N/A");
                    town_text.setText("N/A");

                }
                if (apointments.getProperties().getMain_image() != null) {
                    Glide.with(context).load("http://poraquird.stepinnsolution.com/public/property_main_images/" +
                            apointments.getProperties().getMain_image()).into(iv_property1);
                    //http://poraquird.stepinnsolution.com/public/property_main_images/Property-Rental.jpg.1606997175jpeg

                } else {

                    Glide.with(context).load("https://i0.wp.com/www.complexsql.com/wp-content/uploads/2018/11/null.png?resize=300%2C300").into(iv_property1);
                }

            }


        }

    }


}

