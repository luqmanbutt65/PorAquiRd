package com.example.realestate.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.realestate.Activities.MainActivity;
import com.example.realestate.Adapters.DasboardAdapter;
import com.example.realestate.BottomSheets.BottomSheet;
import com.example.realestate.Model.DashboardData;
import com.example.realestate.R;

import java.util.ArrayList;


public class Homefragment extends Fragment {
    ImageView drawerbtn, filter, notification;
    Context context;
    EditText search;
    DrawerLayout drawerLayout;
    private FrameLayout frameLayout;

    public Homefragment() {
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
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);

        context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        notification = view.findViewById(R.id.notification);
        filter = view.findViewById(R.id.filter);
        search = view.findViewById(R.id.search);
        drawerbtn = view.findViewById(R.id.drawer);

        drawerLayout = view.findViewById(R.id.drawerlayout);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BottomSheet();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

//        drawerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout = view.findViewById(R.id.drawerlayout);
//                drawerLayout.openDrawer(Gravity.LEFT, true);
//            }
//        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new Notifications(this);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        String[] city = {"a", "s", "qqqqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        String[] location = {"aa", "ss", "qqqq", "kkkkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        double[] rating = {121, 121, 12121, 1212, 12121, 21212, 1212};
        double[] price = {2323, 32323, 32232, 23232, 2323223, 2323, 2332};
        String[] title = {"aaaaaa", "sssss", "qqqqqq", "kkk", "kkkkkllll", "ppppppp", "uuuuuuu"};
        int[] image = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house};
        ArrayList<DashboardData> dashboardData = new ArrayList<>();
        dashboardData.add(new DashboardData(city[0], location[0], rating[0], price[0], title[0], image[0]));
        dashboardData.add(new DashboardData(city[1], location[1], rating[1], price[1], title[1], image[1]));
        dashboardData.add(new DashboardData(city[2], location[2], rating[2], price[2], title[2], image[2]));

        dashboardData.add(new DashboardData(city[3], location[3], rating[3], price[3], title[3], image[3]));

        dashboardData.add(new DashboardData(city[4], location[4], rating[4], price[4], title[4], image[4]));

        dashboardData.add(new DashboardData(city[5], location[5], rating[5], price[5], title[5], image[5]));

        dashboardData.add(new DashboardData(city[6], location[6], rating[6], price[6], title[6], image[6]));


        recyclerView.setAdapter(new DasboardAdapter(getActivity(), context, dashboardData));

        return view;
    }
}