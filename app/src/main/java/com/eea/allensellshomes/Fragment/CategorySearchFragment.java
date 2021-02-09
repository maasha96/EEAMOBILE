//package com.eea.allensellshomes.Fragment;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.eea.allensellshomes.Model.House;
//import com.eea.allensellshomes.R;
//import com.eea.allensellshomes.Service.HouseService;
//import com.eea.allensellshomes.Service.RetrofitService;
//
//import java.io.Serializable;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class CategorySearchFragment extends Fragment {
//
//    Button singleFamily,apartment,bungelow,Condo,town_home,search;
//    String noOfRooms,noOfBaths,noOfGarages;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view= inflater.inflate(R.layout.fragment_category_search, container, false);
//        singleFamily=view.findViewById(R.id.single_family);
//        apartment=view.findViewById(R.id.apartment);
//        bungelow=view.findViewById(R.id.bungelow);
//        Condo=view.findViewById(R.id.Condo);
//        town_home=view.findViewById(R.id.town_home);
//        search=view.findViewById(R.id.search);
//
//
//
//        singleFamily.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchByType("Single-Family");
//            }
//        });
//        apartment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchByType("Apartment");
//            }
//        });
//
//        bungelow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchByType("Bungalow");
//            }
//        });
//
//        Condo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchByType("Condo");
//            }
//        });
//
//        final EditText dropdown1 =view.findViewById(R.id.category);
//        final EditText dropdown2 =view.findViewById(R.id.location);
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                noOfBaths = dropdown1.getText().toString();
//                noOfGarages = dropdown2.getText().toString();
//                if (noOfBaths.isEmpty()) {
//                    dropdown1.setError("Enter Number of Rooms ");
//                } else if (noOfGarages.isEmpty()) {
//                    dropdown2.setError("Enter Number of garages  Model");
//                } else {
//                    Retrofit retrofit = RetrofitService.getRetrofit();
//                    HouseService houseService = retrofit.create(HouseService.class);
////                    Call<List<House>> pp = houseService.search(make, model, year);
////                    pp.enqueue(new Callback<List<Vehicle>>() {
////                        @Override
////                        public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
////                            try {
////                                List<Vehicle> aa = response.body();
////                                if(aa.size()!=0) {
////                                    Bundle bundle = new Bundle();
////                                    // bundle.putString("latitude", latitude);
////                                    bundle.putSerializable("KEY_ARRAYLIST", (Serializable) aa);
////
////                                    SearchResult mapFragment = new SearchResult();
////                                    mapFragment.setArguments(bundle);
////
////                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout, mapFragment).commit();
////                                }
////                                else{
////                                    Toast.makeText(getContext(), "No Vehicles Available" , Toast.LENGTH_SHORT).show();
////                                }
////                            } catch (Exception e) {
////
////                            }
////                        }
////
////                        @Override
////                        public void onFailure(Call<List<Vehicle>> call, Throwable thtow) {
////                        }
////                    });
////                }
////            }
////        });
//
//
////
////                }
////            }
////}