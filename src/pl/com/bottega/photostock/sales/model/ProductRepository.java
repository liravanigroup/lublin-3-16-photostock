package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 17/04/16.
 */
public interface ProductRepository {
    public Product load(String nr);
    public void save(Product product);
}
