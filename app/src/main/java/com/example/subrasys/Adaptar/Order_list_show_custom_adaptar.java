package com.example.subrasys.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subrasys.ModelClass.OrderList;
import com.example.subrasys.R;

import java.util.List;

public class Order_list_show_custom_adaptar extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<OrderList> orderLists;

    public Order_list_show_custom_adaptar(Context context, List<OrderList> orderLists) {
        this.context = context;
        this.orderLists = orderLists;
    }

    @Override
    public int getCount() {
        return orderLists.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_sample_design, parent, false);
        }
        TextView orderno = convertView.findViewById(R.id.final_order_no);
        TextView total = convertView.findViewById(R.id.final_total_amount);
        TextView dateshow = convertView.findViewById(R.id.final_date);
        TextView customer_name = convertView.findViewById(R.id.final_customer_name);

        orderno.setText(String.valueOf(orderLists.get(position).getOrderno()));
        total.setText(String.valueOf(orderLists.get(position).getTotal_amount()));
        dateshow.setText(orderLists.get(position).getDate());
        customer_name.setText(orderLists.get(position).getCustomar_name());


        return convertView;
    }
}
