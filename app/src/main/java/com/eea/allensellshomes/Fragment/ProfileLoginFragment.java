package com.eea.allensellshomes.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eea.allensellshomes.Activity.LoginActivity;
import com.eea.allensellshomes.Activity.RegisterActivity;
import com.eea.allensellshomes.R;

public class ProfileLoginFragment extends Fragment {

    public ProfileLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_login, container, false);
        TextView signin=view.findViewById(R.id.logbtn);
        TextView signup=view.findViewById(R.id.regbtn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                intent.putExtra("sample","login");
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}