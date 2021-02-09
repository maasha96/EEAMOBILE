package com.eea.allensellshomes.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eea.allensellshomes.Activity.AppointmentActivity;
import com.eea.allensellshomes.Activity.FavoritesActivity;
import com.eea.allensellshomes.Activity.ProfileActivity;
import com.eea.allensellshomes.Activity.ReservedHouseActivity;
import com.eea.allensellshomes.Activity.MainActivity;
import com.eea.allensellshomes.R;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    CardView savedList,appointments,posts,reserved,profile,settings;
    Button logout;
    TextView email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("shared",getContext().MODE_PRIVATE);
        String emails=  prefs.getString("email", "");
        savedList=view.findViewById(R.id.savecard);

        appointments=view.findViewById(R.id.appointmentcard);
        email=view.findViewById(R.id.email);
        reserved=view.findViewById(R.id.reservedcard);
        profile=view.findViewById(R.id.profilecard);
        settings=view.findViewById(R.id.settingcard);
        email.setText(emails);
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        savedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FavoritesActivity.class);
                startActivity(intent);
            }
        });
//        posts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getContext(), MyPostActivity.class);
//                startActivity(intent);
//            }
//        });
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AppointmentActivity.class);
                startActivity(intent);
            }
        });
        reserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ReservedHouseActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}