package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.Purchase;
import pl.com.bottega.photostock.sales.model.PurchaseRepository;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Slawek on 23/04/16.
 */
public class FakePurchaseRepository implements PurchaseRepository {
    private static Map<String, Purchase> fakeDatabase = new HashMap<>();

    @Override
    public Purchase load(String nr) {
        Purchase purchase = fakeDatabase.get(nr);
        if (purchase == null)
            throw new RuntimeException("purchase " + nr + " does not extist");//TODO wprowadzić wyjątek DataDoesNotExistsException
        return purchase;
    }

    @Override
    public void save(Purchase purchase) {
        if (purchase.getNumber() == null)
            purchase.setNumber(UUID.randomUUID().toString());
        fakeDatabase.put(purchase.getNumber(), purchase);
    }
}
