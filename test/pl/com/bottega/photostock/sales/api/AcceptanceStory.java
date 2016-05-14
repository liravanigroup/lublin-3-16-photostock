package pl.com.bottega.photostock.sales.api;

import org.junit.Assert;
import org.junit.Test;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Purchase;

import java.util.List;

/**
 * Created by Slawek on 14/05/16.
 */
public class AcceptanceStory {
    private static final Money INITIAL_MONEY = new Money(100);

    private ProductsCatalog productsCatalog = new ProductsCatalog();
    private ClientManagement clientManagement = new ClientManagement();
    private LightBoxManagement lightBoxManagement = new LightBoxManagement();
    private PurchaseProcess purchaseProcess = new PurchaseProcess();
    private AdminPanel adminPanel = new AdminPanel();

    @Test
    public void story(){
        //admin dodaje produkty do oferty
        adminPanel.addPicture(new Money(10), new String[]{"ford", "mustang"});
        adminPanel.addPicture(new Money(20), new String[]{"bmw", "m6"});
        adminPanel.addPicture(new Money(30), new String[]{"fiat", "multipla"});

        //użytkownik się rejestruje i doładowuje konto
        String clientNr = clientManagement.register("nazwa 1", "login 1", "email@server.com", "addresss");
        clientManagement.recharge(clientNr, INITIAL_MONEY);

        //użytkownik przeszukuje katalog dostępnych produktów
        List<Product> products = productsCatalog.find(null, null, null, null);

        //użytkownik dodaje pierwszy produkt do lbx
        String lightBoxNr = lightBoxManagement.createLightbox(clientNr, "lightbox 1");
        lightBoxManagement.add(products.get(0).getNumber(), lightBoxNr);

        //użytkonik dodaje drugi i trzeci produkt do rezerwacji
        purchaseProcess.add(clientNr, products.get(1).getNumber());
        purchaseProcess.add(clientNr, products.get(2).getNumber());

        //użytkownik dodaje do rezerwacji jeszcze produkty z lightboxa
        lightBoxManagement.addAllToReservation(lightBoxNr);

        //użytkownik przegląda ofertę
        Offer offer = purchaseProcess.calculateOffer(clientNr);
        Assert.assertEquals(new Money(60), offer.getTotalCost());

        //użytkownik zatwierdza ofertę
        purchaseProcess.confirm(clientNr);

        //użytkownik przegląda swoje zakupy
        List<Purchase> purchases = clientManagement.findPurchases(clientNr);
    }
}
