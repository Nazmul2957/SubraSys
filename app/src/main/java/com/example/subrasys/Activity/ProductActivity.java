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

    static EditText product_name, product_price;
    static Button product_save,product_update;
    ListView product_list;
    static int pid_update=0;
    DbHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        product_list = findViewById(R.id.produst_list);
        product_save = findViewById(R.id.product_save);
        product_update=findViewById(R.id.product_update);
        show_product();

        product_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_product();
                show_product();

            }
        });
        product_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                show_product();
                product_save.setEnabled(true);
                product_update.setEnabled(false);
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
    public void update(){
        String productname = product_name.getText().toString();
        String productprice = product_price.getText().toString();

        if (productname.isEmpty() && productprice.isEmpty()) {
            Toast.makeText(this, "Enter Product", Toast.LENGTH_SHORT).show();
        } else {
            Product product = new Product(pid_update,productname, productprice);
            database = new DbHelper(getApplicationContext());
            database.updateproduct(product);
            product_name.setText("");
            product_price.setText("");
        }
    }
    public static void fun(Product product){
        product_name.setText(product.getProduct_name());
        product_price.setText(product.getProduct_price());
        pid_update=product.getProduct_id();
        product_update.setEnabled(true);
        product_save.setEnabled(false);


    }

}
