package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eea.allensellshomes.Adapter.AppointmentsAdapter;
import com.eea.allensellshomes.Model.Appointments;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.AppointmentService;
import com.eea.allensellshomes.Service.RetrofitService;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AppointmentsAdapter vehicleAdapter;
    List<Appointments> appointments;
    ImageView back;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        setContentView(R.layout.activity_appointment);
        recyclerView=findViewById(R.id.recycleView);
        back=findViewById(R.id.backicon);
        title=findViewById(R.id.title);
        title.setText("My Appointments");
        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointments=new ArrayList<>();

        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        AppointmentService appointmentService=retrofit.create(AppointmentService.class);
        Call<List<Appointments>> pp=appointmentService.getAppointmentByUser(email,token);
        pp.enqueue(new Callback<List<Appointments>>() {
            @Override
            public void onResponse(Call<List<Appointments>> call, Response<List<Appointments>> response) {
                appointments = response.body();
                AppointmentsAdapter appointmentsAdapter=new AppointmentsAdapter(getApplicationContext(),appointments);
                recyclerView.setAdapter(appointmentsAdapter);
            }
            @Override
            public void onFailure(Call<List<Appointments>> call, Throwable t) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });

    }
    public void previous(){
        super.onBackPressed();
    }

}
