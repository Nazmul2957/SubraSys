package com.example.subrasys.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.subrasys.Adaptar.Customer_Adaptar;
import com.example.subrasys.Adaptar.Product_Adaptar;
import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.Customer;
import com.example.subrasys.R;

public class CustomerActivity extends AppCompatActivity {


    static EditText customer_name, customer_phn_no;
    static Button customer_save,customer_update;
    ListView customer_list;
    DbHelper database;
    static int cid_update=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        customer_name = findViewById(R.id.customer_name);
        customer_phn_no = findViewById(R.id.customer_phn_no);
        customer_save = findViewById(R.id.customer_save);
        customer_list = findViewById(R.id.customer_list);
        customer_update=findViewById(R.id.customer_update);

        show_customer();

        customer_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                show_customer();
                customer_save.setEnabled(true);
                customer_update.setEnabled(false);
            }
        });

        customer_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_customer();
                show_customer();
            }
        });
    }

    public void add_customer() {

        String customername = customer_name.getText().toString();
        String customerPhn = customer_phn_no.getText().toString();

        if (customername.isEmpty() && customerPhn.isEmpty()) {
            Toast.makeText(this, "Input Customer", Toast.LENGTH_SHORT).show();
        } else {
            Customer customer = new Customer(customername, customerPhn);
            database = new DbHelper(getApplicationContext());
            database.addCustomer(customer);
            customer_name.setText("");
            customer_phn_no.setText("");
        }
    }

    public void show_customer() {
        DbHelper database = new DbHelper(getApplicationContext());
        Customer_Adaptar customaradaptar = new Customer_Adaptar(database.getCustomer(),getApplicationContext(),getIntent());
        customer_list.setAdapter(customaradaptar);

    }
    public void update(){
        String customername = customer_name.getText().toString();
        String customerphn = customer_phn_no.getText().toString();

        if (customername.isEmpty() && customerphn.isEmpty()) {
            Toast.makeText(this, "Enter Product", Toast.LENGTH_SHORT).show();
        } else {
            Customer customer = new Customer(cid_update,customername, customerphn);
            database = new DbHelper(getApplicationContext());
            database.updatecustomer(customer);
            customer_name.setText("");
            customer_phn_no.setText("");
        }
    }

    ///call from customer adapter to set edittext data for update.
    public static void cus(Customer customer){
        customer_name.setText(customer.getName());
        customer_phn_no.setText(customer.getPhn());
        cid_update=customer.getId();
        customer_update.setEnabled(true);
        customer_save.setEnabled(false);


    }

}
