package com.example.realestate.ApiClass;

import com.example.realestate.Model.Login;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Model.Register;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("/api/login")
    Call<Login> LOGIN_CALL(@Query("email") String email,
                           @Query("password") String password);

    @POST("/api/registration")
    Call<Register> REGISTER_CALL(@Query("name") String name,
                                 @Query("email") String email,
                                 @Query("password") String password);



    @POST("/api/verify-code")
    Call<ResetPasswordResponse> OTP_CALL(@Query("num_entered") String code);

    @POST("/api/reset-password")
    Call<ResetPasswordResponse> RESET_PASWORD_CALL(@Query("email") String email);

    @POST("/api/verify-code-check")
    Call<ResetPasswordResponse>RESETPASSCoode_CALL(@Query("verification_code") String Code);


    @POST("/api/new-password")
    Call<ResetPasswordResponse>RESETPASS_CALL(@Query("password") String password);


    @GET("/api/get_properties")
    Call<Properties_Response>DASHBOARDDATA_CALL();

}
