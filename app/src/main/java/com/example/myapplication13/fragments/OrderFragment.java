package com.example.myapplication13.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication13.CheckoutList;
import com.example.myapplication13.LoginActivity;
import com.example.myapplication13.R;
import com.example.myapplication13.orderAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    private double totalCost;
    private TextView tvTotalCost;
    private RecyclerView rvCheckoutList;
    private orderAdapter adapter;
    private CountDownTimer countDownTimer;
    public static ArrayList<CheckoutList> cartItemList = new ArrayList<>();
    public static final String TAG = "OrderFragment";

    public OrderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.activity_order, container, false );
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        tvTotalCost = view.findViewById(R.id.tvTotalCost);
        rvCheckoutList = view.findViewById(R.id.rvCheckoutList);
        totalCost = 0;
        adapter = new orderAdapter(getContext(), cartItemList);
        rvCheckoutList.setAdapter(adapter);
        rvCheckoutList.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
        refreshView();
    }

    private void queryPosts() {
        ParseQuery<CheckoutList> query = ParseQuery.getQuery(CheckoutList.class);
        query.findInBackground(new FindCallback<CheckoutList>() {
            @Override
            public void done(List<CheckoutList> items, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (CheckoutList item: items){
                    Log.i(TAG, "item:" + item.getItem());
                }
                cartItemList.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void refreshView() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                System.out.println("finish timer");
                cartItemList.clear();
                queryPosts();
                adapter.notifyDataSetChanged();
                refreshView();
            }
        }.start();
    }

}
