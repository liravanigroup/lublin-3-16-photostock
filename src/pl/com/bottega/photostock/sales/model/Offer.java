package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.List;

/**
 * Created by Slawek on 12/03/16.
 */
public class Offer {

    private final Client owner;
    private List<Product> items;

    double totalCost;

    public Offer(Client owner, List<Product> items) {
        this.items = items;
        this.owner = owner;
        this.totalCost = calculateTotalCost();
    }

    private double calculateTotalCost() {
        return 0;//TODO
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getItemsCount(){
        return items.size();
    }

    public List<Product> getItems() {
        return items;
    }
}
