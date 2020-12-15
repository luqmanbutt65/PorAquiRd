package com.example.realestate.Activities;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.example.realestate.R;

import java.util.Calendar;

public class BottomsheetApointment extends BaseActivity {
    EditText et_apointment,et_time;
    RadioGroup statusbutton;
    RadioButton formeeting, forvideomeeting;
    DatePickerDialog apointmentdatepicker;
    Button apointmentSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_bottom_sheet);


        apointmentSubmit = findViewById(R.id.apointmentSubmit);
        statusbutton = findViewById(R.id.togglegroupp);
        et_apointment = findViewById(R.id.et_apointment);
        et_time= findViewById(R.id.et_time);
        formeeting = (RadioButton) findViewById(R.id.tour_meeting);
        forvideomeeting = (RadioButton) findViewById(R.id.tour_video);

        String apointment_val=et_apointment.getText().toString();


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
                        et_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        apointmentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        date = formatedMonth + "/" + formatedDay + "/" + year;
        return date;
    }
}