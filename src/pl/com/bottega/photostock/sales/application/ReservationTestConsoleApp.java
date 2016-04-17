package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Clip;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by Slawek on 13/03/16.
 */
public class ReservationTestConsoleApp {
    public static void main(String[] args) {
        ProductRepository repository = new FakeProductRepository();

        Product mustang = repository.load("nr1"); //new Picture("nr1", 10, new String[]{"ford", "mustang"}, true);
        Product multipla = repository.load("nr2"); //new Picture("nr1", 10, new String[]{"ford", "mustang"}, true);

        //Clip wlaczamyNiskieCeny = new Clip();

        Client takiSobieClient = new Client("Zegrzysław", "tajny", new Money(0));//TODO pobrac z repo

        Reservation reservation = new Reservation(takiSobieClient);//TODO pobrac z repo
        //reservation.add(mustang);

        try {
            reservation.add(mustang);
            reservation.add(multipla);
        }
        catch (ProductNotAvailableException ex){
            System.out.println(ex.getClazz() + " " + ex.getMessage() + " " + ex.getNumber());
        }

        repository.save(mustang);
        repository.save(multipla);

        //reservation.add(wlaczamyNiskieCeny);

        Offer ofertaDlaZegrzyslawa = reservation.generateOffer();
        int count = ofertaDlaZegrzyslawa.getItemsCount();
        System.out.println("Ilość pozycji w ofercie: " + count);
    }
}
