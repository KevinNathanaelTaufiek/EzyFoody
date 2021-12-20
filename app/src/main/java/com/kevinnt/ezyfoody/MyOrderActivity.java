package com.kevinnt.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinnt.ezyfoody.adapters.ListOrdersAdapter;
import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Order;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {

    private TextView tvTotalOrder;
    private RecyclerView rvListOrders;
    private Button btnPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        rvListOrders = findViewById(R.id.rvListOrders);
        btnPayNow = findViewById(R.id.btnPayNow);

        setTotal();

        ListOrdersAdapter adapter = new ListOrdersAdapter(this,true);

        rvListOrders.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvListOrders.setAdapter(adapter);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTotal() == 0){
                    Toast.makeText(MyOrderActivity.this, "Please Order something first!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ArrayList<Order> summary = new ArrayList<>(DatabaseEntity.getInstance().getOrders());
                    DatabaseEntity.getInstance().getOrders().clear();

                    Intent intent = new Intent(MyOrderActivity.this, OrderCompleteActivity.class);
                    intent.putExtra("summary", summary);
                    startActivity(intent);
                }
            }
        });
    }

    public void setTotal(){
        int total = getTotal();
        tvTotalOrder.setText("Total : Rp. " + total);
    }

    private int getTotal(){
        int total = 0;
        ArrayList<Order> orders = DatabaseEntity.getInstance().getOrders();
        for (Order order: orders) {
            total += order.getQuantity() * (order.getProduct().getPrice());
        }
        return total;
    }

}