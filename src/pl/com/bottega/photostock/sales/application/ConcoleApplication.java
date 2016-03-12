package pl.com.bottega.photostock.sales.application;


import pl.com.bottega.photostock.sales.model.*;

/**
 * Created by Slawek on 12/03/16.
 */
public class ConcoleApplication {
    
    public static void main(String[] args){
        //symulcja dostępu do bazy danych

        Picture pic1 = new Picture();
        Picture pic2 = new Picture();
        Picture pic3 = new Picture();

        Client client = new Client();

        Reservation reservation = new Reservation();

        //========================================

        reservation.add(pic1);
        reservation.add(pic2);
        reservation.add(pic3);

        //sytuacja niedozwolona
        reservation.add(pic1);
        reservation.add(pic2);
        reservation.add(pic3);

        int count = reservation.getItemsCount();
        System.out.println(count);

        Offer offer = reservation.generateOffer();
        double offerTotalCost = offer.getTotalCost();
        boolean canAfford = client.canAfford(offerTotalCost);

        if (canAfford){
            client.charge(offerTotalCost, "Za zdjęcia");
            Purchase purchase = new Purchase();//TODO przepakwoac z oferty do zakupu
        }
        else{
            System.out.println("Can not afford! You need " + offerTotalCost);
            return;
        }


    }
}
