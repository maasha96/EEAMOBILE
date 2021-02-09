package com.eea.allensellshomes.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eea.allensellshomes.Activity.HouseDetailsActivity;
import com.eea.allensellshomes.Model.Favorites;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.FavoritesService;
import com.eea.allensellshomes.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.HouseViewHolder>  {


    private Context context;
    private List<Favorites> favoritesList;


    public FavoritesAdapter(Context context, List<Favorites> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_favorites_adapter,null);
        HouseViewHolder holder=new HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HouseViewHolder holder, final int position) {
        final Favorites favorites = favoritesList.get(position);
//        holder.imageView.setImageResource(R.drawable.ic_person_black_24dp);
        holder.location.setText(favorites.getHouse().getLocation()+" "+favorites.getHouse().getDescription());
        holder.garages.setText(favorites.getHouse().getNumOfGarages());
        holder.price.setText(String.valueOf(favorites.getHouse().getPrice()));

        if(
                favorites.getHouse().getCovrimg()==null
        ){
            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTs4gAhdje5HbqJ9yVq9O1LSnf7ui6Dh1iG9SMX3u6ZLFSAc4caxw&s").into(holder.imageView);
        }
        else {
            String image=favorites.getHouse().getCovrimg();
            int index = image.lastIndexOf('/');
            String lastString = image.substring(index +1);
            String updatedImage= RetrofitService.url()+"download/"+lastString;

            Picasso.get()
                    .load(Uri.parse(updatedImage))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent((context), HouseDetailsActivity.class);
                intent.putExtra("id",favorites.getHouse().getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs =  context.getSharedPreferences("shared",Context.MODE_PRIVATE);
                String email=  prefs.getString("email", "");
                String token=prefs.getString("token","");
                retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                FavoritesService saveListService = retrofit.create(FavoritesService.class);
                Call<ResponseBody> pp = saveListService.deleteItem(favorites.getId(),token);
                pp.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String Response = response.body().string();
                            if(Response.equals("Removed From Saved List")){
//                                Intent intent=new Intent(context, SaveListActivity.class);
//                                 context.startActivity(intent);
                                favoritesList.remove(position);
                                //  context.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, favoritesList.size());
                                notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(context, "Error Removing", Toast.LENGTH_SHORT).show();
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
        return favoritesList.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView location,garages,price;
        CardView cardView;
        Button delete;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            imageView =itemView.findViewById(R.id.image);
            location=itemView.findViewById(R.id.location);
            garages=itemView.findViewById(R.id.garages);
            price=itemView.findViewById(R.id.price);
            delete=itemView.findViewById(R.id.delete);

        }
    }


}