package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.subrasys.R;

public class MainActivity extends AppCompatActivity {

    Button product_add,customer_add,order_page,order_list_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_add=findViewById(R.id.product_add);
        customer_add=findViewById(R.id.customer_add);
        order_page=findViewById(R.id.order_page);
        order_list_page=findViewById(R.id.order_list_page);

        product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ProductActivity.class);
                startActivity(intent);
            }
        });

        customer_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });
       order_page.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(MainActivity.this,OrderActivity.class);
               startActivity(intent);
           }
       });
        order_list_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OrderListActivity.class));
            }
        });
    }
}
