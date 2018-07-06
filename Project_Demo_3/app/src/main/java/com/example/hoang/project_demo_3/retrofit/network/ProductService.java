package com.example.hoang.project_demo_3.retrofit.network;

import com.example.hoang.project_demo_3.entity.MyProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ProductService {
    /**
     * Create interface + method GET list
     */
    @Headers({
            "Accept: application/json",
            "User-Agent: Mozilla/5.0"
    })

    @GET("api/products")
    Call<List<MyProduct>> getAllProduct();

    @GET("api/products/category/{category}")
    Call<List<MyProduct>> getProductbyCategory(@Path("category") int category);

    @GET("api/products/{id}")
    Call<MyProduct> getProductbyid(@Path("id") int id);

}
