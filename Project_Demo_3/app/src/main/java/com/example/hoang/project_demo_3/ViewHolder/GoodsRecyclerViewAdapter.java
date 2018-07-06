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
import com.example.hoang.project_demo_3.controller.GoodsDetails;
import com.example.hoang.project_demo_3.entity.Category;
import com.example.hoang.project_demo_3.entity.MyProduct;
import com.example.hoang.project_demo_3.utilities.Interface.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GoodsRecyclerViewAdapter extends RecyclerView.Adapter<GoodsRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<MyProduct> mData;

    public GoodsRecyclerViewAdapter() {
    }

    public GoodsRecyclerViewAdapter(Context mContext, List<MyProduct> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<MyProduct> getmData() {
        return mData;
    }

    public void setmData(List<MyProduct> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.goods_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txtMenuName.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getThumbnail()).fit()
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
                Intent goodDetailIntent = new Intent(mContext, GoodsDetails.class);
                goodDetailIntent.putExtra("id", mData.get(position).getId());
                mContext.startActivity(goodDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtMenuName;
        public ImageView imageView;


        private ItemClickListener itemClickListener;

        MyViewHolder(View itemView) {
            super(itemView);

            txtMenuName = (TextView) itemView.findViewById(R.id.goods_name);
            imageView = (ImageView) itemView.findViewById(R.id.goods_image);

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
