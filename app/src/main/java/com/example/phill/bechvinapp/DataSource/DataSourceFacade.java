package com.example.phill.bechvinapp.DataSource;

import android.content.Context;
import android.util.Log;

import com.example.phill.bechvinapp.Model.Order;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Phill on 13-05-2015.
 */

public class DataSourceFacade {
        SQLHelper SQLHelper;
        OrderDataSource odc;

    public DataSourceFacade(Context context){
        SQLHelper = new SQLHelper(context);
        odc = new OrderDataSource(context);
    }

    public List<Order> getAllOrders(){
        List<Order> orders = null;
        try{
            odc.open();
            odc.beginTransaction();

            orders = odc.getAllOrders();
        }catch (Exception e){
            Log.e("BechWineApp", e.getMessage() + "\n"+stackTraceToString(e));
        }
        finally {
            odc.endTransaction();
            odc.close();
        }
        return orders;
    }

    public void saveOrder(Order order){
        try{
            odc.open();
            odc.beginTransaction();

            odc.saveOrder(order);
        }catch (Exception e){

        }
        finally {
            odc.endTransaction();
            odc.close();
        }
    }
    public String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
