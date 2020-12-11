package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.realestate.Adapters.MyprojectAdapter;
import com.example.realestate.Adddata;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.R;

import java.util.ArrayList;


public class MyProjectsFragment extends Fragment {

    Context context;
    ImageView imageView,backbtn;
    public MyProjectsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_my_projects, container, false);

        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.myproject_recycler);
//       imageView = view.findViewById(R.id.addProject);

        backbtn= view.findViewById(R.id.back_btn_myproject);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameprofile, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), Adddata.class));
//
//            }
//        });

        String[] city = {"this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data"};
        String[] location = {"this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data"};
        double[] rating = {121.00, 121.00, 12121.00, 1,212.00, 1,121.00, 2,1212.00, 1212.00};
        double[] price = {2,323.00, 3,2323.00, 3,2232.00, 2,3232.00, 23,23.00,223.00, 2,323.00, 2,332.00};
        String[] title = {"this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data", "this is dummy data"};
        int[] image = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};

        ArrayList<MyprojectData> myprojectData = new ArrayList<>();
        myprojectData.add(new MyprojectData(city[0], location[0], rating[0], price[0], title[0], image[0]));
        myprojectData.add(new MyprojectData(city[1], location[1], rating[1], price[1], title[1], image[1]));
        myprojectData.add(new MyprojectData(city[2], location[2], rating[2], price[2], title[2], image[2]));

        myprojectData.add(new MyprojectData(city[3], location[3], rating[3], price[3], title[3], image[3]));

        myprojectData.add(new MyprojectData(city[4], location[4], rating[4], price[4], title[4], image[4]));

        myprojectData.add(new MyprojectData(city[5], location[5], rating[5], price[5], title[5], image[5]));

        myprojectData.add(new MyprojectData(city[6], location[6], rating[6], price[6], title[6], image[6]));


        recyclerView.setAdapter(new MyprojectAdapter(getActivity(), context, myprojectData));

        return view;
    }
}