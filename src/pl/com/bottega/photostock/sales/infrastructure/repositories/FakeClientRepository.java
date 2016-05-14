package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.ClientRepository;
import pl.com.bottega.photostock.sales.model.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Slawek on 23/04/16.
 */
public class FakeClientRepository implements ClientRepository {
    private static Map<String, Client> fakeDatabase = new HashMap<>();

    static{
        Client c1 = new Client("nr1", "czeslaw", "adres", new Money(10));
        Client c2 = new Client("nr2", "lama", "lamerski", new Money(1000));


        fakeDatabase.put(c1.getNumber(), c1);
        fakeDatabase.put(c2.getNumber(), c2);
    }

    @Override
    public Client load(String nr) {
        Client client = fakeDatabase.get(nr);
        if (client == null)
            throw new RuntimeException("client " + nr + " does not extist");//TODO wprowadzić wyjątek DataDoesNotExistsException
        return client;
    }

    @Override
    public void save(Client client) {
        if (client.getNumber() == null)
            client.setNumber(UUID.randomUUID().toString());
        fakeDatabase.put(client.getNumber(), client);
    }
}
