package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

/**
 * Created by Slawek on 17/04/16.
 */
public abstract class AbstractProduct implements Product{

    private String number;
    private boolean available;
    private Money price;

    public AbstractProduct(String number, boolean available, Money price) {
        this.number = number;
        this.available = available;
        this.price = price;
    }

    @Override
    public void activate() {
        available = true;
    }

    @Override
    public void deactivate() {
        available = false;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public Money calculatePrice() {
        return price;
    }

    @Override
    public void cancel() {
        available = false;
    }

    @Override
    public void reservePer(Client client) {
        //TODO
    }

    @Override
    public void unReservePer(Client client) {
        //TODO
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractProduct that = (AbstractProduct) o;

        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
