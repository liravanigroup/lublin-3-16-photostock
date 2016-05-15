package pl.com.bottega.photostock.sales.model;

import java.util.List;

/**
 * Created by Slawek on 23/04/16.
 */
public interface PurchaseRepository {
    Purchase load(String nr);

    void save(Purchase purchase);

    List<Purchase> find(String clientNr);
}
