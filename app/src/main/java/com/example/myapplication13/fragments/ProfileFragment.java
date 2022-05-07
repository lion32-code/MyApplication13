package com.example.myapplication13.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication13.LoginActivity;
import com.example.myapplication13.R;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private ImageView imageView;
    private TextView text_name;
    private TextView text_salary;
    private Button btnlogout;
    public static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.profile_activity, container, false );
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.imageView);
        text_name = view.findViewById(R.id.text_name);
        text_salary = view.findViewById(R.id.text_salary);
        btnlogout = view.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutAction();
            }

            public void onLogoutAction() {
                ParseUser.logOut();
                final Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

        });
    }

//    public void buttonlogout(View view) {
//        ParseUser.logOut();
//        final Intent intent = new Intent(getContext(), LoginActivity.class);
//        startActivity(intent);
//        getActivity().finish();
//    }


}
