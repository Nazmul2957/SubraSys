package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subrasys.Adaptar.Order_Page_List_adaptar;
import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.Customer;
import com.example.subrasys.ModelClass.Product;
import com.example.subrasys.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderActivity extends AppCompatActivity {


    Spinner customer_spinner, product_spinner;
    static TextView customer_name_set, show_total_amount, date_show;
    DatePicker date_picker;
    Button final_order_list, date_pic;
    ListView product_select_list;
    List<Product> selected_products;
    List<Product> productslist;
    int toggole=0;
    Order_Page_List_adaptar pro_adaptars;
    public static int total_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        customer_spinner = findViewById(R.id.customer_spinner);
        product_spinner = findViewById(R.id.product_spinner);
        customer_name_set = findViewById(R.id.customer_name_set);
        show_total_amount = findViewById(R.id.show_total_amount);
        date_show = findViewById(R.id.date_show);
        final_order_list = findViewById(R.id.final_order_list);
        date_pic = findViewById(R.id.date_pic);
        date_picker=findViewById(R.id.date_picker);
        product_select_list = findViewById(R.id.product_select_list);
        selected_products = new ArrayList<Product>();
        productslist = new ArrayList<Product>();
         //calender---------------

        date_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggole==0){
                    date_picker.setVisibility(View.VISIBLE);
                    toggole=1;
                }else{
                    date_picker.setVisibility(View.GONE);
                    toggole=0;
                }

                Calendar calendar =Calendar.getInstance();
                date_picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date_show.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                    }
                });
            }
        });


        ///spinner for customer
        final String[] a = allCustomer();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, a);
        customer_spinner.setAdapter(adapter);
        pro_adaptars = new Order_Page_List_adaptar(OrderActivity.this, selected_products);

        product_select_list.setAdapter(pro_adaptars);
        product_select_list.deferNotifyDataSetChanged();
        customer_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customer_name_set.setText(a[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ///////Spinner for product
        String[] pro = allproduct();
        ArrayAdapter<String> pro_adaptar = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pro);
        product_spinner.setAdapter(pro_adaptar);

        product_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*selected_products.clear();*/
                total_amount = total_amount + Integer.parseInt(productslist.get(position).getProduct_price());
                selected_products = pro_adaptars.getfinaldata();
                selected_products.add(productslist.get(position));
                pro_adaptars.notifyDataSetChanged();
                show_total_amount.setText(String.valueOf(total_amount));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////product add list


    }

    public String[] allCustomer() {

        List<Customer> customers = new ArrayList<>();
        DbHelper db = new DbHelper(getApplicationContext());
        customers = db.getCustomer();
        String[] customer = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            customer[i] = customers.get(i).getName();
        }
        return customer;
    }


    ///////Spinner for product


    public String[] allproduct() {
        List<Product> products = new ArrayList<>();
        DbHelper db = new DbHelper(getApplicationContext());
        products = db.get_product();
        productslist = products;
        String[] product = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            product[i] = products.get(i).getProduct_name();

        }
        return product;

    }

    public static void setTotalamount() {
        show_total_amount.setText(String.valueOf(total_amount));


    }
}
