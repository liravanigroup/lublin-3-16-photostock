package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 03/04/16.
 */
public interface Product {
    public boolean isAvailable();
    public Money calculatePrice();
    public void cancel();
    public void reservePer(Client client);
    public void unReservePer(Client client);
    public String getNumber();
}
