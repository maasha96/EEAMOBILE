package com.eea.allensellshomes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eea.allensellshomes.Adapter.FavoritesAdapter;
import com.eea.allensellshomes.Adapter.HouseAdapter;
import com.eea.allensellshomes.Model.Favorites;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.FavoritesService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HouseAdapter houseAdapter;
    List<Favorites> saveList;
    ImageView back;
    TextView title;

    public void previous(){
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView=findViewById(R.id.recycleView);
        back=findViewById(R.id.backicon);
        title=findViewById(R.id.title);
        title.setText("Save List");
        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        saveList=new ArrayList<>();


        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        FavoritesService favoritesService=retrofit.create(FavoritesService.class);
        Call<List<Favorites>> pp=favoritesService.getSaveList(email,token);
        pp.enqueue(new Callback<List<Favorites>>() {
            @Override
            public void onResponse(Call<List<Favorites>> call, Response<List<Favorites>> response) {
                saveList = response.body();
                FavoritesAdapter favoritesAdapter=new FavoritesAdapter(getApplicationContext(),saveList);
                recyclerView.setAdapter(favoritesAdapter);
                favoritesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Favorites>> call, Throwable t) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });


    }
}