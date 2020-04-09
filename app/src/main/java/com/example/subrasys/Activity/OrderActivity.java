package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.subrasys.R;

public class OrderActivity extends AppCompatActivity {

    Spinner customer_spinner, product_spinner;
    TextView customer_name_set, show_total_amount, date_show;
    Button final_order_list, date_pic;
    ListView product_select_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        customer_spinner=findViewById(R.id.customer_spinner);
        product_spinner=findViewById(R.id.product_spinner);
        customer_name_set=findViewById(R.id.customer_name_set);
        show_total_amount=findViewById(R.id.show_total_amount);
        date_show=findViewById(R.id.date_show);
        final_order_list=findViewById(R.id.final_order_list);
        date_pic=findViewById(R.id.date_pic);
        product_select_list=findViewById(R.id.product_select_list);

    }
}
