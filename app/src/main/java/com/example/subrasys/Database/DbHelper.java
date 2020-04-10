package com.example.subrasys.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.subrasys.ModelClass.Customer;
import com.example.subrasys.ModelClass.OrderList;
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

        String CREATE_ORDER_LIST_TABLE = "CREATE TABLE " + Constant.ORDER_DETAILS_TABLE_NAME + "("
                + Constant.ORDER_DETAILS_id + " INTEGER PRIMARY KEY," + Constant.ORDER_DETAILS_ORDER_NO
                + " INTEGER ," + Constant.ORDER_DETAILS_PRODUCT_ID + " INTEGER," + Constant.ORDER_DETAILS_QUANTITY + " TEXT,"
                + Constant.ORDER_DETAILS_TOTAL_AMOUNT + " INTEGER " + ")";


        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ORDER_LIST_TABLE);

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
        cv.put(Constant.PRODUCT_NAME, product.getProduct_name()); //These Fields should be your String values of actual column names
        cv.put(Constant.PRODUCT_PRICE, product.getProduct_price()); //These Fields should be your String values of actual column names
        db.update(Constant.PRODUCT_TABLE_NAME, cv, Constant.PRODUCT_id + "=" + product.getProduct_id(), null);
        db.close();
        return true;


    }


    public boolean updatecustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constant.CUSTOMER_NAME, customer.getName());
        cv.put(Constant.CUSTOMER_PHN, customer.getPhn());
        db.update(Constant.CUSTOMER_TABLE_NAME, cv, Constant.CUSTOMER_id + "=" + customer.getId(), null);
        db.close();
        return true;
    }

    //order fucnctions------------------
    public long addOrder(String customer_id, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.ORDER_CUSTOMER_ID, customer_id);
        contentValues.put(Constant.ORDER_DATE, date);
        long id = db.insert(Constant.ORDER_TABLE_NAME, null, contentValues);


        db.close();
        return id;

    }

    public long addOrderDetails(long order_no, String date, int quantity, int total_amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.ORDER_DETAILS_ORDER_NO, order_no);
        contentValues.put(Constant.ORDER_DETAILS_PRODUCT_ID, date);
        contentValues.put(Constant.ORDER_DETAILS_QUANTITY, quantity);
        contentValues.put(Constant.ORDER_DETAILS_TOTAL_AMOUNT, total_amount);
        long id = db.insert(Constant.ORDER_DETAILS_TABLE_NAME, null, contentValues);


        db.close();
        return id;

    }


    ///order details show final page
    public List<OrderList> getOrderdetails() {

        int order_no = 0;
        Cursor cursorCustomer = null;
        Cursor cursorOrder = null;
        Cursor customcurosr = null;
        List<OrderList> orderLists = new ArrayList<>();
        String customer_query = "SELECT * FROM " + Constant.ORDER_DETAILS_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursorCustomer = db.rawQuery(customer_query, null);

        if (cursorCustomer != null) {
            if (cursorCustomer.moveToFirst()) {
                do {
                    order_no = cursorCustomer.getInt(cursorCustomer.getColumnIndex(Constant.ORDER_DETAILS_id));
                    int total_amount = cursorCustomer.getInt(cursorCustomer.getColumnIndex(Constant.ORDER_DETAILS_TOTAL_AMOUNT));
                    order_no = cursorCustomer.getInt(cursorCustomer.getColumnIndex(Constant.ORDER_DETAILS_ORDER_NO));


                    String order_query = " SELECT * FROM " + Constant.ORDER_TABLE_NAME + " WHERE " + Constant.ORDER_NO + " = " + order_no;
                    cursorOrder = db.rawQuery(order_query, null);

                    cursorOrder.moveToFirst();
                    String date = cursorOrder.getString(cursorOrder.getColumnIndex(Constant.ORDER_DATE));
                    String customar_id = cursorOrder.getString(cursorOrder.getColumnIndex(Constant.ORDER_CUSTOMER_ID));


                    String customer_name = " SELECT * FROM " + Constant.CUSTOMER_TABLE_NAME + " WHERE " + Constant.CUSTOMER_id + " = " + customar_id;
                    customcurosr = db.rawQuery(customer_name, null);
                    customcurosr.moveToFirst();
                    String customer_namer = customcurosr.getString(customcurosr.getColumnIndex(Constant.CUSTOMER_NAME));


                    OrderList orderList = new OrderList(order_no, total_amount, customer_namer, date);
                    orderLists.add(orderList);


                } while (cursorCustomer.moveToNext());
                cursorCustomer.close();
                db.close();
                return orderLists;
            }
        }


        db.close();
        return orderLists;

    }

}
