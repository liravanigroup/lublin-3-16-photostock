package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.products.Clip;
import pl.com.bottega.photostock.sales.model.products.Picture;
import pl.com.bottega.photostock.sales.model.Reservation;

/**
 * Created by Slawek on 13/03/16.
 */
public class ReservationTestConsoleApp {
    public static void main(String[] args) {
        Picture mustang = new Picture("nr1", 10, new String[]{"ford", "mustang"}, true);
        Clip wlaczamyNiskieCeny = new Clip();

        Client takiSobieClient = new Client("Zegrzysław", "tajny", 0);

        Reservation reservation = new Reservation(takiSobieClient);
        reservation.add(mustang);
        reservation.add(wlaczamyNiskieCeny);

        Offer ofertaDlaZegrzyslawa = reservation.generateOffer();
        int count = ofertaDlaZegrzyslawa.getItemsCount();
        System.out.println("Ilość pozycji w ofercie: " + count);
    }
}
