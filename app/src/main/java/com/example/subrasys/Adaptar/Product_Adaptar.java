package com.example.subrasys.Adaptar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subrasys.Database.DbHelper;
import com.example.subrasys.ModelClass.Product;
import com.example.subrasys.R;

import java.util.List;

public class Product_Adaptar extends BaseAdapter {

    List<Product> products;
    Context context;
    LayoutInflater layoutInflater;

    public Product_Adaptar(List<Product> products, Context context) {

        this.context = context;
        this.products = products;

    }

    @Override
    public int getCount() {
        return products.size();
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
            convertView = layoutInflater.inflate(R.layout.product_sample_design, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.sample_product_name);
        TextView textview2 = convertView.findViewById(R.id.sample_product_price);
        ImageButton delete = convertView.findViewById(R.id.delete_product);
        ImageButton update = convertView.findViewById(R.id.edit_product_name);


        textView.setText(products.get(position).getProduct_name());
        textview2.setText(products.get(position).getProduct_price());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DbHelper dbHelper = new DbHelper(context);
                dbHelper.updateproduct(products.get(position));*/
                EditText editText = (EditText) context.findViewById(R.id.product_name);
                Toast.makeText(context, editText.getText(), Toast.LENGTH_LONG).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper=new DbHelper(context);
                dbHelper.deleteproduct(products.get(position).getProduct_id());
                products.remove(position);
                notifyDataSetChanged();

            }
        });

        return convertView;
    }
}
