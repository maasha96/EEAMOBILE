package com.eea.allensellshomes.AdminManagement.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.UserAdapter;
import com.eea.allensellshomes.Model.User;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.eea.allensellshomes.Service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageUsers extends AppCompatActivity {


    ImageView back;
    TextView title;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        back=findViewById(R.id.backicon);
        recyclerView=findViewById(R.id.recycleView);
        title=findViewById(R.id.title);
        title.setText("Manage User");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });

        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user=new ArrayList<>();

        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        final UserService userService=retrofit.create(UserService.class);
        Call<List<User>> pp=userService.getAllUser(token);
        pp.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                user = response.body();
                UserAdapter userAdapter=new UserAdapter(getApplicationContext(),user);
                recyclerView.setAdapter(userAdapter);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    public void previous(){
        super.onBackPressed();
    }
}