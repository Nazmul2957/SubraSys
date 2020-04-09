package com.example.subrasys.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.subrasys.Activity.OrderActivity;
import com.example.subrasys.ModelClass.Product;
import com.example.subrasys.R;

import java.util.List;

public class Order_Page_List_adaptar extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Product> productslist;
    List<Product> finallist;
    int quantity = 1;



    public Order_Page_List_adaptar(Context context, List<Product> productslist) {
        this.context = context;
        this.productslist = productslist;
        finallist= productslist;
    }

    @Override
    public int getCount() {
        return productslist.size();
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
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.productlist_show_sample_design, parent, false);
        }

        TextView productName = convertView.findViewById(R.id.cart_product_name);
        TextView productPrice = convertView.findViewById(R.id.cart_product_price);
        final TextView productQuantity = convertView.findViewById(R.id.cart_product_quantity);
        ImageButton productAdd = convertView.findViewById(R.id.plus_product);
        ImageButton productMinus = convertView.findViewById(R.id.minus_product);
        ImageButton DeleteProduct = convertView.findViewById(R.id.delete_cart_product);

        productName.setText(productslist.get(position).getProduct_name());
        productPrice.setText(productslist.get(position).getProduct_price());
        productQuantity.setText(String.valueOf(1));

        productAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=finallist.get(position).getProduct_quantity();
                a++;
               finallist.get(position).setProduct_quantity(a);
                productQuantity.setText(String.valueOf(finallist.get(position).getProduct_quantity()));
            }
        });

        productMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=finallist.get(position).getProduct_quantity();
                a--;
                finallist.get(position).setProduct_quantity(a);
                productQuantity.setText(String.valueOf(finallist.get(position).getProduct_quantity()));
            }
        });

        DeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.total_amount=OrderActivity.total_amount-Integer.parseInt(productslist.get(position).getProduct_price());
                productslist.remove(position);
                OrderActivity.setTotalamount();
                notifyDataSetChanged();
            }
        });


        return convertView;
    }
    public List<Product> getfinaldata(){
        return finallist;
    }


}
