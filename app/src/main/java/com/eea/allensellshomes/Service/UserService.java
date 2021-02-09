package com.eea.allensellshomes.Service;

import com.eea.allensellshomes.Model.JwtResponse;
import com.eea.allensellshomes.Model.SignInRequest;
import com.eea.allensellshomes.Model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("api/users/signup")
    Call<User> registerUser(@Body User user);

    @POST("api/users/signin")
    Call<JwtResponse> loginUser(@Body SignInRequest user);

    @GET("api/users/getAllUser")
    Call<List<User>> getAllUser(@Header("Authorization") String accessToken);

    @DELETE("api/users/deleteUser/{id}")
    Call<ResponseBody> deleteUser(@Path("id") String id, @Header("Authorization") String accessToken);

    @GET("api/users/getUser/{id}")
    Call<User> getUser(@Path("id") String id, @Header("Authorization") String accessToken);

}
