package com.example.realestate.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.realestate.Adapters.AppointmentsAdapter;
import com.example.realestate.Model.AppointmentsData;
import com.example.realestate.R;

import java.util.ArrayList;


public class MyApointmentsFragment extends Fragment {


Context context;
    public MyApointmentsFragment() {
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
        View  view=inflater.inflate(R.layout.fragment_apointments, container, false);

        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.appointments_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        String[] city = {"Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data"};
        String[] location = {"Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data"};
        String[] rating = {"Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data"};
        String[] price = {"Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data", "Dummy data"};
        int[] image = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};
        ArrayList<AppointmentsData> apointmentsoins = new ArrayList<>();
        apointmentsoins.add(new AppointmentsData(city[0], location[0], rating[0], price[0],image[0]));
        apointmentsoins.add(new AppointmentsData(city[1], location[1], rating[1], price[1],image[1]));
        apointmentsoins.add(new AppointmentsData(city[2], location[2], rating[2], price[2],image[2]));

        apointmentsoins.add(new AppointmentsData(city[3], location[3], rating[3], price[3],image[3]));

        apointmentsoins.add(new AppointmentsData(city[4], location[4], rating[4], price[4],image[4]));

        apointmentsoins.add(new AppointmentsData(city[5], location[5], rating[5], price[5],image[5]));

        apointmentsoins.add(new AppointmentsData(city[6], location[6], rating[6], price[6],image[6]));


        recyclerView.setAdapter(new AppointmentsAdapter(this.getActivity(), context, apointmentsoins));

        return view;
    }
}