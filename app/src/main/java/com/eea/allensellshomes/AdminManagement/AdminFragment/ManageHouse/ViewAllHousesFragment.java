package com.eea.allensellshomes.AdminManagement.AdminFragment.ManageHouse;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eea.allensellshomes.AdminManagement.AdminAdapter.ViewAllHousesAdapter;
import com.eea.allensellshomes.Model.House;
import com.eea.allensellshomes.R;
import com.eea.allensellshomes.Service.HouseService;
import com.eea.allensellshomes.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewAllHousesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAllHousesFragment extends Fragment {

    RecyclerView recyclerView;
    List<House> houses;

    public ViewAllHousesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_all_houses, container, false);
        recyclerView=view.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);
        SharedPreferences prefs = getActivity().getSharedPreferences("shared",MODE_PRIVATE);
        String email=  prefs.getString("email", "");
        String token=prefs.getString("token","");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        houses=new ArrayList<>();
        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
        final HouseService houseService=retrofit.create(HouseService.class);
        Call<List<House>> pp=houseService.getHouses();
        pp.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                houses = response.body();
                List<House> newPost=new ArrayList<>();

                ViewAllHousesAdapter viewAllHousesAdapter=new ViewAllHousesAdapter(getContext(),newPost);
                recyclerView.setAdapter(viewAllHousesAdapter);
                }


            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {

            }
        });

        return  view;
    }
}