package com.eea.allensellshomes.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.eea.allensellshomes.R;

public class ReviewsFragment extends Fragment {


    EditText comment;
    Button submit;
    RatingBar ratingBar;

    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reviews, container, false);

        ratingBar=view.findViewById(R.id.rating);
        comment=view.findViewById(R.id.comment);
        submit=view.findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getActivity().getSharedPreferences("shared",getContext().MODE_PRIVATE);
                String email=  prefs.getString("email", "");
                String token=prefs.getString("token","");
                float getrating = ratingBar.getRating();
                String message=comment.getText().toString();
            }
        });


        return view;
    }

}