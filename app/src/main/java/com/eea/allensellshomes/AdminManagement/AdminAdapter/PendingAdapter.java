package com.eea.allensellshomes.AdminManagement.AdminAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eea.allensellshomes.Dto.AppointmentRequest;
import com.eea.allensellshomes.Model.Appointments;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.AppointmentService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.HouseViewHolder> {

    private Context context;
    private List<Appointments> appointments;

    public PendingAdapter() {
    }

    public PendingAdapter(Context context, List<Appointments> appointments) {
        this.context = context;
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public PendingAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_pending_adapter,parent,false);

        PendingAdapter.HouseViewHolder holder=new PendingAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  PendingAdapter.HouseViewHolder holder, final int position) {
        final Appointments appointment = appointments.get(position);
//        if(appointment.getStatus().equals("")){
//            holder.cancel.setVisibility(View.GONE);
//        }
        holder.email.setText(appointment.getUseremail());
        holder.messsage.setText(appointment.getMessage());
        // if(!appointment.getCreatedDate().toString().isEmpty()){
        //   holder.cretedDate.setText(appointment.getCreatedDate().toString());
        //}
        holder.house.setText(String.valueOf(appointment.getHouseid()));
//        holder.appointmentDate.setText(appointment.getAppointmentDateTime().toString());
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppointmentRequest appointmentRequest=new AppointmentRequest(appointment.getId(),"Confirmed");
                method(appointmentRequest,  position);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppointmentRequest appointmentRequest=new AppointmentRequest(appointment.getId(),"Closed");
                method(appointmentRequest,  position);
            }
        });
    }

    public void method(AppointmentRequest appointmentRequest, final int position){
        SharedPreferences prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        String token=prefs.getString("token","");
        retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
        AppointmentService appointmentService = retrofit.create(AppointmentService.class);
        Call<ResponseBody> pp = appointmentService.respondToAppointment(appointmentRequest,token);
        pp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String Response = response.body().string();
                    if(Response.equals("Status Updated")){
                        appointments.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, appointments.size());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sucessfully Updated Status", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable thtow) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }


    class HouseViewHolder extends RecyclerView.ViewHolder {

        TextView messsage, cretedDate, appointmentDate,email,house;
        Button approve, reject;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            approve = itemView.findViewById(R.id.approve);
            reject = itemView.findViewById(R.id.reject);
            messsage = itemView.findViewById(R.id.message);
            cretedDate = itemView.findViewById(R.id.createdDate);
            appointmentDate = itemView.findViewById(R.id.appointmentDate);
            house=itemView.findViewById(R.id.house);
        }
    }
}