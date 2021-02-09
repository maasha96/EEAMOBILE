package com.eea.allensellshomes.Service;

import com.eea.allensellshomes.Dto.FavoritesDto;
import com.eea.allensellshomes.Model.Favorites;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoritesService {

    @POST("api/favorites/addToFavorites")
    Call<ResponseBody> addSaveList(@Body FavoritesDto favoritesDto, @Header("Authorization") String accessToken);

    @GET("api/favorites/get/{id}")
    Call<List<Favorites>> getSaveList(@Path("id") String id, @Header("Authorization") String accessToken);

    @DELETE("api/favorites/deleteItem/{id}")
    Call<ResponseBody> deleteItem(@Path("id") Long id, @Header("Authorization") String accessToken);

}
