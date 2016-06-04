package pl.com.bottega.photostock.sales.infrastructure.repositories;

import com.google.common.base.Strings;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.products.Picture;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                    loadTags(c, nr),
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
            insertTags(c, product);
        } catch (Exception ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<Product> find(String[] tags, String author, Money minPrice, Money maxPrice, boolean acceptNotavailable) {
        return null;
    }

    private void insertTags(Connection c, Product product) throws Exception {
        if (product instanceof Picture) {
            Picture picture = (Picture) product;
            String[] tags = picture.getTags();
            if(tags.length == 0)
                return;

            ResultSet rs = queryTags(c, tags);
            Set<String> existingTags = new HashSet<>();
            while (rs.next()) {
                existingTags.add(rs.getString("name"));
            }

            for (String tag : tags) {
                if (!existingTags.contains(tag))
                    insertTag(c, tag);
            }
            linkTags(c, picture);
        }
    }

    private void insertTag(Connection c, String tag) throws Exception {
        PreparedStatement s = c.prepareStatement("INSERT INTO Tags (name) VALUES (?)");
        s.setString(1, tag);
        s.executeUpdate();
    }

    private void linkTags(Connection c, Picture product) throws Exception {
        PreparedStatement s = c.prepareStatement("SELECT id FROM PRODUCTS WHERE NUMBER = ?");
        s.setString(1, product.getNumber());
        ResultSet rs = s.executeQuery();
        rs.next();
        int productId = rs.getInt("id");

        rs = queryTags(c, product.getTags());
        Set<Integer> tagIds = new HashSet<>();
        while (rs.next()) {
            tagIds.add(rs.getInt("id"));
        }

        //TODO Sprawdzić istniejące połączenia i dodać tylko nowe, ew. usunąć niepotrzebne stare połączenia
        //TODO SELECT który wyciąga istniejące połączenia z ProductsTags
        //TODO Iterowanie po wyiku żeby stwierdzić co trzeba dodać a co usunąć

        //TODO Usunąć niepotrzebne połączenia

        for (Integer tagId : tagIds)
            // TODO if połączenie NIE istnieje
            linkTag(c, productId, tagId);
    }

    private void linkTag(Connection c, Integer productId, Integer tagId) throws Exception {
        PreparedStatement s = c.prepareStatement("INSERT INTO ProductsTags (productId, tagId) VALUES (?, ?)");
        s.setInt(1, productId);
        s.setInt(2, tagId);
        s.executeUpdate();
    }

    private String[] loadTags(Connection c, String nr) throws Exception {
        PreparedStatement s = c.prepareStatement("SELECT Tags.name \n" +
                "FROM Tags \n" +
                "JOIN ProductsTags ON ProductsTags.tagId = Tags.id\n" +
                "JOIN Products ON Products.id = ProductsTags.productId\n" +
                "WHERE Products.number = ?");
        s.setString(1, nr);
        ResultSet rs = s.executeQuery();
        Set<String> tags = new HashSet<>();
        while (rs.next()) {
            tags.add(rs.getString("name"));
        }
        return tags.toArray(new String[0]);
    }

    private ResultSet queryTags(Connection c, String[] tags) throws Exception {
        String[] questionMarks = new String[tags.length];
        for (int i = 0; i < questionMarks.length; i++) {
            questionMarks[i] = "?";
        }
        String questionMarksConcat = String.join(",", questionMarks);

        PreparedStatement s = c.prepareStatement("SELECT id, name FROM Tags WHERE name IN (" + questionMarksConcat + ")");
        for (int i = 1; i <= tags.length; i++) {
            s.setString(i, tags[i - 1]);
        }
        return s.executeQuery();
    }


}
