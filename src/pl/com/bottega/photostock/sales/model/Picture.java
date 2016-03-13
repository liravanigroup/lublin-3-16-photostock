package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class Picture {

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

    public void reservedPer(Client reservingClient) {

    }

    public void unreservedPer(Client unreservigClient){

    }



    public boolean isAvailable(){
        return isAvailabe;

    }

    public void cancel() {
        isAvailabe = false;
    }
}
