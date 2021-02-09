package com.eea.allensellshomes.AdminManagement.AdminAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eea.allensellshomes.Model.Appointments;
import com.eea.allensellshomes.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.HouseViewHolder> {
    private Context context;
    private List<Appointments> appointments;

    public ConfirmAdapter(Context context, List<Appointments> appointments) {
        this.context = context;
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public ConfirmAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_confirm_adapter,parent,false);

        ConfirmAdapter.HouseViewHolder holder=new ConfirmAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  ConfirmAdapter.HouseViewHolder holder, final int position) {
        final Appointments appointment = appointments.get(position);
//        if(appointment.getStatus().equals("")){
//            holder.cancel.setVisibility(View.GONE);
//        }
        holder.messsage.setText(appointment.getMessage());
        // if(!appointment.getCreatedDate().toString().isEmpty()){
        //   holder.cretedDate.setText(appointment.getCreatedDate().toString());
        //}

//        holder.appointmentDate.setText(appointment.getAppointmentDateTime().toString());
        holder.email.setText(appointment.getUseremail());
        holder.vehicle.setText(String.valueOf(appointment.getHouseid()));


    }



    @Override
    public int getItemCount() {
        return appointments.size();
    }


    class HouseViewHolder extends RecyclerView.ViewHolder {

        TextView messsage, cretedDate, appointmentDate,email,vehicle;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            messsage = itemView.findViewById(R.id.message);
            cretedDate = itemView.findViewById(R.id.createdDate);
            appointmentDate = itemView.findViewById(R.id.appointmentDate);
            vehicle=itemView.findViewById(R.id.house);

        }
    }
}