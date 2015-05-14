package com.example.phill.bechvinapp.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.phill.bechvinapp.Model.Order;
import com.example.phill.bechvinapp.Model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Phill on 13-05-2015.
 */
public class OrderDataSource {

    private SQLiteDatabase database;
    private SQLHelper dbHelper;
    private String[] allColumns = { "_id","costumer","att","date","status"};

    public OrderDataSource(Context context) {
        dbHelper = new SQLHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();
    }

    public void beginTransaction(){
        database.beginTransaction();
    }
    public void endTransaction(){
        database.endTransaction();
    }

    public void saveOrder(Order order){
            String ID = UUID.randomUUID().toString();
            ContentValues values = new ContentValues();
            values.put("_id", ID);
            values.put("costumer", order.getCostumer());
            values.put("att", order.getAtt());
            values.put("status", order.getStatus().toString());
            values.put("date", String.valueOf(order.getDate().getTime()));

            saveProducts(ID, order.getProducts());

            database.insert("ORDERS","att",values);
            database.setTransactionSuccessful();
    }

    private void saveProducts(String orderid, HashMap<Product,Integer> products) throws SQLException {
        ArrayList<ContentValues> values = new ArrayList<>();
        for(Product p : products.keySet()){
            ContentValues value = new ContentValues();
            value.put("orderid",orderid);
            value.put("productid",p.getId());
            value.put("productname",p.getName());
            value.put("productprice",p.getPrice());
            value.put("productamount",products.get(p));
            values.add(value);
        }

        for(ContentValues cv : values){
            database.insert("ORDERPRODUCTS","productamount",cv);
        }

    }

    public List<Order> getAllOrders(){

        String[] projection = {
                "_id",
                "costumer",
                "att",
                "status",
                "date",
        };
        String sortOrder = "date DESC";


            Cursor c = database.query(
                    "ORDERS",
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);

        ArrayList<Order> orders = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(0);
                String costumer = c.getString(1);
                String att = c.getString(2);
                Order.Status status = parseStatus(c.getString(3));
                String datestr = c.getString(4);
                Long datelong = Long.parseLong(datestr);
                Date date = new Date(datelong);

                HashMap products = getProducts(id);
                Order order = new Order(costumer, products, date, att);
                orders.add(order);

                c.moveToNext();
            } while (!c.isLast());
        }
        c.close();

        database.setTransactionSuccessful();
        return orders;

    }

    private HashMap<Product,Integer> getProducts(String orderid){
        String[] projection = {
                "orderid",
                "productid",
                "productname",
                "productprice",
                "productamount"
        };

        String selection = "orderid = ?";
        String[] selectionargs = {orderid};

        Cursor c = database.query(
                "ORDERPRODUCTS",
                projection,
                selection,
                selectionargs,
                null,
                null,
                null);

        HashMap<Product,Integer> productmap = new HashMap<>();
        if (c.getCount() > 0) {
            c.moveToFirst();

            do {
                int amount = c.getInt(4);
                String name = c.getString(2);
                String id = c.getString(1);
                Double price = c.getDouble(3);
                Product p = new Product(name, id, price);
                productmap.put(p, amount);

                c.moveToNext();
            } while (!c.isLast());
        }
        c.close();

        return productmap;
    }

    private  Order.Status parseStatus(String str){
        String input = str.toLowerCase();
        switch(input){
            case "created":
                return Order.Status.Created;
            case "sent":
                return Order.Status.Sent;
            case "delivered":
                return Order.Status.Delivered;
            case "billed":
                return Order.Status.Billed;
            default:
                return null;
        }
    }
   // public Order getLastOrder(){
   //
   // }
}
