package pl.com.bottega.photostock.sales.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by Slawek on 24/04/16.
 */
public class LightboxTest {

    @Test
    public void shouldCloneNames() throws CloneNotSupportedException {
        //given
        Client client = new Client("nr", "imię", "adres", new Money(0));
        LightBox lbx = new LightBox(client);

        //when
        LightBox clone = (LightBox) lbx.clone();

        //then
        Assert.assertEquals(clone.getName(), lbx.getName());
    }

    @Test
    public void shouldDeeplyCloneItems() throws CloneNotSupportedException {
        //given
        Client client = new Client("nr", "imię", "adres", new Money(0));
        LightBox lbx = new LightBox(client);
        Picture pic1 = new Picture("nr", new Money(2), null, true);
        lbx.add(pic1);

        Picture pic2 = new Picture("nr2", new Money(4), null, true);

        //when
        LightBox clone = (LightBox) lbx.clone();
        clone.add(pic2);

        //then
        Assert.assertEquals(1, lbx.getItemsCount());
        Assert.assertEquals(2, clone.getItemsCount());
    }
}
