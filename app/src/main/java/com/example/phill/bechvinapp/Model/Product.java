package com.example.phill.bechvinapp.Model;


/**
 * Created by Phill on 12-05-2015.
 */
public class Product {
    String name;
    String id;
    Double price;

    public Product(String name, String id, Double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }
}
