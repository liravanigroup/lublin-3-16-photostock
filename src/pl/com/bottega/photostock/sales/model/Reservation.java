package pl.com.bottega.photostock.sales.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Slawek on 12/03/16.
 */
public class Reservation {

    private Client owner;
    private List<Product> items = new LinkedList<>();

    public Reservation(Client owner) {
        this.owner = owner;
    }

    public void add(Product product){
        //....
        if (!product.isAvailable())
            throw new IllegalArgumentException("product is not available");
        if (items.contains(product))
            throw new IllegalArgumentException("product is duplicated");

        items.add(product);
    }

    public void remove(Product product){
        boolean removed = items.remove(product);
        if (!removed)
            throw new IllegalArgumentException("product never added");
    }

    public Offer generateOffer(){
        List<Product> result = new ArrayList<>();

        for(Product product : items){
            if (product.isAvailable()){
                result.add(product);
            }
        }

        return new Offer(owner, result);
    }

    public int getItemsCount() {
        return items.size();
    }
}
