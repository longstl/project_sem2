package com.example.hoang.project_demo_3.retrofit.retrofit;

import com.example.hoang.project_demo_3.retrofit.network.ApiServices;
import com.example.hoang.project_demo_3.retrofit.network.CategoryServices;
import com.example.hoang.project_demo_3.retrofit.network.ProductService;

import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static final String BASE_URL = "https://daokhanh-201004.appspot.com/_api/v1/";

    private static final String PROJECT_BASE_URL = "https://quiet-cliffs-41062.herokuapp.com/";

    public static ApiServices getApiervice() {
//        return RetrofitClient.getClient(BASE_URL).create(ApiServices.class);
        return RetrofitClient.getClient(BASE_URL).create(ApiServices.class);
    }

    public static ProductService getProductService() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(PROJECT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductService.class);
    }

    public static CategoryServices getCategoryService() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(PROJECT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CategoryServices.class);
    }
}
