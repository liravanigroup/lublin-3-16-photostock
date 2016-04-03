package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Product;

/**
 * Created by Slawek on 03/04/16.
 */
public class Clip implements Product{
    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public double calculatePrice() {
        return 0;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void reservePer(Client client) {

    }

    @Override
    public void unReservePer(Client client) {

    }
}
