package com.example.hoang.project_demo_3.controller;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.database.DBModel;
import com.example.hoang.project_demo_3.entity.MyProduct;
import com.example.hoang.project_demo_3.entity.Order;
import com.example.hoang.project_demo_3.retrofit.network.ProductService;
import com.example.hoang.project_demo_3.retrofit.retrofit.ApiUtils;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsDetails extends AppCompatActivity {

    int id;
    MyProduct currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        final TextView textView_Title = (TextView) findViewById(R.id.goods_name);
        final TextView textView_Price = (TextView) findViewById(R.id.goods_price);
        final TextView textView_Description = (TextView) findViewById(R.id.good_description);
        final ImageView img = (ImageView) findViewById(R.id.img_goods);
        final FloatingActionButton btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        final ElegantNumberButton numberButton = (ElegantNumberButton) findViewById(R.id.number_button);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        //Receive Data
        Intent intent = getIntent();
        final int id = (int) intent.getExtras().get("id");

        ProductService productService = ApiUtils.getProductService();
        productService.getProductbyid(id).enqueue(new Callback<MyProduct>() {
            @Override
            public void onResponse(Call<MyProduct> call, Response<MyProduct> response) {
                if (response.isSuccessful()) {
                    {
                        currentProduct = response.body();
                        //setting value
                        textView_Title.setText(currentProduct.getTitle());
                        collapsingToolbarLayout.setTitle(currentProduct.getTitle());
                        textView_Price.setText(String.valueOf(currentProduct.getPrice()));
                        textView_Description.setText(currentProduct.getDescription());

                        Picasso.get().load(currentProduct.getThumbnail()).fit().centerInside()
                                .placeholder(R.drawable.avatar).error(R.drawable.avatar)
                                .into(img, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });

                        //Action Cart.
                        btnCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    new DBModel(getBaseContext()).addToCart(new Order(
                                            id,
                                            currentProduct.getTitle(),
                                            Integer.parseInt(numberButton.getNumber()),
                                            String.valueOf(currentProduct.getPrice())
                                    ));
                                Toast.makeText(GoodsDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<MyProduct> call, Throwable t) {

            }
        });
    }
}
