package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.Client2;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by Slawek on 23/04/16.
 */
public interface PayerStrategy {
    boolean canAfford(Payer payer, Money value);
    Money calculateSaldo(Payer payer);

    void recharge(Payer payer, Money value);
    void charge(Payer payer, Money value, String cause);
}
