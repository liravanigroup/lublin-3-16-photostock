package pl.com.bottega.photostock.sales.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Purchase;

import java.util.List;

/**
 * Created by Slawek on 14/05/16.
 */
public class AcceptanceStory {
    private static final Money INITIAL_MONEY = new Money(50);

    private static final Money PRICE_1 = new Money(10);
    private static final Money PRICE_2 = new Money(20);
    private static final Money PRICE_3 = new Money(30);
    private static final Money PRICE_4 = new Money(40);


    private static final Money TOTAL_COST = PRICE_1.add(PRICE_2).add(PRICE_3).add(PRICE_4);

    //tyle udzielimy limity kredytu aby wysatrczyło na różnicą mędzy kosztem a tym co ma klient
    private static final Money CREDIT_LIMIT = TOTAL_COST.substract(INITIAL_MONEY);


    private ProductsCatalog productsCatalog = new ProductsCatalog();
    private ClientManagement clientManagement = new ClientManagement();
    private LightBoxManagement lightBoxManagement = new LightBoxManagement();
    private PurchaseProcess purchaseProcess = new PurchaseProcess();
    private AdminPanel adminPanel = new AdminPanel();

    @Test
    public void story(){
        //admin dodaje produkty do oferty
        adminPanel.addPicture(PRICE_1, new String[]{"ford", "mustang"});
        adminPanel.addPicture(PRICE_2, new String[]{"bmw", "m6"});
        adminPanel.addPicture(PRICE_3, new String[]{"fiat", "multipla"});
        //ten produkt stanie sie pozniej niedostepny
        adminPanel.addPicture(PRICE_4, new String[]{"lamborghini", "huracan"});


        //użytkownik się rejestruje i doładowuje konto
        String clientNr = clientManagement.register("nazwa 1", "login 1", "email@server.com", "addresss");
        clientManagement.recharge(clientNr, INITIAL_MONEY);

        //admin awansuje klienta i daje mi limit kredytu
        adminPanel.promoteClient(clientNr);
        adminPanel.changeCreditLimit(clientNr, CREDIT_LIMIT);

        //użytkownik przeszukuje katalog dostępnych produktów
        List<Product> products = productsCatalog.find(null, null, null, null);

        //użytkownik dodaje pierwszy produkt do lbx
        String lightBoxNr = lightBoxManagement.createLightbox(clientNr, "lightbox 1");
        lightBoxManagement.add(products.get(0).getNumber(), lightBoxNr);

        //użytkonik dodaje drugi i trzeci produkt do rezerwacji
        //oraz czwarty, ktory stanie sie niedostepny
        purchaseProcess.add(clientNr, products.get(1).getNumber());
        purchaseProcess.add(clientNr, products.get(2).getNumber());
        //jego niedługo usuniemy z oferty
        purchaseProcess.add(clientNr, products.get(3).getNumber());


        //użytkownik dodaje do rezerwacji jeszcze produkty z lightboxa
        lightBoxManagement.addAllToReservation(lightBoxNr);

        //admin usuwa czwarty produkt
        adminPanel.changeAvability(products.get(3).getNumber(), false);

        //użytkownik przegląda ofertę - w rezerwacji ma 4, ale w ofercie znajdą się 3 elementy
        //gdyż jeden wlasnie usunieto
        Offer offer = purchaseProcess.calculateOffer(clientNr);
        Assert.assertEquals(TOTAL_COST.substract(products.get(3).calculatePrice()),
                            offer.getTotalCost());

        //użytkownik zatwierdza ofertę
        purchaseProcess.confirm(clientNr);

        //użytkownik przegląda swoje zakupy
        List<Purchase> purchases = clientManagement.findPurchases(clientNr);
        Assert.assertEquals(1, purchases.size());
    }
}
