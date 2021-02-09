package com.eea.allensellshomes.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.eea.allensellshomes.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eea.allensellshomes.Model.Appointments;
import com.eea.allensellshomes.Service.AppointmentService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.HouseViewHolder> {

    private Context context;
    private List<Appointments> appointments;

    public AppointmentsAdapter(Context context, List<Appointments> appointments) {
        this.context = context;
        this.appointments = appointments;

    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_appointment_adapter,parent,false);

        AppointmentsAdapter.HouseViewHolder holder=new AppointmentsAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HouseViewHolder holder, final int position) {
        final Appointments appointment = appointments.get(position);
//        if(appointment.getStatus().equals("")){
//            holder.cancel.setVisibility(View.GONE);
//        }
        holder.bookedDate.setText(appointment.getCreatedDate().toString());
//        holder.appointmentDate.setText(appointment.getAppointmentDateTime().toString());
        holder.house.setText(String.valueOf(appointment.getHouseid()));
        holder.status.setText(appointment.getStatus());
        holder.message.setText(appointment.getMessage());
        if(appointment.getStatus().equals("Closed")|| appointment.getStatus().equals("Confirmed")|| appointment.getStatus().equals("Cancelled")){
            holder.cancel.setEnabled(false);
            holder.cancel.setVisibility(View.GONE);
        }
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
                String email=  prefs.getString("email", "");
                String token=prefs.getString("token","");
                retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                AppointmentService appointmentService = retrofit.create(AppointmentService.class);
                Call<ResponseBody> pp = appointmentService.cancelAppointment(appointment.getId(),token);
                pp.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            String Response = response.body().string();
                            if(Response.equals("Status Updated")){
                                appointments.remove(position);
                                //  context.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, appointments.size());
                                notifyDataSetChanged();
                            }

                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable thtow) {
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }


    class HouseViewHolder extends RecyclerView.ViewHolder{

        TextView bookedDate,appointmentDate,message,house,status;
        Button cancel;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cancel=itemView.findViewById(R.id.cancel);
            bookedDate =itemView.findViewById(R.id.createdDate);
            house =itemView.findViewById(R.id.house);
            status =itemView.findViewById(R.id.status);
            appointmentDate=itemView.findViewById(R.id.appointmentDate);
            message=itemView.findViewById(R.id.message);

        }
    }
}
