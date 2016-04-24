package pl.com.bottega.photostock.sales.model.client;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.client.Payer;
import pl.com.bottega.photostock.sales.model.client.PayerStrategy;

/**
 * Created by Slawek on 23/04/16.
 */
public class CreditedPayerStrategy implements PayerStrategy {

    private Money creditLimit;
    private Money debt;

    public CreditedPayerStrategy(Money creditLimit, Money debt){
        this.creditLimit = creditLimit;
        this.debt = debt;
    }

    @Override
    public boolean canAfford(Payer payer, Money value) {
        Money purchasePotential = payer.getAmount().add(this.creditLimit.substract(this.debt));
        return purchasePotential.ge(value);
    }

    @Override
    public Money calculateSaldo(Payer payer) {
        return payer.getAmount().substract(debt);
    }

    @Override
    public void recharge(Payer payer, Money value) {
        this.debt = this.debt.substract(value);
        if (this.debt.lt(this.debt.getZero())){
            payer.setAmount(payer.getAmount().substract(this.debt));
            this.debt = this.debt.getZero();
        }
    }

    @Override
    public void charge(Payer payer, Money value, String cause) {
        Money amount = payer.getAmount().substract(value);
        if (amount.lt(amount.getZero())){
            this.debt = this.debt.substract(amount);
            payer.setAmount(value.getZero());
        }
    }
}
