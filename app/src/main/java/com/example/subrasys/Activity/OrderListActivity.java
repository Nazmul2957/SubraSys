package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.subrasys.Adaptar.Order_list_show_custom_adaptar;
import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.OrderList;
import com.example.subrasys.R;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    ListView order_list_show;
    List<OrderList> orderLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        orderLists = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(OrderListActivity.this);
        orderLists = dbHelper.getOrderdetails();
        order_list_show = findViewById(R.id.order_list_show);

        Order_list_show_custom_adaptar adaptar = new Order_list_show_custom_adaptar(getApplicationContext(), orderLists);
        order_list_show.setAdapter(adaptar);

    }
}
