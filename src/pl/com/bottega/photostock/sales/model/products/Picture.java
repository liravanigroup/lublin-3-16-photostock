package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;

/**
 * Created by Slawek on 12/03/16.
 */
public class Picture implements Product{

    private String number;
    private double price;
    private String[] tags;
    private boolean isAvailabe;

    public Picture(String number, double price, String[] tags, boolean isAvailabe) {
        this.number = number;
        this.price = price;
        this.tags = tags;
        this.isAvailabe = isAvailabe;
    }

    public double calculatePrice(){
        return 0; //TODO dodac alg wyliczania
    }


    public boolean isAvailable(){
        return isAvailabe;

    }

    public void cancel() {
        isAvailabe = false;
    }

    @Override
    public void reservePer(Client client) {

    }

    @Override
    public void unReservePer(Client client) {

    }

    public String getNumber() {
        return number;
    }
}
