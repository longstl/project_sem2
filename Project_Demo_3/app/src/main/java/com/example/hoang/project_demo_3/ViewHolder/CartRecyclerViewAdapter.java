package com.example.hoang.project_demo_3.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.entity.Order;
import com.example.hoang.project_demo_3.utilities.Interface.ItemClickListener;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder> {

    private List<Order> listData;
    private Context context;

    public CartRecyclerViewAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice()) * listData.get(position).getQuantity());
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txt_cart_name, txt_price;
        public ImageView img_cart_count;

        private ItemClickListener itemClickListener;

        public TextView getTxt_cart_name() {
            return txt_cart_name;
        }

        public void setTxt_cart_name(TextView txt_cart_name) {
            this.txt_cart_name = txt_cart_name;
        }

        public TextView getTxt_price() {
            return txt_price;
        }

        public void setTxt_price(TextView txt_price) {
            this.txt_price = txt_price;
        }

        public ImageView getImg_cart_count() {
            return img_cart_count;
        }

        public void setImg_cart_count(ImageView img_cart_count) {
            this.img_cart_count = img_cart_count;
        }

        public ItemClickListener getItemClickListener() {
            return itemClickListener;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public CartViewHolder(View itemView) {
            super(itemView);
            txt_cart_name = itemView.findViewById(R.id.cart_item_name);
            txt_price = itemView.findViewById(R.id.cart_item_price);
            img_cart_count = itemView.findViewById(R.id.cart_item_count);
        }

        @Override
        public void onClick(View view) {

        }
    }
}