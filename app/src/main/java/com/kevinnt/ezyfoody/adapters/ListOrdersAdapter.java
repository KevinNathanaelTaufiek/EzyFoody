package com.kevinnt.ezyfoody.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kevinnt.ezyfoody.MyOrderActivity;
import com.kevinnt.ezyfoody.R;
import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Order;

import java.util.ArrayList;

public class ListOrdersAdapter extends RecyclerView.Adapter<ListOrdersAdapter.ListOrdersViewHolder> {

    private ArrayList<Order> orders;
    private Context context;
    private DatabaseEntity db;

    private int deleteVisibility;

    public ListOrdersAdapter(Context context, Boolean canDelete) {
        this.db = DatabaseEntity.getInstance();
        this.orders = db.getOrders();
        this.context = context;

        if(canDelete){
            deleteVisibility = View.VISIBLE;
        }
        else {
            deleteVisibility = View.INVISIBLE;
        }
    }

    @NonNull
    @Override
    public ListOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_my_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrdersViewHolder holder, int position) {
        Order order = orders.get(position);
        switch (order.getProduct().getType()){
            case "drink":
                holder.ivOrder.setImageResource(R.drawable.greentea);
                break;
            case "snack":
                holder.ivOrder.setImageResource(R.drawable.dango);
                break;
            case "food":
                holder.ivOrder.setImageResource(R.drawable.kareraisu);
                break;
            case "topup":
                holder.ivOrder.setImageResource(R.drawable.coins);
                break;
        }

        holder.tvOrderName.setText(order.getProduct().getName());
        holder.tvOrderPrice.setText(order.getQuantity() + " x Rp. " + order.getProduct().getPrice());

        holder.btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Deleted" + position, Toast.LENGTH_SHORT).show();
                orders.remove(position);
                ((MyOrderActivity)context).setTotal();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public class ListOrdersViewHolder extends RecyclerView.ViewHolder{

        private TextView tvOrderName, tvOrderPrice;
        private ImageView ivOrder;
        private Button btnDeleteOrder;

        public ListOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
            ivOrder = itemView.findViewById(R.id.ivOrder);
            btnDeleteOrder = itemView.findViewById(R.id.btnDeleteOrder);

            btnDeleteOrder.setVisibility(deleteVisibility);
        }
    }
}
