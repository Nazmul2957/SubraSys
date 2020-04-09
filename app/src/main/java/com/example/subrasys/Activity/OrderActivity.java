package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.subrasys.R;

public class OrderActivity extends AppCompatActivity {

    Spinner customer_spinner,product_spinner;
    TextView customer_name_set,show_total_amount,date_show;
    Button final_order_list,date_pic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }
}
