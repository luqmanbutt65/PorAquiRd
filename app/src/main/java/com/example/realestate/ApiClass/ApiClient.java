package com.example.realestate.ApiClass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {



    public static final String NEW_BASE_URL = "https://poraquird.stepinnsolution.com";
    public static final String BASE_URL = "http:////";
    public static final String LOCAL_BASE_URL = "http:////";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(NEW_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;

    }
}
