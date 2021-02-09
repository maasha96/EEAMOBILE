package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.eea.allensellshomes.Model.User;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.eea.allensellshomes.Service.UserService;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);

        String userEmail=  prefs.getString("email", "");
        String token=prefs.getString("token", "");
        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        UserService userService=retrofit.create(UserService.class);
        Call<User> pp=userService.getUser(userEmail,token);

        pp.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
//                name.setText(user.getFullName());
                email.setText(user.getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void previous(){
        super.onBackPressed();
    }

    public void Initialize(){



    }

}