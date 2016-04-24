package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.client.Payer;
import pl.com.bottega.photostock.sales.model.client.PayerStrategy;

/**
 * Created by Slawek on 23/04/16.
 */
public class StandardPayerStrategy implements PayerStrategy {
    @Override
    public boolean canAfford(Payer payer, Money value) {
        return payer.getAmount().gt(value);
    }

    @Override
    public Money calculateSaldo(Payer payer) {
        return payer.getAmount();
    }

    @Override
    public void charge(Payer payer, Money value, String cause) {
        Money actual = payer.getAmount();
        Money newValue = actual.substract(value);
        payer.setAmount(newValue);
    }

    @Override
    public void recharge(Payer payer, Money value) {
        Money actual = payer.getAmount();
        Money newValue = actual.add(value);
        payer.setAmount(newValue);
    }
}
