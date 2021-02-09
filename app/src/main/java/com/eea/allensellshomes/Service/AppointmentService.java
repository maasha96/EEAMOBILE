package com.eea.allensellshomes.Service;

import com.eea.allensellshomes.Dto.AppointmentDto;
import com.eea.allensellshomes.Dto.AppointmentRequest;
import com.eea.allensellshomes.Model.Appointments;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AppointmentService {

    @POST("api/appointments/makeAppointments")
    Call<Object> makeAppointments(@Body AppointmentDto appointmentDTO, @Header("Authorization") String accessToken);

    @GET("api/appointments/getAppointmentByUser/{email}")
    Call<List<Appointments>> getAppointmentByUser(@Path("email") String email, @Header("Authorization") String accessToken);

    @PUT("api/appointments/cancelAppointment/{id}")
    Call<ResponseBody> cancelAppointment(@Path("id") Long id, @Header("Authorization") String accessToken);

    @GET("api/appointments/getAllAppointment")
    Call<List<Appointments>> getAllAppointment(@Header("Authorization") String accessToken);

    @PUT("api/appointments/respondToAppointment")
    Call<ResponseBody> respondToAppointment(@Body AppointmentRequest appointmentRequest, @Header("Authorization") String accessToken);
}
