package com.example.realestate.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.realestate.Adapters.Notificationsadapter;
import com.example.realestate.Model.Notification;
import com.example.realestate.R;

import java.util.ArrayList;


public class Notifications extends Fragment {

ImageView backbtn;
Context context;
    public Notifications(View.OnClickListener mainActivity) {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notifications, container, false);


        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.notification_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        String[] city = {"a", "s", "qqqqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        String[] location = {"aa", "ss", "qqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        String[] rating = {"1m", "2m", "2h", "3m", "5h", "15m", "33m"};

        ArrayList<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification(city[0], location[0], rating[0]));
        notifications.add(new Notification(city[1], location[1], rating[1]));
        notifications.add(new Notification(city[2], location[2], rating[2]));

        notifications.add(new Notification(city[3], location[3], rating[3]));

        notifications.add(new Notification(city[4], location[4], rating[4]));

        notifications.add(new Notification(city[5], location[5], rating[5]));

        notifications.add(new Notification(city[6], location[6], rating[6]));

        recyclerView.setAdapter(new Notificationsadapter(this.getActivity(), context, notifications));


        backbtn=view.findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return view;
    }
}