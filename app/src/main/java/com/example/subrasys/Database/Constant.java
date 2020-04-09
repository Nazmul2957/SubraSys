package com.example.subrasys.Database;

public class Constant {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "production";

    //product table-----------
    public static final String PRODUCT_TABLE_NAME = "product";
    public static final String PRODUCT_id = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";

    //Customer Table
    public static final String CUSTOMER_TABLE_NAME = "customer";
    public static final String CUSTOMER_id = "id";
    public static final String CUSTOMER_NAME = "name";
    public static final String CUSTOMER_PHN = "phone";

    //ORDER TABLE
    public static final String ORDER_TABLE_NAME = "orderc";
    public static final String ORDER_NO= "no";
    public static final String ORDER_DATE = "date";
    public static final String ORDER_CUSTOMER_ID = "serial";


    ///ORDER DETAILS

    public static final String ORDER_DETAILS_TABLE_NAME = "orderc_details";
    public static final String ORDER_DETAILS_id = "id";
    public static final String ORDER_DETAILS_ORDER_NO = "order_no";
    public static final String ORDER_DETAILS_PRODUCT_ID = "product_id";
    public static final String ORDER_DETAILS_QUANTITY = "order_quantity";
    public static final String ORDER_DETAILS_TOTAL_AMOUNT = "total_amount";



}
