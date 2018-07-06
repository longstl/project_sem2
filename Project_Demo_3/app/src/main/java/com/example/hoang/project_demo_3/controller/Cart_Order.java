package com.example.hoang.project_demo_3.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.ViewHolder.CartRecyclerViewAdapter;
import com.example.hoang.project_demo_3.common.Common;
import com.example.hoang.project_demo_3.database.DBModel;
import com.example.hoang.project_demo_3.entity.Order;
import com.example.hoang.project_demo_3.entity.Request;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Cart_Order extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView txtTotalPrice;
    Button btnPlace;
    List<Order> cartList = new ArrayList<>();
    CartRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_order);
        //Init
        recyclerView = findViewById(R.id.listCart);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total_Price);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
        loadListGoods();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart_Order.this);
        alertDialog.setTitle("One more step");
        alertDialog.setMessage("Enter your address");

        final EditText edtAddress = new EditText(Cart_Order.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); // add edit text to alert dialog.
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Request request = new Request(
                        Common.currentAccount.getPhone(),
                        Common.currentAccount.getFullname(),
                        edtAddress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cartList
                );

                // Submit to API

                //Delete cart
                new DBModel(getBaseContext()).clearCart();

                Toast.makeText(Cart_Order.this,"Thank you, Order Place", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadListGoods() {
        cartList = new DBModel(this).getCart();
        adapter = new CartRecyclerViewAdapter(cartList, this);
        recyclerView.setAdapter(adapter);

        //calculate total price
        int total = 0;

        for (Order order : cartList) {
            total += (Integer.parseInt(order.getPrice()) * order.getQuantity());
            Locale locale = new Locale("en", "US");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
            txtTotalPrice.setText(fmt.format(total));
        }
    }
}
