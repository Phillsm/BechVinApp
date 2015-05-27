package com.example.phill.bechvinapp.DataSource;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.phill.bechvinapp.Model.Product;

/**
 * Created by Phill on 13-05-2015.
 */
public class ApiHelper {
    ApiMock api;

    private Context con;
    public ApiHelper(Context context){
        api = new ApiMock(context);
        con = context;
        Log.d("BechWineApp","Made APIHelper");
    }
    public ArrayList<Product> getAllWines(){
        String json = api.getWines();

        ArrayList<Product> wines = new ArrayList<>();
        try {
            JSONArray jarray = new JSONArray(json);

            Log.d("BechWineApp", "" + jarray.length());

            for(int i = 0; i<jarray.length(); i++){
                JSONObject jobj = jarray.getJSONObject(i);
                String id = jobj.getString("id");
                String name = jobj.getString("name");
                String pricestr = jobj.getString("price").replace(".","").replace(",",".");
                double price = Double.parseDouble(pricestr);

                Product p = new Product(name,id,price);
                wines.add(p);
            }
            Log.d("BechWineApp","Finished parsing wine json");
        }
        catch(Exception e){
            Log.d("BechWineApp", "Error in makin jarray" + e.getMessage() +"\n" + e.getStackTrace().toString());
            //TODO ERROR HANDLING
        }
        return wines;
    }



    public ArrayList<String> getAllCustomers(){
        String json = api.getCostumers();

        ArrayList<String> customers = new ArrayList<>();
        try {
            JSONArray jarray = new JSONArray(json);

            Log.d("BechWineApp",json);
            Log.d("BechWineApp", "" + jarray.length());

            for(int i = 0; i<jarray.length(); i++){

                String id = jarray.get(i).toString();
                customers.add(id);

            }
            Log.d("BechWineApp","Finished parsing costumer json");
        }
        catch(Exception e){
            Log.d("BechWineApp", "Error in makin jarray" + e.getMessage() +"\n" + e.getStackTrace().toString());
            //TODO ERROR HANDLING
        }
        return customers;
    }


}
