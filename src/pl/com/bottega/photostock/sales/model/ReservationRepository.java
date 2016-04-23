package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 23/04/16.
 */
public interface ReservationRepository {
    void save(Reservation reservation);

    Reservation load(String reservatonNr);
}
