package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.ReservationRepository;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public void save(Reservation reservation) {
        if (reservation.getNumber() == null)
            reservation.setNumber(UUID.randomUUID().toString());//symulacja generowania ID przez bazę danych
        fakeDatabase.put(reservation.getNumber(), reservation);
    }
}
