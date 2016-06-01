package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.products.Picture;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

/**
 * Created by maciuch on 29.05.16.
 */
public class JDBCProductRepository implements ProductRepository {

    private final String url;
    private final String login;
    private final String pwd;

    public JDBCProductRepository(String url, String login, String pwd) {
        this.url = url;
        this.login = login;
        this.pwd = pwd;
    }

    @Override
    public Product load(String nr) {
        try (Connection c = DriverManager.getConnection(url, login, pwd)) {
            PreparedStatement s = c.prepareStatement("SELECT number, priceCents, priceCurrency, available FROM Products WHERE number = ?");
            s.setString(1, nr);
            ResultSet rs = s.executeQuery();
            if (!rs.next())
                return null;
            return new Picture(
                    rs.getString("number"),
                    new Money(rs.getInt("priceCents") / 100.0, rs.getString("priceCurrency")),
                    new String[]{},
                    rs.getBoolean("available")
            );
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void save(Product product) {
        try (Connection c = DriverManager.getConnection(url, login, pwd)) {
            PreparedStatement s = c.prepareStatement("INSERT INTO Products (number, available, priceCents, priceCurrency, type) VALUES (?, ?, ?, ?, 'Picture');");
            s.setString(1, product.getNumber());
            s.setBoolean(2, product.isAvailable());
            s.setInt(3, product.calculatePrice().cents());
            s.setString(4, product.calculatePrice().currency());
            s.execute();
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<Product> find(String[] tags, String author, Money minPrice, Money maxPrice, boolean acceptNotavailable) {
        return null;
    }
}
