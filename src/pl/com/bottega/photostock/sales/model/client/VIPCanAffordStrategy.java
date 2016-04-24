package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by Slawek on 23/04/16.
 */
public class VIPCanAffordStrategy implements CanAffordStrategy {
    @Override
    public boolean canAfford(Payer payer, Money value) {
        return payer.getAmount().gt(value);
    }
}
