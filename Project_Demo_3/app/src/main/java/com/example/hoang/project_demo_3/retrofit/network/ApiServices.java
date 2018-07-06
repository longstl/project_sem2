package com.example.hoang.project_demo_3.retrofit.network;

import com.example.hoang.project_demo_3.entity.Account;
import com.example.hoang.project_demo_3.entity.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {
    /**
     * Create interface + method GET list
     */
    @Headers({
            "Accept: application/json",
            "User-Agent: Mozilla/5.0"
    })

    @GET("account/{phone}")
    Call<Account> resForAccount(@Path("phone") String phone);

    @POST("account/signup/")
    Call<Account> resForSignUp(@Body Account account);

    @GET("category/")
    Call<List<Category>> resForListCategory();
}
