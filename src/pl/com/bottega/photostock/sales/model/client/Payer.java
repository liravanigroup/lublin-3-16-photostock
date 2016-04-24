package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by Slawek on 23/04/16.
 */
public interface Payer {
    Money getAmount();

    void setAmount(Money value);
}
