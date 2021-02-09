package com.eea.allensellshomes.AdminManagement.AdminAdapter;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eea.allensellshomes.Activity.HouseDetailsActivity;
import com.eea.allensellshomes.Dto.HouseDto;
import com.eea.allensellshomes.Dto.ReservedHousesDto;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservedAdapter extends RecyclerView.Adapter<ReservedAdapter.HouseViewHolder>{

    private Context context;
    private List<ReservedHousesDto> reservedHousesDtos;

    public ReservedAdapter(Context context, List<ReservedHousesDto> reservedHousesDtos) {
        this.context = context;
        this.reservedHousesDtos = reservedHousesDtos;
    }
    @NonNull
    @Override
    public ReservedAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_reserved_adapter,parent,false);
        ReservedAdapter.HouseViewHolder holder=new ReservedAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservedAdapter.HouseViewHolder holder, final int position) {
        final ReservedHousesDto house = reservedHousesDtos.get(position);
        holder.category.setText(house.getCategory());
        holder.location.setText(house.getLocation());
//        holder.price.setText("RS :"+String.valueOf(house.getPrice()));
//        holder.status.setText(house.getStatus());

        if(
                house.getImagePath()==null
        ){

            Picasso.get().load("https://images.unsplash.com/photo-1580587771525-78b9dba3b914?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80").into(holder.imageView);
        }
        else {
            String image=house.getImagePath();
            int index = image.lastIndexOf('/');
            final String lastString = image.substring(index +1);
            String updatedImage= RetrofitService.url()+"download/"+lastString;

            Picasso.get()
                    .load(Uri.parse(updatedImage))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        }

        holder.viewHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent((context), HouseDetailsActivity.class);
                intent.putExtra("id",house.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences prefs =  context.getSharedPreferences("shared",Context.MODE_PRIVATE);
//                String email=  prefs.getString("email", "");
//                String token=prefs.getString("token","");
//                retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
//                HouseService houseService = retrofit.create(HouseService.class);
//                HouseDto houseDto=new HouseDto(house.getId(), "Reserved", email);
//                Call<Object> pp = houseService.cancelReservation(houseDto,token);
//                pp.enqueue(new Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//                        try {
//                            Object j = response.body();
//                            if (j.equals(true) ) {
//                                reservedHousesDtos.remove(position);
//                                //  context.removeViewAt(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position, reservedHousesDtos.size());
//                                notifyDataSetChanged();
//                                Toast.makeText(context, " Cancelled Reservation Successfully", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(context, "Error In Cancellation", Toast.LENGTH_SHORT).show();
//                            }
//                        }catch (Exception e){
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable thtow) {
//                    }
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return reservedHousesDtos.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView category,location,year,price,color,status;
        CardView viewHouse;
        Button delete;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.image);
            category=itemView.findViewById(R.id.category);
            viewHouse=itemView.findViewById(R.id.card);
            location=itemView.findViewById(R.id.location);
            year=itemView.findViewById(R.id.garages);
            price=itemView.findViewById(R.id.price);
            color=itemView.findViewById(R.id.reservedBy);
            status=itemView.findViewById(R.id.status);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}