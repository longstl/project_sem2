package com.example.hoang.project_demo_3.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.ViewHolder.CategoryRecyclerViewAdapter;
import com.example.hoang.project_demo_3.common.Common;
import com.example.hoang.project_demo_3.entity.Category;
import com.example.hoang.project_demo_3.entity.MyProduct;
import com.example.hoang.project_demo_3.retrofit.network.ApiServices;
import com.example.hoang.project_demo_3.retrofit.network.CategoryServices;
import com.example.hoang.project_demo_3.retrofit.network.ProductService;
import com.example.hoang.project_demo_3.retrofit.retrofit.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Call API or set FireBase or ....

    TextView tv_fullname, tv_email;
    ImageView img_avatar;
    RecyclerView recycler_list_categories;
    RecyclerView.LayoutManager layoutManager;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("List Category");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Home.this, Cart_Order.class);
                startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //set name for full name
        //set email for email
        //set avatar

        View headerView = navigationView.getHeaderView(0);
        tv_fullname = (TextView) headerView.findViewById(R.id.tv_fullname);
        tv_fullname.setText(Common.currentAccount.getFullname());
        tv_email = (TextView) headerView.findViewById(R.id.tv_email);
        tv_email.setText(Common.currentAccount.getEmail());
        img_avatar = (ImageView) headerView.findViewById(R.id.img_avatar);
        loadImageFromUrl(Common.currentAccount.getAvatar());

        //load menu
        recycler_list_categories = (RecyclerView) findViewById(R.id.recycler_menu);
        recycler_list_categories.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recycler_list_categories.setLayoutManager(layoutManager);
        loadMenu();
    }

    private void loadImageFromUrl(String url) {
        Picasso.get().load(url).resize(100, 100)
                .placeholder(R.drawable.avatar).error(R.drawable.avatar)
                .into(img_avatar, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void loadMenu() {
        CategoryServices categoryServices = ApiUtils.getCategoryService();
        categoryServices.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Home.this, "Wait to Connect to Server.", Toast.LENGTH_SHORT).show();
                    categoryList = response.body();
                    addToAdapter(categoryList);
                }else {
                    Toast.makeText(Home.this, "Failure Connect to Server.", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(Home.this, MainForm.class);
                    startActivity(homeIntent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(Home.this, "Failure Connect to Server.", Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(Home.this, MainForm.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }

    public void addToAdapter(List<Category> list) {
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(this, list);
        recycler_list_categories.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(Home.this, Home.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_cart:
                Intent cartIntent = new Intent(Home.this, Cart_Order.class);
                startActivity(cartIntent);
                break;
            case R.id.nav_orders:
                break;
            case R.id.nav_logout:
                Intent mainIntent = new Intent(Home.this, MainForm.class);
                startActivity(mainIntent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
