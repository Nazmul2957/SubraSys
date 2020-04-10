package com.example.subrasys.Adaptar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.subrasys.Activity.CustomerActivity;
import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.Customer;
import com.example.subrasys.R;

import java.util.List;

public class Customer_Adaptar extends BaseAdapter {

    List<Customer> customer;
    Context context;
    Intent intent;
    LayoutInflater layoutInflater;

    public Customer_Adaptar(List<Customer> customer, Context context, Intent intent) {
        this.customer = customer;
        this.context = context;
        this.intent = intent;
    }

    @Override
    public int getCount() {
        return customer.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.customer_sample_design, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.sample_customer_name);
        TextView textview2 = convertView.findViewById(R.id.sample_customer_phn);
        ImageButton delete = convertView.findViewById(R.id.delete_customer);
        ImageButton update = convertView.findViewById(R.id.edit_name);

        textView.setText(customer.get(position).getName());
        textview2.setText(customer.get(position).getPhn());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerActivity.cus(customer.get(position));
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(context);
                dbHelper.deleteCustomer(customer.get(position).getId());
                customer.remove(position);
                notifyDataSetChanged();

            }
        });


        return convertView;
    }
}
