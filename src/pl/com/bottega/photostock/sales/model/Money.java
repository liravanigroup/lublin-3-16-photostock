package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class Money {
    private final Double value = null;//TODO zamienić na Fraction
    private final String currency = null;//TODO zamienić na użycie klasy Currency z biblioteki Javy

    public Money(Double value, String currency) {
        //TODO
    }

    public Money(int integerValue, int cents, String currency) {
        //TODO

    }

    public Money(double value) {
        this(value, "PLN");
    }

    public Money add(Money amount){
        return null;//TODO
    }

    public Money substract(Money amount){
        return null;//TODO
    }

    public Money multiple(int ratio){
        return null;//TODO
    }

    public Money multiple(double ratio){
        return null;//TODO
    }

    public boolean equals(Object obj){
        return false;//TODO
    }
}
