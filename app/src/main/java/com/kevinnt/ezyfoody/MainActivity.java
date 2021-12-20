package com.kevinnt.ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnMyCart;
    private LinearLayout llDrinks, llSnacks, llFoods, llTopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyCart = findViewById(R.id.btnMyCart);
        llDrinks = findViewById(R.id.llDrinks);
        llSnacks = findViewById(R.id.llSnacks);
        llFoods = findViewById(R.id.llFoods);
        llTopUp = findViewById(R.id.llTopUp);

        llDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListMenu("drink");
            }
        });
        llSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListMenu("snack");
            }
        });
        llFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListMenu("food");
            }
        });
        llTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goListMenu("topup");
            }
        });

        btnMyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyOrderActivity.class);
                startActivity(intent);

            }
        });

    }

    private void goListMenu(String type){
        Intent intent = new Intent(this,ListMenuActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }



}