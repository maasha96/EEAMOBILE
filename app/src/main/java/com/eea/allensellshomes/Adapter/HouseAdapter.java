package com.eea.allensellshomes.Adapter;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.Activity.HouseDetailsActivity;
import com.eea.allensellshomes.Model.House;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HouseAdapter  extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    private Context context;
    private List<House> houseList;


    public HouseAdapter(Context context, List<House> houseList) {
        this.context = context;
        this.houseList = houseList;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_house_adapter,parent,false);
        HouseViewHolder holder=new HouseViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        final House house = houseList.get(position);
        holder.imageView.setImageResource(R.drawable.ic_person_black_24dp);
        holder.desc.setText(house.getDescription());
        holder.price.setText("Rs :"+String.valueOf(house.getPrice()).toString());
        holder.location.setText(house.getLocation());
        holder.size.setText("Sqft : " + String.valueOf(house.getLotSize()).toString());

        if(
                house.getImagePath()==null
        ){
            Picasso.get().load("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/brewster-mcleod-architects-1486154143.jpg").into(holder.imageView);
        }
        else{
            String image= house.getCovrimg();
            int index = image.lastIndexOf('/');
            String lastString = image.substring(index +1);
            String updatedImage= RetrofitService.url()+"download/"+lastString;

            Picasso.get()
                    .load(Uri.parse(updatedImage))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);

        /*
        Bitmap bitmap = null;

        try {

            URL urlImage = new URL(
                    vehicle.getCovrimg());
            HttpURLConnection connection = (HttpURLConnection) urlImage
                    .openConnection();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/





            //  Picasso.get().load(vehicle.getVehicleImages().get(1).getImagePath()).into(holder.imageView);

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.layout,new DetailFragment()).commit();
                Intent intent=new Intent( context, HouseDetailsActivity.class);
                intent.putExtra("id",house.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return houseList.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView location,price,size,desc;
        CardView cardView;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            imageView =itemView.findViewById(R.id.imageView7);
            size=itemView.findViewById(R.id.textViewTitle);
            desc=itemView.findViewById(R.id.textViewDes);
            location=itemView.findViewById(R.id.textViewlocation);
            price=itemView.findViewById(R.id.textViewPrice);


        }
    }
}