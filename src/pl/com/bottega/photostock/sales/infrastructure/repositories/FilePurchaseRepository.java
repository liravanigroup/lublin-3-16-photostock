package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.ClientRepository;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.Purchase;
import pl.com.bottega.photostock.sales.model.PurchaseRepository;

import java.util.List;

/**
 * Created by maciuch on 15.05.16.
 */
public class FilePurchaseRepository implements PurchaseRepository{

    private final String path;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public FilePurchaseRepository(String path, ClientRepository clientRepository, ProductRepository productRepository) {
        this.path = path;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Purchase load(String nr) {
        return null;
    }

    @Override
    public void save(Purchase purchase) {

    }

    @Override
    public List<Purchase> find(String clientNr) {
        return null;
    }
}
