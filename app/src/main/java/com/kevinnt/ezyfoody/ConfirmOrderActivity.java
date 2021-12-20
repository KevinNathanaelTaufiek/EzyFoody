package com.kevinnt.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinnt.ezyfoody.datastore.DatabaseEntity;
import com.kevinnt.ezyfoody.models.Order;
import com.kevinnt.ezyfoody.models.Product;

public class ConfirmOrderActivity extends AppCompatActivity {

    private Button btnMyCart, btnOrderMore;
    private ImageView ivConfirmOrder;
    private TextView tvConfirmOrderName, tvConfirmOrderPrice;
    private EditText etConfirmOrderQuantitiy;

    private Product product;
    private DatabaseEntity db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        db = DatabaseEntity.getInstance();

        btnMyCart = findViewById(R.id.btnMyCart);
        btnOrderMore = findViewById(R.id.btnOrderMore);
        ivConfirmOrder = findViewById(R.id.ivConfirmOrder);
        tvConfirmOrderName = findViewById(R.id.tvConfirmOrderName);
        tvConfirmOrderPrice = findViewById(R.id.tvConfirmOrderPrice);
        etConfirmOrderQuantitiy = findViewById(R.id.etConfirmOrderQuantitiy);

        product = getIntent().getParcelableExtra("order");

        switch (product.getType()){
            case "drink":
                ivConfirmOrder.setImageResource(R.drawable.greentea);
                break;
            case "snack":
                ivConfirmOrder.setImageResource(R.drawable.dango);
                break;
            case "food":
                ivConfirmOrder.setImageResource(R.drawable.kareraisu);
                break;
            case "topup":
                ivConfirmOrder.setImageResource(R.drawable.coins);
                break;
        }

        tvConfirmOrderName.setText(product.getName());
        tvConfirmOrderPrice.setText("Rp. " + product.getPrice());

        btnOrderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.getOrders().add(new Order(product, Integer.parseInt(etConfirmOrderQuantitiy.getText().toString())));
                Intent intent = new Intent(ConfirmOrderActivity.this, ListMenuActivity.class);
                intent.putExtra("type",product.getType());
                startActivity(intent);


            }
        });

        btnMyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.getOrders().add(new Order(product, Integer.parseInt(etConfirmOrderQuantitiy.getText().toString())));
                Intent intent = new Intent(ConfirmOrderActivity.this, MyOrderActivity.class);
                startActivity(intent);

            }
        });

    }
}