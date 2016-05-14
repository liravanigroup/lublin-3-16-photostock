package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;

import java.util.List;

/**
 * Created by Slawek on 23/04/16.
 */
public class ProductsCatalog {
    private ProductRepository productRepository = new FakeProductRepository();

    public List<Product> find(String[] tags, String author,
                              Money minPrice, Money maxPrice){

        return productRepository.find(tags, author, minPrice, maxPrice, false);
    }
}
