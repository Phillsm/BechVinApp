package com.example.phill.bechvinapp.DataSource;

import android.content.Context;

import org.json.*;
import java.io.*;
/**
 * Created by Phill on 13-05-2015.
 */
public class ApiMock {
    JSONArray wines;
    JSONArray costumers;

    public ApiMock(Context con) {
        // get wines
        wines = new JSONArray();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(
                    new InputStreamReader(con.getAssets().open("varer.csv"))
            );
            //discard first 5 lines
            for(int i = 0; i<5;i++){
                reader.readLine();
            }
            String line = reader.readLine();
            while (line != null){
                JSONObject obj = new JSONObject();
                String[] lines = line.split(";");
                obj.put("id",lines[0]);
                obj.put("name",lines[1]);
                obj.put("price",lines[3]);
                wines.put(obj);
                line = reader.readLine();
            }
        }
        catch(Exception e){

        }
        // get costumers
        costumers = new JSONArray();
        BufferedReader breader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(con.getAssets().open("kunder.txt"))
            );
            String currentline = breader.readLine();
            while (currentline != null){
                costumers.put(currentline);
                currentline = breader.readLine();
            }
        }
        catch(Exception e){

        }

    }

    public String getWines() {
        return wines.toString();
    }
    public String getCostumers()
    {
        return costumers.toString();
    }
}
