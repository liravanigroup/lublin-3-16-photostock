package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

/**
 * Created by Slawek on 12/03/16.
 */
public class Picture extends AbstractProduct {

    private String[] tags;

    public Picture(String number, Money price, String[] tags, boolean isAvailabe) {
        super(number, isAvailabe, price);
        this.tags = tags;
    }


    public String[] getTags() {
        return tags;
    }

    //number,priceCents,priceCurrency,available,length,tags,type
    public String[] export() {
        Money price = calculatePrice();
        String tagsJoined = String.join(" ", this.getTags());
        return new String[]{
                getNumber(),
                String.valueOf(price.cents()),
                price.currency(),
                String.valueOf(isAvailable()),
                "",
                tagsJoined,
                "Picture"
        };
    }
}
