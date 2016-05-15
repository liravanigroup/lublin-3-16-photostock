package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakePurchaseRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeReservationRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.client.StandardPayerStrategy;

import java.util.List;

/**
 * Created by Slawek on 14/05/16.
 */
public class ClientManagement {
    private ClientRepository clientRepository = new FakeClientRepository();
    private ReservationRepository reservationRepository = new FakeReservationRepository();
    private PurchaseRepository purchaseRepository = new FakePurchaseRepository();

    public String register(String name, String login, String email, String address){
        Client client = new Client(
                null, name, address, new Money(0), new StandardPayerStrategy());

        clientRepository.save(client);

        return client.getNumber();
    }

    public void recharge(String clientNr, Money value){
        Client client = clientRepository.load(clientNr);
        client.recharge(value);
        clientRepository.save(client);
    }

    public List<Reservation> findReservations(String clientNr){
        return reservationRepository.find(clientNr);
    }

    public List<Purchase> findPurchases(String clientNr){
        return purchaseRepository.find(clientNr);
    }
}
