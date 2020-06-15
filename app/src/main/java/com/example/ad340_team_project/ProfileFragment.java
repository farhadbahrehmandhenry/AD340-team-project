package com.example.ad340_team_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    TextView profileName;
    TextView profileAge;
    TextView profileSize;
    TextView profileBreed;
    TextView profileDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String[] data = ((ProfileActivity) Objects.requireNonNull(getActivity())).getExtraData();

        profileName = view.findViewById(R.id.tv_name);
        profileAge = view.findViewById(R.id.tv_age);
        profileSize = view.findViewById(R.id.tv_size);
        profileBreed = view.findViewById(R.id.tv_bread);
        profileDescription = view.findViewById(R.id.tv_description);

        profileName.setText(data[0]);
        profileAge.setText(data[1]);
        profileSize.setText(data[2]);
        profileBreed.setText(data[4]);
        profileDescription.setText(data[3]);

        return view;
    }
}
