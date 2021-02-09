package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eea.allensellshomes.Adapter.AppointmentsAdapter;
import com.eea.allensellshomes.AdminManagement.AdminAdapter.ReservedAdapter;
import com.eea.allensellshomes.Dto.ReservedHousesDto;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservedHouseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AppointmentsAdapter vehicleAdapter;
    List<ReservedHousesDto> reservedVehicle;
    ImageView back;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_house);

        recyclerView=findViewById(R.id.recycleView);
        back=findViewById(R.id.backicon);
        title=findViewById(R.id.title);
        title.setText("Reserved Vehicle List");
        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reservedVehicle=new ArrayList<>();


        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        HouseService houseService=retrofit.create(HouseService.class);
        Call<List<ReservedHousesDto>> pp=houseService.getAllReservedHouses(token);
        pp.enqueue(new Callback<List<ReservedHousesDto>>() {
            @Override
            public void onResponse(Call<List<ReservedHousesDto>> call, Response<List<ReservedHousesDto>> response) {
                reservedVehicle = response.body();
                ReservedAdapter reservedAdapter=new ReservedAdapter(getApplicationContext(),reservedVehicle);
                recyclerView.setAdapter(reservedAdapter);
            }
            @Override
            public void onFailure(Call<List<ReservedHousesDto>> call, Throwable t) {

            }
        });
    }
}