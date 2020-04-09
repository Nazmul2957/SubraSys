package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.subrasys.Adaptar.Product_Adaptar;
import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.Product;
import com.example.subrasys.R;

public class ProductActivity extends AppCompatActivity {

    EditText product_name, product_price;
    Button product_save;
    ListView product_list;
    DbHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        product_list = findViewById(R.id.produst_list);
        product_save = findViewById(R.id.product_save);

        show_product();

        product_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_product();
                show_product();

            }
        });
    }

    public void add_product() {

        String productname = product_name.getText().toString();
        String productprice = product_price.getText().toString();

        if (productname.isEmpty() && productprice.isEmpty()) {
            Toast.makeText(this, "Enter Product", Toast.LENGTH_SHORT).show();
        } else {
            Product product = new Product(productname, productprice);
            database = new DbHelper(getApplicationContext());
            database.addProduct(product);
            product_name.setText("");
            product_price.setText("");
        }
    }

    public void show_product() {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        Product_Adaptar adaptar = new Product_Adaptar(dbHelper.get_product(), ProductActivity.this);
        product_list.setAdapter(adaptar);

    }

}
