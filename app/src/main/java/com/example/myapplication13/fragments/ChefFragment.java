package com.example.myapplication13.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication13.ChefMenu;
import com.example.myapplication13.ChefMenuAdapter;
import com.example.myapplication13.EditMenu;
import com.example.myapplication13.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChefFragment extends Fragment {
    public static final String TAG = "ChefFragment";

    private RecyclerView rvMenu;
    private ChefMenuAdapter adapter;
    private List<ChefMenu> all_item;
    private Button addbutton;

    public ChefFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.menu_item, container, false );
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMenu = view.findViewById(R.id.menuitem);
        all_item = new ArrayList<>();
        queryPosts();
        adapter = new ChefMenuAdapter(getContext(), all_item);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        addbutton = view.findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonadd();
            }

            private void buttonadd() {
                Intent i = new Intent(getContext(), EditMenu.class);
                startActivity(i);
            }
        });
    }

    private void queryPosts() {
        ParseQuery<ChefMenu> query = ParseQuery.getQuery(ChefMenu.class);
        query.findInBackground(new FindCallback<ChefMenu>() {
            @Override
            public void done(List<ChefMenu> items, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (ChefMenu item: items){
                    Log.i(TAG, "item:" + item.getDescription());
                }
                all_item.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

//    public void buttonadd(View view) {
//        Intent i = new Intent(getContext(), EditMenu.class);
//        startActivity(i);
//    }

}
