package com.example.hoang.project_demo_3.retrofit.network;

import com.example.hoang.project_demo_3.entity.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryServices {

    @GET("api/category")
    Call<List<Category>> getAllCategory();

    @GET("api/category/{category_Title}")
    Call<Category> getCategoryByCategoryTitle(@Path("category_Title") int category_Title);
}
