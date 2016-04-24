package pl.com.bottega.photostock.sales.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.photostock.sales.model.client.CreditedPayerStrategy;
import pl.com.bottega.photostock.sales.model.client.PayerStrategy;

/**
 * Created by Slawek on 24/04/16.
 */
public class Client2CreditedTest {
    private Client2 client;

    @Before
    public void init(){
        Money creditLimit = new Money(50);
        Money debt = new Money(0);

        PayerStrategy payerStrategy = new CreditedPayerStrategy(creditLimit, debt);

        client = new Client2("nr1", "imie", "adres", new Money(10), payerStrategy);
    }

    @Test
    public void shuldCalculateSaldo(){
        Money saldo = client.getSaldo();
        Assert.assertEquals(new Money(10), saldo);
    }

    @Test
    public void shuldcharge(){
        client.charge(new Money(15), "cause");
        Money saldo = client.getSaldo();
        Assert.assertEquals(new Money(-5), saldo);
    }

    @Test
    public void shuldRecharge(){
        client.recharge(new Money(30));
        Money saldo = client.getSaldo();
        Assert.assertEquals(new Money(40), saldo);
    }
}
