package com.example.realestate.ApiClass;

import com.example.realestate.BuildConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //http://poraquird.stepinnsolution.com

    public static final String NEW_BASE_URL = "http://poraquird.stepinnsolution.com";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .build();

            retrofit = new Retrofit.Builder().baseUrl(NEW_BASE_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;

    }

}
