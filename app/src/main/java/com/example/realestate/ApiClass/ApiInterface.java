package com.example.realestate.ApiClass;

import com.example.realestate.Model.GetList.GetCitiesListResponse;
import com.example.realestate.Model.GetList.GetListPropertyType.GetpropertyListResponse;
import com.example.realestate.Model.GetUpdateData.UpdateData_response;
import com.example.realestate.Model.Like.LikeResponse;
import com.example.realestate.Model.Like.PropertiesLike_Response;
import com.example.realestate.Model.Login;
import com.example.realestate.Model.MyProject.AddProperties_Response;
import com.example.realestate.Model.MyProject.MyProperties_Response;
import com.example.realestate.Model.REST.Properties.Properties_Response;
import com.example.realestate.Model.REST.PropertiesSingle.PropertiesSingleResp;
import com.example.realestate.Model.REST.ResetPasswordResponse;
import com.example.realestate.Model.Rating.GetRating.Rating_Response;
import com.example.realestate.Model.Rating.PostRating.PostRatigResp;
import com.example.realestate.Model.Register;

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

    //Login Call
    @POST("/api/login")
    Call<Login> LOGIN_CALL(@Query("email") String email,
                           @Query("password") String password);

    //SignUp Call
    @POST("/api/registration")
    Call<Register> REGISTER_CALL(@Query("name") String name,
                                 @Query("email") String email,
                                 @Query("password") String password);

    //Otp Code
    @POST("/api/verify-code")
    Call<ResetPasswordResponse> OTP_CALL(@Query("num_entered") String code);

    //Forgot Password email
    @POST("/api/reset-password")
    Call<ResetPasswordResponse> RESET_PASWORD_CALL(@Query("email") String email);

    // Otp code
    @POST("/api/verify-code-check")
    Call<ResetPasswordResponse> RESETPASSCoode_CALL(@Query("verification_code") String Code);

    //Reset Forgot Password
    @POST("/api/new-password")
    Call<ResetPasswordResponse> RESETPASS_CALL(@Query("email") String email,
                                               @Query("password") String password);

    //Listing of Properties
//    @GET("/api/get_properties")
//    Call<Properties_Response> DASHBOARDDATA_CALL(@Query("id") String id);


    //TODO:today work 4/12/2020
//Reset Password
    @POST("/api/profile_password")
    Call<ResetPasswordResponse> RESETPROFILEPASS_CALL(
            @Query("id") int id,
            @Query("old") String old,
            @Query("new_pass") String new_pass,
            @Query("confirm_pass") String confirm_pass,
            @Query("email") String email
    );

    // Update Profile
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

    // Get User Data
    @POST("/api/login")
    Call<Login> GETPROFILE_CALL(@Query("email") String email,
                                @Query("password") String password);


    // get description Of Property

    @GET("/api/get_property/{id}")
    Call<PropertiesSingleResp> PROPERTY_CALL(@Path(value = "id", encoded = true) String id);


    // description button

    @POST("/api/property_favourite")
    Call<LikeResponse> LIKEPROPERTY_CALL(@Query("user_id") String user_id,
                                         @Query("property_id") String property_id);


    // Add Property
    @Multipart
    @POST("/api/add_property")
    Call<AddProperties_Response> ADD_PROPERTY_DATA(
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
            @Part MultipartBody.Part[] propertyImages,
            @Part MultipartBody.Part featureImage);

    //Favirot Properties
    @POST("/api/favourite_properties")
    Call<PropertiesLike_Response> FAV_CALL(@Query("id") String id);

    //My Projects Properties
    @POST("/api/my_properties")
    Call<MyProperties_Response> MYPROJECT_CALL(@Query("id") String id);


    //get cities
    @GET("/api/get_cities")
    Call<GetCitiesListResponse> CITYLIST_CALL();


    //get propert type
    @GET("api/get_property_type")
    Call<GetpropertyListResponse> PROPERTY_TYPE_LIST_CALL();


    //get user for update profile
    @POST("/api/get_user")
    Call<UpdateData_response> GET_UPDATE_DATA(@Query("id") String id);


   // Send Rating

    @POST("/api/reviews")
    Call<PostRatigResp> SEND_RATING_CALL(@Query("user_id") String id,
                                         @Query("property_id") String property_id,
                                         @Query("rating") float rating,
                                         @Query("comment") String comment);


// get like properties
    @GET("/api/get_properties/{id}")
    Call<Properties_Response> LIKEPROPERTY_CALL(@Path(value = "id", encoded = true) String id);

    @GET("/api/get_property/{id}")
    Call<Rating_Response> RATING_DATA_CALL(@Path(value = "id", encoded = true) String id);


}
