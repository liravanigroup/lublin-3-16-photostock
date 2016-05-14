package pl.com.bottega.photostock.sales.infrastructure.repositories;

import org.junit.Test;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;
import pl.com.bottega.photostock.sales.model.products.Picture;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maciuch on 14.05.16.
 */
public class FileProductRepositoryTest {

    @Test
    public void shouldLoadProduct() {
        // given
        ProductRepository productRepository = new FileProductRepository(getClass().getResource("/fixtures/products.csv").getPath());

        //when
        Product product = productRepository.load("nr2");

        //then
        //assertEquals(new Product(...), product);

        assertEquals("nr2", product.getNumber());
        assertEquals(Picture.class, product.getClass());
        assertEquals(new Money(5.0, "USD"), product.calculatePrice());
        assertTrue(product.isAvailable());
        assertArrayEquals(new String[] {"tag1", "tag2", "tag3", "tag4"}, ((Picture) product).getTags());
    }

    @Test
    public void shouldThrowDataAccessExceptionWhenFileNotFound() {
        //given
        ProductRepository productRepository = new FileProductRepository("fake path");

        //when
        DataAccessException ex = null;
        try {
            productRepository.load("nr2");
        }
        catch(DataAccessException dae) {
            ex = dae;
        }

        //then
        assertNotNull(ex);
    }

}
