package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;

import java.util.*;

/**
 * Created by Slawek on 17/04/16.
 */
public class FakeProductRepository implements ProductRepository {

    private static Map<String, Product> fakeDatabase = new HashMap<>();

    /*
    static{
        Picture p1 = new Picture("nr1", new Money(10), new String[]{"ford", "mustang"}, true);
        Picture p2 = new Picture("nr2", new Money(20), new String[]{"fiat", "multipla"}, true);
        Picture p3 = new Picture("nr3", new Money(20), new String[]{"bmw", "m6"}, true);

        fakeDatabase.put(p1.getNumber(), p1);
        fakeDatabase.put(p2.getNumber(), p2);
        fakeDatabase.put(p3.getNumber(), p3);
    }*/

    @Override
    public Product load(String nr) {
        Product product = fakeDatabase.get(nr);
        if (product == null)
            throw new RuntimeException("product " + nr + " does not extist");//TODO wprowadzić wyjątek DataDoesNotExistsException
        return product;
    }

    @Override
    public void save(Product product) {
        if (product.getNumber() == null)
            product.setNumber(UUID.randomUUID().toString());
        fakeDatabase.put(product.getNumber(), product);
    }

    @Override
    public List<Product> find(String[] tags, String author,
                              Money minPrice, Money maxPrice, boolean acceptNotavailable) {
        //return new ArrayList<>(fakeDatabase.values());

        List<Product> result = new LinkedList<>();

        if (emptyFilter(tags, author, minPrice, maxPrice, acceptNotavailable))
            return new ArrayList<>(fakeDatabase.values());

        for(Product product : fakeDatabase.values()){
            if (! (acceptNotavailable || product.isAvailable()))
                continue;

            if (minPrice != null && product.calculatePrice().gt(minPrice))
                result.add(product);

            if (maxPrice != null && product.calculatePrice().lt(maxPrice))
                result.add(product);
        }

        return result;
    }

    private boolean emptyFilter(String[] tags, String author, Money minPrice, Money maxPrice, boolean acceptNotavailable) {
        return !acceptNotavailable &&
                (tags == null || tags.length == 0)
                && author == null
                && minPrice == null && maxPrice == null;
    }
}
