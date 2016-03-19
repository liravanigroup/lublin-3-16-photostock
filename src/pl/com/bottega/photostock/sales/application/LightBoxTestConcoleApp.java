package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Picture;

/**
 * Created by Slawek on 19/03/16.
 */
public class LightBoxTestConcoleApp {
    public static void main(String[] args){
        Client client = new Client("Pan janusz", "tajny adres", 20);
        LightBox lightBoxJanusza = new LightBox(client);

        Picture lumberJack = new Picture("nr1", 2, null, true);
        Picture kitty = new Picture("nr2", 2, null, true);

        lightBoxJanusza.add(lumberJack);
        lightBoxJanusza.add(kitty);

        lightBoxJanusza.add(lumberJack);

    }
}
