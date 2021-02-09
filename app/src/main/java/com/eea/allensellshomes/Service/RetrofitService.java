package com.eea.allensellshomes.Service;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    static  String  BASE_URL = "http://192.168.8.181:8080/";
    public static retrofit2.Retrofit getRetrofit(){
        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static String url(){

        return  BASE_URL;
    }
}
