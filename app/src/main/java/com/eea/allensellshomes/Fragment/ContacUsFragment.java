package com.eea.allensellshomes.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.eea.allensellshomes.R;



public class ContacUsFragment extends Fragment {

    EditText name,email,subject,message;
    Button submit;

    public ContacUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contac_us, container, false);

        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        subject=view.findViewById(R.id.subject);
        message=view.findViewById(R.id.message);
        submit=view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String _name=name.getText().toString();
                    String _email=email.getText().toString();
                    String _subject=subject.getText().toString();
                    String _message=message.getText().toString();
                    SharedPreferences prefs = getActivity().getSharedPreferences("shared",getContext().MODE_PRIVATE);
                    String email=  prefs.getString("email", "");
                    String token=prefs.getString("token","");

                }
            }
        });


        return  view;
    }

    public Boolean validate(){
        Boolean flag=true;
        if(name.getText().toString().isEmpty()){
            name.setError("Please Enter Your Name");
            flag=false;
        }
        else if(email.getText().toString().isEmpty()){
            email.setError("Please Enter Your email");
            flag=false;
        }
        else if(subject.getText().toString().isEmpty()){

            message.setError("Please Enter the message");
            flag=false;
        }
        else if(message.getText().toString().isEmpty()){

            message.setError("Please Enter Your Message");
            flag=false;
        }
        return  flag;
    }

}