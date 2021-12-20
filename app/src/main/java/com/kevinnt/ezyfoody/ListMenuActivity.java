package com.kevinnt.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kevinnt.ezyfoody.adapters.ListProductsAdapter;
import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Order;
import com.kevinnt.ezyfoody.models.Product;

import java.util.ArrayList;

public class ListMenuActivity extends AppCompatActivity {

    private Button btnMyCart;
    private RecyclerView rvListProducts;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        btnMyCart = findViewById(R.id.btnMyCart);
        rvListProducts = findViewById(R.id.rvListProducts);
        type = getIntent().getStringExtra("type");

        ListProductsAdapter adapter = new ListProductsAdapter(this, type);
        rvListProducts.setAdapter(adapter);
        rvListProducts.setLayoutManager(new GridLayoutManager(this, 2));

        btnMyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListMenuActivity.this, MyOrderActivity.class);
                startActivity(intent);

            }
        });


    }
}