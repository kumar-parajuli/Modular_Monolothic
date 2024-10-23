package com.kumar.modularMonothic;

import com.kumar.modularMonothic.ProductsModule.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testProductAttributes() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(99.99);

        assertEquals("Test Product", product.getName());
        assertEquals(99.99, product.getPrice());
    }
}
