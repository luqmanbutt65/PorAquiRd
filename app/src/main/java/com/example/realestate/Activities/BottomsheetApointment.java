package com.example.realestate.Activities;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.realestate.Adddata;
import com.example.realestate.ApiClass.ApiInterface;
import com.example.realestate.Model.Apoinment.Apointment_Response;
import com.example.realestate.Model.Rating.PostRating.PostRatigResp;
import com.example.realestate.R;
import com.example.realestate.SharedPreference.SharedPreferenceConfig;
import com.example.realestate.Utills.GlobalState;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BottomsheetApointment extends BaseActivity {
    EditText et_apointment, et_time;
    RadioGroup statusbutton;
    RadioButton formeeting, forvideomeeting;
    DatePickerDialog apointmentdatepicker;
    Button apointmentSubmit;
    int propertieID = 1;
    String user_id = "";
    String datetime = "";
    ProgressDialog apointmentProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_bottom_sheet);


        apointmentSubmit = findViewById(R.id.apointmentSubmit);
        statusbutton = findViewById(R.id.togglegroupp);
        et_apointment = findViewById(R.id.et_apointment);
        et_time = findViewById(R.id.et_time);
        formeeting = (RadioButton) findViewById(R.id.tour_meeting);
        forvideomeeting = (RadioButton) findViewById(R.id.tour_video);


        apointmentProgressDialog = new ProgressDialog(BottomsheetApointment.this);
        apointmentProgressDialog.setMessage("Logining..."); // Setting Message
        apointmentProgressDialog.setCancelable(false);

        et_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BottomsheetApointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        apointmentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = new SharedPreferenceConfig().getidOfUSerFromSP("id", BottomsheetApointment.this);
                propertieID = Integer.parseInt(GlobalState.getInstance().getCurrent_Property_id());

                String apointment_val = et_apointment.getText().toString();
                String time = et_time.getText().toString();
                if (!apointment_val.equals(null) || !et_time.equals(null)) {
                    datetime = apointment_val + " " + time + ":00";

                }
                if (!datetime.equals(null)) {
                    putApointmentData(user_id, propertieID, datetime);

                } else {

                    showToast("Please select data and Time first");
                }

            }
        });

        et_apointment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

// date picker dialog
                apointmentdatepicker = new DatePickerDialog(BottomsheetApointment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = getDateInCorrectFormat(year, monthOfYear, dayOfMonth);

                                et_apointment.setText(date);

                            }
                        }, year, month, day);
                apointmentdatepicker.getDatePicker().setMinDate(System.currentTimeMillis());// TODO: used to hide future date,month and year

                apointmentdatepicker.show();


            }
        });
        statusbutton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (formeeting.isChecked()) {
                    formeeting.setTextColor(Color.WHITE);
                    forvideomeeting.setTextColor(Color.BLACK);
                }
                if (forvideomeeting.isChecked()) {
                    forvideomeeting.setTextColor(Color.WHITE);
                    formeeting.setTextColor(Color.BLACK);
                }
            }
        });

    }


    private String getDateInCorrectFormat(int year, int monthOfYear, int dayOfMonth) {

        String date = "";
        String formatedMonth = "";
        String formatedDay = "";
        if (monthOfYear < 9) {
            formatedMonth = "0" + (monthOfYear + 1);
        } else {
            formatedMonth = String.valueOf(monthOfYear + 1);
        }

        if (dayOfMonth < 10) {
            formatedDay = "0" + dayOfMonth;
        } else {
            formatedDay = String.valueOf(dayOfMonth);
        }
        date = year + "-" + formatedMonth + "-" + formatedDay;
        return date;
    }


    public void putApointmentData(String user_id, int property_id, String dateTime) {
        apointmentProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://poraquird.stepinnsolution.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Call<Apointment_Response> call = retrofit.create(ApiInterface.class).SET_APOINTMENT_CALL(user_id, String.valueOf(property_id), dateTime);
        call.enqueue(new Callback<Apointment_Response>() {
            @Override
            public void onResponse(Call<Apointment_Response> call, Response<Apointment_Response> response) {
                if (response.isSuccessful()) {
                    Apointment_Response apointment_response = response.body();
                    if (apointment_response.getMessage().equals("Appointment Successfully Set.")) {

                        showToast("Appointment Set Successfully");


                    } else if (apointment_response.getMessage().equals("Appointment Successfully Updated.")) {

                        showToast("Appointment Update Successfully");

                    } else {
                        showToast("Error Please try again");
                    }

                }
                apointmentProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Apointment_Response> call, Throwable t) {
                showToast(t.getMessage());
                apointmentProgressDialog.dismiss();
            }
        });

    }
}