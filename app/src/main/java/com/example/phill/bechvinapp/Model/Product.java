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

    @Override
    public String toString(){

        String toStringed = "Vinen : " + getName() + "\n" + " Pris : " + getPrice() ;
        return toStringed;
    }
   // @Override
   // public boolean equals(Object obj){
    //    if (!(obj instanceof Product))
    //        return false;
    //    if (obj == this)
    //        return true;

    //    Product other = (Product) obj;
    //    return this.id == other.getId() && this.getName() == other.getName();
   // }

   @Override
    public int hashCode(){
       return this.id.hashCode() + this.getName().hashCode();
   }
}
