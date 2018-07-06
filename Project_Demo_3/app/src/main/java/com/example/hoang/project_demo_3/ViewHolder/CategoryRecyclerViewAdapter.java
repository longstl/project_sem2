package com.example.hoang.project_demo_3.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.project_demo_3.R;
import com.example.hoang.project_demo_3.controller.Goods_Activity;
import com.example.hoang.project_demo_3.entity.Category;
import com.example.hoang.project_demo_3.entity.MyProduct;
import com.example.hoang.project_demo_3.utilities.Interface.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Category> mData;
    private List<MyProduct> listProduct;

    public CategoryRecyclerViewAdapter() {
    }

    public CategoryRecyclerViewAdapter(Context mContext, List<Category> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Category> getmData() {
        return mData;
    }

    public void setmData(List<Category> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.menu_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txtMenuName.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getImage_url()).fit()
                .placeholder(R.drawable.avatar).error(R.drawable.avatar)
                .into(holder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        //Set click listener
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Goods_Activity.class);
                intent.putExtra("id", mData.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtMenuName;
        public ImageView imageView;


        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
            imageView = (ImageView) itemView.findViewById(R.id.menu_image);

            itemView.setOnClickListener(this);
        }

        public ItemClickListener getItemClickListener() {
            return itemClickListener;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(imageView, getAdapterPosition(), false);
        }
    }
}
