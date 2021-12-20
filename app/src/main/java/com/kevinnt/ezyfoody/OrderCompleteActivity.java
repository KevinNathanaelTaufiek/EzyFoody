package com.kevinnt.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kevinnt.ezyfoody.adapters.ListOrdersAdapter;
import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Order;

import java.util.ArrayList;

public class OrderCompleteActivity extends AppCompatActivity {

    private Button btnMainMenu;
    private TextView tvTotalSummary;
    private RecyclerView rvListSummary;

    private ArrayList<Order> summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

        btnMainMenu = findViewById(R.id.btnMainMenu);
        tvTotalSummary = findViewById(R.id.tvTotalSummary);
        rvListSummary = findViewById(R.id.rvListSummary);

        summary = getIntent().getParcelableArrayListExtra("summary");

        setTotal();

        ListOrdersAdapter adapter = new ListOrdersAdapter(this,false);
        rvListSummary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter.setOrders(summary);
        rvListSummary.setAdapter(adapter);

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainMenu();
            }
        });

    }

    @Override
    public void onBackPressed() {
        goMainMenu();
    }

    private void goMainMenu(){
        Intent intent = new Intent(OrderCompleteActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void setTotal(){
        int total = getTotal();
        tvTotalSummary.setText("Total : Rp. " + total);
    }

    private int getTotal(){
        int total = 0;
        ArrayList<Order> orders = summary;
        for (Order order: orders) {
            total += order.getQuantity() * (order.getProduct().getPrice());
        }
        return total;
    }
}