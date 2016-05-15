package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.client.CreditedPayerStrategy;
import pl.com.bottega.photostock.sales.model.client.PayerStrategy;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by Slawek on 14/05/16.
 */
public class AdminPanel {

    private static final Money INITIAL_CREDIT_LIMIT = new Money(10);

    private ClientRepository clientRepository = new FakeClientRepository();
    private ProductRepository productRepository = new FakeProductRepository();

    public String addPicture(Money price, String[] tages){
        Picture picture = new Picture(null, price, tages, true);
        productRepository.save(picture);
        return  picture.getNumber();
    }

    public void promoteClient(String clientNr){
        Client client = clientRepository.load(clientNr);
        client.setPayerStrategy(new CreditedPayerStrategy(INITIAL_CREDIT_LIMIT, new Money(0)));
        clientRepository.save(client);
    }

    public void changeCreditLimit(String clientNr, Money creditLimit){
        Client client = clientRepository.load(clientNr);

        PayerStrategy payerStrategy = client.getPayerStrategy();
        if (payerStrategy instanceof CreditedPayerStrategy){
            CreditedPayerStrategy creditedPayerStrategy = (CreditedPayerStrategy) payerStrategy;
            client.setPayerStrategy(new CreditedPayerStrategy(creditLimit, creditedPayerStrategy.getDebt()));
        }
        else{
            throw new IllegalArgumentException(clientNr + " is not able to use credit limit");
        }

        clientRepository.save(client);
    }

    public void changeAvability(String productNr, boolean available) {
        Product product = productRepository.load(productNr);
        if (available)
            product.activate();
        else
            product.deactivate();
        productRepository.save(product);
    }
}
