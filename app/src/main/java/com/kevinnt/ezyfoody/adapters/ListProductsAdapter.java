package com.kevinnt.ezyfoody.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kevinnt.ezyfoody.ConfirmOrderActivity;
import com.kevinnt.ezyfoody.R;
import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Product;

import java.util.ArrayList;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ListProductsViewHolder> {

    private ArrayList<Product> allProduct;
    private Context context;
    private DatabaseEntity db;
    private String type;

    public ListProductsAdapter(Context context, String type) {
        this.context = context;
        db = DatabaseEntity.getInstance();
        this.type = type;
        switch (type){
            case "drink":
                allProduct = db.getDrinks();
                break;
            case "snack":
                allProduct = db.getSnacks();
                break;
            case "food":
                allProduct = db.getFoods();
                break;
            case "topup":
                allProduct = db.getTopups();
                break;
        }
    }

    @NonNull
    @Override
    public ListProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductsViewHolder holder, int position) {
        switch (this.type){
            case "drink":
                holder.ivListProduct.setImageResource(R.drawable.greentea);
                break;
            case "snack":
                holder.ivListProduct.setImageResource(R.drawable.dango);
                break;
            case "food":
                holder.ivListProduct.setImageResource(R.drawable.kareraisu);
                break;
            case "topup":
                holder.ivListProduct.setImageResource(R.drawable.coins);
                break;
        }

        holder.tvListProductName.setText(allProduct.get(position).getName());
        holder.tvListProductPrice.setText("Rp. " + allProduct.get(position).getPrice());

        holder.llListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConfirmOrderActivity.class);
                intent.putExtra("order", (Parcelable) allProduct.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProduct.size();
    }

    public void setAllProduct(ArrayList<Product> allProduct) {
        this.allProduct = allProduct;
    }

    public class ListProductsViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout llListProduct;
        private ImageView ivListProduct;
        private TextView tvListProductName, tvListProductPrice;

        public ListProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            llListProduct = itemView.findViewById(R.id.llListProduct);
            ivListProduct = itemView.findViewById(R.id.ivListProduct);
            tvListProductName = itemView.findViewById(R.id.tvListProductName);
            tvListProductPrice = itemView.findViewById(R.id.tvListProductPrice);
        }
    }
}
