package com.example.hoang.project_demo_3.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.ViewHolder.GoodsRecyclerViewAdapter;
import com.example.hoang.project_demo_3.entity.MyProduct;
import com.example.hoang.project_demo_3.retrofit.network.ProductService;
import com.example.hoang.project_demo_3.retrofit.retrofit.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Goods_Activity extends AppCompatActivity {
    List<MyProduct> list_goods;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_activity);

        Intent intent = getIntent();
        int id = (int) intent.getExtras().get("id");

        ProductService productService = ApiUtils.getProductService();
        productService.getProductbyCategory(id).enqueue(new Callback<List<MyProduct>>() {
            @Override
            public void onResponse(Call<List<MyProduct>> call, Response<List<MyProduct>> response) {
                if (response.isSuccessful()) {
                    list_goods = response.body();
                    addToAdapter(list_goods);
                }
            }

            @Override
            public void onFailure(Call<List<MyProduct>> call, Throwable t) {

            }
        });
    }


    public void addToAdapter(List<MyProduct> list) {
        RecyclerView recyclerViewGoods = (RecyclerView) findViewById(R.id.recycler_list_goods);
        recyclerViewGoods.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewGoods.setLayoutManager(layoutManager);
        GoodsRecyclerViewAdapter adapter = new GoodsRecyclerViewAdapter(this, list);
        recyclerViewGoods.setAdapter(adapter);
    }
}
