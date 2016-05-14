package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.client.StandardPayerStrategy;

/**
 * Created by Slawek on 13/03/16.
 */
public class ClientTestConsoleApp{
    public static void main(String[] args) {
        Client panJanusz = new Client("nr1", "Janusz", "ksiezyc", new Money(4), new StandardPayerStrategy());
        panJanusz.recharge(new Money(10));
        if (panJanusz.canAfford(new Money(12))) {
            panJanusz.charge(new Money(12), "za coś");
            System.out.println("Saldo: " + panJanusz.getSaldo());
        }
        else{
            System.out.println("can not afford!");
        }
    }
}
