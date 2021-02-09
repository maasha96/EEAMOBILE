package com.eea.allensellshomes.AdminManagement.AdminAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.eea.allensellshomes.Model.User;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.eea.allensellshomes.Service.UserService;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.HouseViewHolder>{

    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> user) {
        this.context = context;
        this.users = user;
    }
    @NonNull
    @Override
    public UserAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_user_adapter,parent,false);

        UserAdapter.HouseViewHolder holder=new UserAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.HouseViewHolder holder, final int position) {
        final User userDTO = users.get(position);
//        if(appointment.getStatus().equals("")){
//            holder.cancel.setVisibility(View.GONE);
//        }
        holder.name.setText(userDTO.getFullName());
        holder.email.setText(userDTO.getEmail());
        holder.number.setText(userDTO.getNumber());
        if(userDTO.getEmail().equals("admin@gmail.com")){
            holder.cancel.setVisibility(View.GONE);
        }
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
                String token=prefs.getString("token","");
                retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                UserService userService = retrofit.create(UserService.class);
                Call<ResponseBody> pp = userService.deleteUser(userDTO.getEmail(),token);
                pp.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String Response = response.body().string();
                            if(Response.equals("Deleted")){
                                users.remove(position);
                                //  context.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, users.size());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Successfully Removed User", Toast.LENGTH_SHORT).show();
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
        return users.size();
    }


    class HouseViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,number;
        Button cancel;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cancel=itemView.findViewById(R.id.delete);
            name =itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            number=itemView.findViewById(R.id.number);

        }
    }
}