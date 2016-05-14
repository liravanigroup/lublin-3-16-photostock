package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.client.StandardPayerStrategy;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by Slawek on 19/03/16.
 */
public class LightBoxTestConcoleApp {
    public static void main(String[] args){
        Client client = new Client("nr1", "Pan janusz", "tajny adres", new Money(20), new StandardPayerStrategy());
        LightBox lightBoxJanusza = new LightBox(client);

        Picture lumberJack = new Picture("nr1", new Money(2), null, true);
        Picture kitty = new Picture("nr2", new Money(2), null, true);
        //Picture lumberJack2 = new Picture("nr1", 2, null, true);

        try {
            lightBoxJanusza.add(lumberJack);
            lightBoxJanusza.close();//!!!!!!
            lightBoxJanusza.add(kitty);
            lightBoxJanusza.add(lumberJack);
        }
        catch(IllegalStateException skucha){
            System.out.println("nie udało się " + skucha.getLocalizedMessage());
        }
        catch(IllegalArgumentException ex){
            //..
        }
        finally{//przykład, kod, który wykona się niezależnie od tego czy był wyjątek czy nie
            System.out.println("fajnie że żyjesz");
        }

        int count = lightBoxJanusza.getItemsCount();
        System.out.println("ilosc elementow " + count);

    }
}
