package com.example.hoang.project_demo_3.retrofit.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    /**
     * Create an instance of Retrofit Object.
     */

    public static Retrofit getClient(String baseUrl){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
