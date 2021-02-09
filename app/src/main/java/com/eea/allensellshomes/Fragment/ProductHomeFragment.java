package com.eea.allensellshomes.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eea.allensellshomes.Adapter.HouseAdapter;
import com.eea.allensellshomes.Model.House;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductHomeFragment extends Fragment {

    RecyclerView recyclerView;
    HouseAdapter houseAdapter;
    List<House> houseList;
    public ProductHomeFragment() {
        // Required empty public constructor
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_product_home, container, false);
        recyclerView=view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        houseList=new ArrayList<>();
        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        HouseService houseService=retrofit.create(HouseService.class);
        Call<List<House>> pp=houseService.getHouses();
        pp.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {

                houseList = response.body();
                List<House> newPost=new ArrayList<>();
                for (House house:houseList
                ) {
                    if(house.getStatus().equals("Available")){
                        newPost.add(house);
                    }
                }
                houseAdapter=new HouseAdapter(getContext(),newPost);
                recyclerView.setAdapter(houseAdapter);
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {

                if (t instanceof IOException) {
                    Toast.makeText(getContext(), "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(getContext(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });


        return  view;
    }


}