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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!value.equals(money.value)) return false;
        return currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
