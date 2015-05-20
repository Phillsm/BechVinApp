package com.example.phill.bechvinapp.Model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Phill on 12-05-2015.
 */
public class Order {
    Status status;
    String costumer;
    HashMap<Product,Integer> products;
    Date date;
    String att;
    Double price;

    public enum Status {
        Created, Sent, Delivered, Billed
    }

    public Order(String costumer, HashMap<Product, Integer> products, Date date, String att) {
        this.status = Status.Created;
        this.costumer = costumer;
        this.products = products;
        this.date = date;
        this.att = att;

        for(Product p : products.keySet()){
            int amount = products.get(p);
            for(int i = 0; i<amount; i++){
                price += p.getPrice();
            }
        }
        //TODO: iterate hashmap and get accumulative price
    }
    public Order(Status status, String costumer, Date date, String att) {
        this.status = Status.Created;;
        this.costumer = costumer;
        this.products = new HashMap<>();
        this.date = date;
        this.att = att;
        this.price = 0.0;
    }

    public void addProductAmount(Product product, int amount){

        if(products.containsKey(product)){
            products.put(product,(products.get(product) + 1));
        }
        else{
            products.put(product,1);
        }


        for(int i = 0; i<amount; i++){
            this.price += product.getPrice();
        }
    }

    public Status getStatus() {
        return status;
    }

    public String getCostumer() {
        return costumer;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public Date getDate() {
        return date;
    }

    public String getAtt() {
        return att;
    }

    public Double getPrice() {
        return price;
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
