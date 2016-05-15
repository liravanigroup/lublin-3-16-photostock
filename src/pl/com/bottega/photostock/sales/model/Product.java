package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 03/04/16.
 */
public interface Product {
    boolean isAvailable();
    Money calculatePrice();
    void cancel();
    void reservePer(Client client);
    void unReservePer(Client client);
    String getNumber();
    void setNumber(String number);

    String[] export();
    void deactivate();
    void activate();
}
