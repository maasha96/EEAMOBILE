package com.eea.allensellshomes.Service;

import com.eea.allensellshomes.Dto.HouseDto;
import com.eea.allensellshomes.Dto.HouseRequest;
import com.eea.allensellshomes.Dto.ReservedHousesDto;
import com.eea.allensellshomes.Model.House;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HouseService {

    @GET("api/house/getResHouses")
    Call<List<House>> getHouses();

    @GET("api/house/getResHouseById/{id}")
    Call<House> getHouseById(@Path("id") Long id);

    @GET("api/house/search/{noOfRooms}/{noOfGarages}")
    Call<List<House>> search(@Path("noOfRooms") String make, @Path("noOfGarages") String model);

    @PUT("api/house/updateHousePrice")
    Call<Object> updateHousePrice(@Body HouseRequest houseRequest, @Header("Authorization") String accessToken);

    @DELETE("api/house/deleteHouse/{id}")
    Call<Object> deleteHouse(@Path("id") Long id, @Header("Authorization") String accessToken);

    @GET("api/house/getALlReservedHouses")
    Call<List<ReservedHousesDto>> getAllReservedHouses( @Header("Authorization") String accessToken);

    @PUT("api/house/updateHouseStatus")
    Call<Object> updateHouse(@Body HouseDto vehicleDTO, @Header("Authorization") String accessToken);

    @PUT("api/house/cancelReservation")
    Call<Object> cancelReservation(@Body HouseDto houseDto, @Header("Authorization") String accessToken);
}
