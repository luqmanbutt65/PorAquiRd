package com.example.realestate.ApiClass;

import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.Like.LikeResponse;
import com.example.realestate.Model.Like.PropertiesLike_Data;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.MyProject.MyProperties_Response;
import com.example.realestate.Model.REST.Properties.Properties_Add_Response;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.PropertiesSingle.PropertiesSingleResp;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Model.Register;
import com.example.realestate.Model.UserInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    Call<ResetPasswordResponse> RESETPASSCoode_CALL(@Query("verification_code") String Code);


    @POST("/api/new-password")
    Call<ResetPasswordResponse> RESETPASS_CALL(@Query("email") String email,
                                               @Query("password") String password);


    @GET("/api/get_properties")
    Call<Properties_Response> DASHBOARDDATA_CALL(@Query("id") String id);


//TODO:today work 4/12/2020

    @POST("/api/profile_password")
    Call<ResetPasswordResponse> RESETPROFILEPASS_CALL(


            @Query("id") int id,
            @Query("old") String old,
            @Query("new_pass") String new_pass,
            @Query("confirm_pass") String confirm_pass,
            @Query("email") String email
    );


    @POST("/api/profile")
    Call<Login> UPDATEPROFIL_CALL(
            @Query("id") int Id,
            @Query("name") String name,
            @Query("number") String number,
            @Query("city") String city,
            @Query("sector") String sector,
            @Query("your_id") String your_id,
            @Query("rnc") String rnc,
            @Query("company_name") String company_name,
            @Query("address") String address,
            @Query("phone_number") String phone_number);


    @POST("/api/login")
    Call<Login> GETPROFILE_CALL(@Query("email") String email,
                                @Query("password") String password);


    // 8/12/2020

    @GET("/api/get_property/{id}")
    Call<PropertiesSingleResp> PROPERTY_CALL(@Path(value = "id", encoded = true) String id);


    // description button

    @POST("/api/property_favourite")
    Call<LikeResponse> LIKEPROPERTY_CALL(@Query("user_id") String user_id,
                                         @Query("property_id") String property_id);


    //    // Add Property
    @Multipart
    @POST("/api/add_property")
    Call<Properties_Add_Response> ADD_PROPERTY_DATA(
            @Part("user_id") RequestBody user_id,
            @Part("sale_type") RequestBody status,
            @Part("property_type") RequestBody property_type,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("location") RequestBody location,
            @Part("city") RequestBody city,
            @Part("sector") RequestBody sector,
            @Part("bedrooms") RequestBody bedroom,
            @Part("bathrooms") RequestBody bathroom,
            @Part("area") RequestBody unit_of_measure,
            @Part("date_of_construction") RequestBody date_of_construction,
            @Part("pets") RequestBody petroom,
            @Part("parking") RequestBody parkinglot,
            @Part("property_condition") RequestBody property_condition,

            @Part MultipartBody.Part featureImage);

   // @Part MultipartBody.Part[] propertyImages,
    @POST("/api/favourite_properties")
    Call<PropertiesLike_Response> FAV_CALL(@Query("id") String id);


    @POST("/api/my_properties")
    Call<MyProperties_Response> MYPROJECT_CALL(@Query("id") String id);


//get cities
    @POST("/api/get_cities")
    Call<GetCitiesListResponse> CITYLIST_CALL();


//get propert type

    @POST("/")
    Call<GetCitiesListResponse> PROPERTY_TYPE_LIST_CALL();
}
