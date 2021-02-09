//package com.eea.allensellshomes.AdminManagement.AdminFragment.ManageProducts;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.eea.allensellshomes.Model.House;
//import com.eea.allensellshomes.R;
//import com.eea.allensellshomes.Service.HouseService;
//import com.eea.allensellshomes.Service.RetrofitService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static android.content.Context.MODE_PRIVATE;
//
//public class MyProduct extends Fragment {
//
//    public MyProduct() {
//
//    }
//    RecyclerView recyclerView;
//    List<House> pendingProducts;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view= inflater.inflate(R.layout.fragment_pending_products, container, false);
//        recyclerView=view.findViewById(R.id.recycleView);
//        recyclerView.setHasFixedSize(true);
//        SharedPreferences prefs = getActivity().getSharedPreferences("shared",MODE_PRIVATE);
//        String email=  prefs.getString("email", "");
//        String token=prefs.getString("token","");
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        pendingProducts=new ArrayList<>();
//        retrofit2.Retrofit retrofit= RetrofitService.getRetrofit();
//        final HouseService houseService=retrofit.create(HouseService.class);
//        Call<List<House>> pp=houseService.getHouses();
//        pp.enqueue(new Callback<List<House>>() {
//            @Override
//            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
//                pendingProducts = response.body();
//                List<House> newProduct=new ArrayList<>();
//                for (House house:pendingProducts
//                ) {
//                    if(house.getId().equals(true)&&house){
//                        newProduct.add(house);
//                    }
//                }
//                com.fake3.luxcars.Admin.AdminAdapters.PendingPost pendingAdapter=new com.fake3.luxcars.Admin.AdminAdapters.PendingPost(getContext(),newPost);
//                recyclerView.setAdapter(pendingAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<House>> call, Throwable t) {
//
//            }
//        })
//
//
//
//
//        return  view;
//    }
//}