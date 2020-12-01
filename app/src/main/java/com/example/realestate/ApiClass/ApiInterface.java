package com.example.realestate.ApiClass;

import com.example.realestate.Model.Login;
import com.example.realestate.Model.REST.ResetPassword;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Model.Register;

import retrofit2.Call;
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


    @POST("/api/reset-password")
    Call<ResetPasswordResponse> RESET_PASWORD_CALL(@Query("email") String email);


    @POST("/api/verify-code")
    Call<ResetPasswordResponse> OTP_CALL(@Query("num_entered") String code);



}
