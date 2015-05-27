package com.example.phill.bechvinapp.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Phill on 12-05-2015.
 */

public class Order {
    Status status;
    String costumer;
    Map<Product,Integer> products;
    Date date;
    String att;

    public enum Status {
        Created, Sent, Delivered, Billed
    }

    public Order(String costumer, Map<Product, Integer> products, Date date, String att) {
        this.status = Status.Created;
        this.costumer = costumer;
        this.products = products;
        this.date = date;
        this.att = att;

//        for(Product p : products.keySet()){
//            int amount = products.get(p);
//            for(int i = 0; i<amount; i++){
//                price += p.getPrice();
//            }
//        }
        //TODO: iterate hashmap and get accumulative price
    }
    public Order(Status status, String costumer, Date date, String att) {
        this.status = Status.Created;;
        this.costumer = costumer;
        this.products = new Hashtable<>();
        this.date = date;
        this.att = att;
    }

    public void addProductAmount(Product product, int amount){

        products.put(product,amount);
        for(Product p : products.keySet()){
            if(p.id.equals(product.getId())){
                products.put(product,(products.get(product) + amount));
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    public String getCostumer() {
        return costumer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Date getDate() {
        return date;
    }

    public String getAtt() {
        return att;
    }

    public Double getPrice() {
        if (products == null){
            return 0.0;
        }else{
          double price = 0.0;
          for(Product p : products.keySet()){
            int amount = products.get(p);
            for(int i = 0; i<amount; i++){
                price += p.getPrice();
            }
          }
          return price;
        }
    }

    public boolean send(){
        //TODO: Fix when http api has been implemented
        return true;
        //TODO: if api returns accepted:
        // set status to sent
        // save to db
    }

   @Override
    public String toString(){

       String toStringed = "Kunde : " +getCostumer() + "\n" +
                           "Dato : " + getDate() + "\n" +
                           "Samlet pris for ordre : " + getPrice() + "\n" +
                           "Status : " + getStatus();
       return toStringed;
   }
}
