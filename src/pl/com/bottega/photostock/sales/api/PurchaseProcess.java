package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakePurchaseRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeReservationRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.*;

/**
 * Created by Slawek on 23/04/16.
 */
public class PurchaseProcess {

    private ClientRepository clientRepository = new FakeClientRepository();
    private ReservationRepository reservationRepository = new FakeReservationRepository();
    private ProductRepository productRepository = new FakeProductRepository();
    private PurchaseRepository purchaseRepository = new FakePurchaseRepository();

    public String create(String clientNr){
        Client client = clientRepository.load(clientNr);

        Reservation reservation = new Reservation(client);

        reservationRepository.save(reservation);
        return reservation.getNumber();
    }

    public void add(String reservatonNr, String productNumber){
        Reservation reservation = reservationRepository.load(reservatonNr);
        Product product = productRepository.load(productNumber);

        reservation.add(product);

        reservationRepository.save(reservation);
        productRepository.save(product);
    }

    public Offer calculateOffer(String reservationNr){
        Reservation reservation = reservationRepository.load(reservationNr);
        return reservation.generateOffer();
    }

    public void confirm(String reservationNr){
        Reservation reservation = reservationRepository.load(reservationNr);
        Client client = reservation.getOwner();
        confirm(client, reservation);
    }

    public void confirm(String reservationNr, String payerNr){
        Reservation reservation = reservationRepository.load(reservationNr);
        Client client = clientRepository.load(payerNr);
        confirm(client, reservation);
    }

    private void confirm(Client client, Reservation reservation){
        Offer offer = reservation.generateOffer();
        if (client.canAfford(offer.getTotalCost())){
            client.charge(offer.getTotalCost(), "za rezerwację " + reservation.getNumber());

            Purchase purchase = new Purchase(client, offer.getItems());

            purchaseRepository.save(purchase);
            clientRepository.save(client);
        }
    }

}