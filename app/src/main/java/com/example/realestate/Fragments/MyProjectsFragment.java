package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.realestate.Adapters.MyprojectAdapter;
import com.example.realestate.Adddata;
import com.example.realestate.Model.MyprojectData;
import com.example.realestate.Activities.NewProjects;
import com.example.realestate.R;

import java.util.ArrayList;


public class MyProjectsFragment extends Fragment {

    Context context;
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
        View view=inflater.inflate(R.layout.fragment_my_projects, container, false);

        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.myproject_recycler);
        ImageView imageView=view.findViewById(R.id.addProject);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Adddata.class));

            }
        });

        String[] city = {"a", "s", "qqqqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        String[] location = {"aa", "ss", "qqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        double[] rating = {121, 121, 12121, 1212, 12121, 21212, 1212};
        double[] price = {2323, 32323, 32232, 23232, 2323223, 2323, 2332};
        String[] title = {"aaaaaa", "sssss", "qqqqqq", "kkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
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