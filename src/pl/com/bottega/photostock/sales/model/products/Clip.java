package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

/**
 * Created by Slawek on 03/04/16.
 */
public class Clip extends AbstractProduct {

    private long length;

    public Clip(String number, boolean available, Money price, long lengt) {
        super(number, available, price);
        this.length = lengt;
    }

    public long getLength() {
        return length;
    }

    //TODO extract to AbstractPorduct
    //number,priceCents,priceCurrency,available,length,tags,type
    public String[] export() {
        Money price = calculatePrice();
        return new String[]{
                getNumber(),
                String.valueOf(price.cents()),
                price.currency(),
                String.valueOf(isAvailable()),
                String.valueOf(this.length),
                "",
                "Clip"
        };
    }
}
