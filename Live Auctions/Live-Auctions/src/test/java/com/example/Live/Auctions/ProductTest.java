/*
package com.example.Live.Auctions;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
public class ProductTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductDao productDao;

    @Test
    public void getProductsTest() {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Jaguar", "expensive car", "cars", 99000));
        productList.add(new Product("Lexus", "expensive car", "cars", 80000));

        when(productDao.findAll()).thenReturn(productList);

        List<Product> result = productService.getProducts();

        assertEquals(2, result.size());
        assertEquals("Jaguar", result.get(0).getTitle());
        assertEquals("expensive car", result.get(0).getDescription());
        assertEquals("cars", result.get(0).getCategory());
        assertEquals(99000, result.get(0).getBid());

        assertEquals("Lexus", result.get(1).getTitle());
        assertEquals("expensive car", result.get(1).getDescription());
        assertEquals("cars", result.get(1).getCategory());
        assertEquals(80000, result.get(1).getBid());

    }

    @Test
    public void createProductTest() {
        Product product = new Product("Jaguar", "expensive car", "cars", 99000);

        when(productDao.save(any(Product.class))).thenReturn(new Product());

        productService.addProduct(product);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productDao, times(1)).save(captor.capture());
        Product saveProduct = captor.getValue();

        assertEquals("Jaguar", saveProduct.getTitle());
        assertEquals("expensive car", saveProduct.getDescription());
        assertEquals("cars", saveProduct.getCategory());
        assertEquals(99000, saveProduct.getBid());
    }

    @Test
    public void updateProductTest() {

        long id = 0L;
        Product product = new Product("Jaguar", "expensive car", "cars", 99000);


        Product existingProduct = new Product("Lexus", "expensive car", "cars", 80000);
        existingProduct.setId(id);

        when(productDao.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productDao.save(existingProduct)).thenReturn(existingProduct);

        Product updateProduct = productService.updateProduct(id, product);

        assertEquals(product.getId(), updateProduct.getId());
        assertEquals(product.getTitle(), updateProduct.getTitle());
        assertEquals(product.getDescription(), updateProduct.getDescription());
        assertEquals(product.getCategory(), updateProduct.getCategory());
        assertEquals(product.getBid(), updateProduct.getBid());

    }
}
*/
