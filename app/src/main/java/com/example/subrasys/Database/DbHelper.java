package com.example.subrasys.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.subrasys.ModelClass.Customer;
import com.example.subrasys.ModelClass.Product;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + Constant.CUSTOMER_TABLE_NAME + "("
                + Constant.CUSTOMER_id + " INTEGER PRIMARY KEY," + Constant.CUSTOMER_NAME + " TEXT,"
                + Constant.CUSTOMER_PHN + " TEXT " + ")";

        String CREATE_PRODUCT_TABLE = " CREATE TABLE " + Constant.PRODUCT_TABLE_NAME + "("
                + Constant.PRODUCT_id + " INTEGER PRIMARY KEY," + Constant.PRODUCT_NAME + " TEXT,"
                + Constant.PRODUCT_PRICE + " TEXT " + ")";

        String CREATE_ORDER_TABLE = "CREATE TABLE " + Constant.ORDER_TABLE_NAME + "("
                + Constant.ORDER_NO + " INTEGER PRIMARY KEY," + Constant.ORDER_DATE + " TEXT,"
                + Constant.ORDER_CUSTOMER_ID + " TEXT " + ")";


        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.PRODUCT_NAME, product.getProduct_name());
        values.put(Constant.PRODUCT_PRICE, product.getProduct_price());
//        Log.e("data_insert",product.getProduct_name());

        if (db.insert(Constant.PRODUCT_TABLE_NAME, null, values) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    public boolean addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.CUSTOMER_NAME, customer.getName());
        contentValues.put(Constant.CUSTOMER_PHN, customer.getPhn());

        if (db.insert(Constant.CUSTOMER_TABLE_NAME, null, contentValues) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

//    public boolean addordder() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Constant.ORDER_DATE, "nasim");
//        contentValues.put(Constant.ORDER_CUSTOMER_ID, "01629668325");
//
//        if (db.insert(Constant.ORDER_TABLE_NAME, null, contentValues) > 0) {
//            db.close();
//            return true;
//
//        } else {
//
//            db.close();
//            return false;
//        }
//
//    }

    public List<Product> get_product() {

        Cursor cursor = null;
        List<Product> product = new ArrayList<>();
        String lsit_query = "SELECT * FROM " + Constant.PRODUCT_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(lsit_query, null);
        if (cursor != null)
            if (cursor.moveToFirst()) {
                do {
                    int productid = cursor.getInt(cursor.getColumnIndex(Constant.PRODUCT_id));
                    String name = cursor.getString(cursor.getColumnIndex(Constant.PRODUCT_NAME));
                    String price = cursor.getString(cursor.getColumnIndex(Constant.PRODUCT_PRICE));
                    Product product2 = new Product(productid, name, price);
                    product.add(product2);
                }
                while (cursor.moveToNext());
                cursor.close();
                db.close();
                return product;
            }
        db.close();
        return product;
    }

    public List<Customer> getCustomer() {
        Log.e("customer_cursor", "insert");
        Cursor cursorCustomer = null;
        List<Customer> customer = new ArrayList<>();
        String customer_query = "SELECT * FROM " + Constant.CUSTOMER_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursorCustomer = db.rawQuery(customer_query, null);

        if (cursorCustomer != null) {
            if (cursorCustomer.moveToFirst()) {
                do {
                    int nameid = cursorCustomer.getInt(cursorCustomer.getColumnIndex(Constant.CUSTOMER_id));
                    String nametwo = cursorCustomer.getString(cursorCustomer.getColumnIndex(Constant.CUSTOMER_NAME));
                    String phn = cursorCustomer.getString(cursorCustomer.getColumnIndex(Constant.CUSTOMER_PHN));
                    Customer customer2 = new Customer(nameid, nametwo, phn);
                    customer.add(customer2);

                } while (cursorCustomer.moveToNext());
                cursorCustomer.close();
                db.close();
                return customer;
            }
        }
        db.close();
        return customer;

    }

    public boolean deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteCustomer = "DELETE FROM " + Constant.CUSTOMER_TABLE_NAME + " WHERE " + Constant.CUSTOMER_id + "=" + String.valueOf(id);
        Cursor cursor = db.rawQuery(deleteCustomer, null);
//        Log.e("delete", String.valueOf(cursor.getCount()));
        db.close();
        return true;
    }

    public boolean deleteproduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteproduct = " DELETE FROM " + Constant.PRODUCT_TABLE_NAME + " WHERE " + Constant.PRODUCT_id + "=" + String.valueOf(id);
        Cursor cursor = db.rawQuery(deleteproduct, null);
        Log.e("delete", String.valueOf(cursor.getCount()));
        db.close();
        return true;

    }

    public boolean updateproduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constant.PRODUCT_NAME,product.getProduct_name()); //These Fields should be your String values of actual column names
        cv.put(Constant.PRODUCT_PRICE,product.getProduct_price()); //These Fields should be your String values of actual column names
        db.update(Constant.PRODUCT_TABLE_NAME,cv,Constant.PRODUCT_id+"="+product.getProduct_id(),null);
        db.close();
        return true;


    }


    public  boolean updatecustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constant.CUSTOMER_NAME,customer.getName());
        cv.put(Constant.CUSTOMER_PHN,customer.getPhn());
        db.update(Constant.CUSTOMER_TABLE_NAME,cv,Constant.CUSTOMER_id+"="+customer.getId(),null);
        db.close();
        return true;


    }

}
