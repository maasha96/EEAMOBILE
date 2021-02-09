package com.eea.allensellshomes.AdminManagement.AdminFragment.ManageAppointments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.ConfirmAdapter;
import com.eea.allensellshomes.Model.Appointments;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.AppointmentService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;

    List<Appointments> appointmentDTOS;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView=view.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getActivity().getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        appointmentDTOS=new ArrayList<>();
        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        final AppointmentService appointmentService=retrofit.create(AppointmentService.class);
        Call<List<Appointments>> pp=appointmentService.getAllAppointment(token);
        pp.enqueue(new Callback<List<Appointments>>() {
            @Override
            public void onResponse(Call<List<Appointments>> call, Response<List<Appointments>> response) {
                appointmentDTOS = response.body();
                List<Appointments> newAppointment=new ArrayList<>();
                for (Appointments appointment:appointmentDTOS
                ) {
                    if(appointment.getStatus().equals("Closed")||appointment.getStatus().equals("Canceled")){
                        newAppointment.add(appointment);
                    }
                }
                ConfirmAdapter confirmAdapter=new ConfirmAdapter(getContext(),newAppointment);
                recyclerView.setAdapter(confirmAdapter);
            }
            @Override
            public void onFailure(Call<List<Appointments>> call, Throwable t) {

            }
        });

        return  view;
    }

}