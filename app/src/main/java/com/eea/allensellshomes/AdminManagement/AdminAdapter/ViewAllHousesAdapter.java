package com.eea.allensellshomes.AdminManagement.AdminAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eea.allensellshomes.Dto.HouseRequest;
import com.eea.allensellshomes.Model.House;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllHousesAdapter extends RecyclerView.Adapter<ViewAllHousesAdapter.HouseViewHolder>{


    private Context context;
    private List<House> pendingPost;

    public ViewAllHousesAdapter(Context context, List<House> pendingPost) {
        this.context = context;
        this.pendingPost = pendingPost;
    }

    @NonNull
    @Override
    public ViewAllHousesAdapter.HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_view_all_houses,parent,false);

        ViewAllHousesAdapter.HouseViewHolder holder=new ViewAllHousesAdapter.HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewAllHousesAdapter.HouseViewHolder holder, final int position) {
        final House house = pendingPost.get(position);

        holder.category.setText(house.getCategory());
        holder.location.setText(house.getLocation());
        holder.rooms.setText(house.getNumOfRooms());
        holder.baths.setText(house.getNumOfbBaths());
        holder.price.setText(String.valueOf(house.getPrice()));
        holder.lotSize.setText(String.valueOf(house.getLotSize()));

        SharedPreferences prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        final String token=prefs.getString("token","");
        if(!house.getStatus().equals("Available")) {
            holder.update.setEnabled(false);
        }
        if(!house.getStatus().equals("Available")) {
            holder.delete.setEnabled(false);
        }
        holder.update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.update_house, null);

                final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
                Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
                Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS

                        HouseRequest vehicleRequest = new HouseRequest((long) house.getId(), Double.valueOf(editText.getText().toString()));
                        retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                        HouseService houseService = retrofit.create(HouseService.class);
                        Call<Object> pp = houseService.updateHousePrice(vehicleRequest, token);
                        pp.enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                try {
                                    Object Response = response.body();
                                    if (Response.equals(true)) {
                                        notifyItemChanged(position);
                                        Toast.makeText(context, "Sucessfully Updated House Price", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(context, "Cannot Update House Price", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable thtow) {
                            }
                        });

                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }


        });

        if(
                house.getImagePath()==null
        ){
            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTs4gAhdje5HbqJ9yVq9O1LSnf7ui6Dh1iG9SMX3u6ZLFSAc4caxw&s").into(holder.imageView);
        }
        else {
            String image= house.getCovrimg();
            int index = image.lastIndexOf('/');
            String lastString = image.substring(index +1);

            String updatedImage= RetrofitService.url()+"download/"+lastString;

            Picasso.get()
                    .load(Uri.parse(updatedImage))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        }


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit2.Retrofit retrofit = RetrofitService.getRetrofit();
                HouseService houseService = retrofit.create(HouseService.class);
                Call<Object> pp = houseService.deleteHouse((long) house.getId(),token);
                pp.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        try {
                            Object Response = response.body();
                            if(Response.equals(true)){
                                pendingPost.remove(position);
                                // context.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, pendingPost.size());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Sucessfully Deleted House", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            Toast.makeText(context, "Cannot Delete House", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable thtow) {
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return pendingPost.size();
    }


    class HouseViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView category, baths, rooms,location,lotSize,price;
        Button update, delete;
        ImageView imageView;


        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card);
            price = itemView.findViewById(R.id.price);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
            category = itemView.findViewById(R.id.category);
            rooms = itemView.findViewById(R.id.rooms);
            baths = itemView.findViewById(R.id.baths);
            imageView=itemView.findViewById(R.id.image);
            location=itemView.findViewById(R.id.location);
            lotSize=itemView.findViewById(R.id.lotsize);

        }
    }
}