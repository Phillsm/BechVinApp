package com.example.phill.bechvinapp.DataSource;

/**
 * Created by Phill on 13-05-2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    // Database creation sql statement
    //private static final String CREATE_TABLE_PRODUCTS =
    //        "CREATE TABLE PRODUCTS(_id text PRIMARY KEY," +
    //                              "name text," +
    //                              "price real);";
    private static final String CREATE_TABLE_ORDERS =
            "CREATE TABLE ORDERS(_id text PRIMARY KEY,"+
                                "costumer text,"+
                                "att text,"+
                                "date text,"+
                                "status text);";
    private static final String CREATE_TABLE_ORDERPRODUCTS =
            "CREATE TABLE ORDERPRODUCTS(orderid text,"+
                                       "productid text," +
                                       "productname text,"+
                                       "productprice real,"+
                                       "productamount integer,"+
                                       "FOREIGN KEY (orderid) REFERENCES orders(_id),"+
                                       "PRIMARY KEY (orderid, productid));";

    private static final String DATABASE_NAME = "wine.db";

    private static final int DATABASE_VERSION = 1;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_ORDERS);
        database.execSQL(CREATE_TABLE_ORDERPRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: READ UP ON ONUPGRADE

        onCreate(db);
    }

}