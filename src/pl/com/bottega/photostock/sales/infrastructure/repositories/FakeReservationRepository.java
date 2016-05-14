package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.ReservationRepository;

import java.util.*;

/**
 * Created by Slawek on 23/04/16.
 */
public class FakeReservationRepository implements ReservationRepository {
    private static Map<String, Reservation> fakeDatabase = new HashMap<>();


    @Override
    public Reservation load(String nr) {
        Reservation reservation = fakeDatabase.get(nr);
        if (reservation == null)
            throw new RuntimeException("reservation " + nr + " does not exist");//TODO wprowadzić wyjątek DataDoesNotExistsException
        return reservation;
    }

    @Override
    public Reservation findOpenedPer(Client client) {
        for (Reservation reservation : fakeDatabase.values()){
            if (reservation.getOwner().equals(client) && !reservation.isClosed())
                return reservation;
        }
        return null;
    }

    @Override
    public List<Reservation> find(String clientNr) {
        List<Reservation> result = new LinkedList<>();
        for (Reservation reservation : fakeDatabase.values()){
            if (reservation.getOwner().getNumber().equals(clientNr))
                result.add(reservation);
        }
        return result;
    }

    @Override
    public void save(Reservation reservation) {
        if (reservation.getNumber() == null)
            reservation.setNumber(UUID.randomUUID().toString());//symulacja generowania ID przez bazę danych
        fakeDatabase.put(reservation.getNumber(), reservation);
    }
}
