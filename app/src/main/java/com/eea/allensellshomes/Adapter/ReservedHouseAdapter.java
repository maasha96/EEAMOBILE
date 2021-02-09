package com.eea.allensellshomes.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.Activity.HouseDetailsActivity;
import com.eea.allensellshomes.Dto.ReservedHousesDto;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReservedHouseAdapter extends  RecyclerView.Adapter<ReservedHouseAdapter.HouseViewHolder> {


    private Context context;
    private List<ReservedHousesDto> reservedHousesDtos;

    public ReservedHouseAdapter(Context context, List<ReservedHousesDto> reservedHousesDtos) {
        this.context = context;
        this.reservedHousesDtos = reservedHousesDtos;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_reserved_adapter,parent,false);
        ReservedHouseAdapter.HouseViewHolder holder=new ReservedHouseAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, final int position) {
        final ReservedHousesDto house = reservedHousesDtos.get(position);
        holder.category.setText(house.getCategory());
        holder.location.setText(house.getLocation());
        holder.price.setText(String.valueOf(house.getPrice()));

        if(
                house.getCovrimg()==null
        ){

            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTs4gAhdje5HbqJ9yVq9O1LSnf7ui6Dh1iG9SMX3u6ZLFSAc4caxw&s").into(holder.imageView);
        }
        else {
            String image=house.getCovrimg();
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


    }

    @Override
    public int getItemCount() {
        return reservedHousesDtos.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView category,location,price,color;
        CardView viewHouse;


        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.image);
            category=itemView.findViewById(R.id.category);
            viewHouse=itemView.findViewById(R.id.card);
            location=itemView.findViewById(R.id.location);
            price=itemView.findViewById(R.id.price);

        }
    }

}