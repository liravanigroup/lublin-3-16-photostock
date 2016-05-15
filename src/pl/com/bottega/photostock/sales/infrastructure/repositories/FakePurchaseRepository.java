package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Purchase;
import pl.com.bottega.photostock.sales.model.PurchaseRepository;

import java.util.*;

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

    @Override
    public List<Purchase> find(String clientNr) {
        List<Purchase> result = new LinkedList<>();

        for(Purchase purchase : fakeDatabase.values())
            if(purchase.getOwner().getNumber().equals(clientNr))
                result.add(purchase);

        return result;
    }
}
