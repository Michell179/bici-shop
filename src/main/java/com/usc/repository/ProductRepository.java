package com.usc.repository;

import com.usc.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        // Datos de prueba
        products.add(new Product(1, "Manzana", 0.50, 100));
        products.add(new Product(2, "Banana", 0.30, 80));
        products.add(new Product(3, "Naranja", 0.60, 50));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void updateProduct(Product product) {
        // En memoria, la lista ya contiene la referencia
    }
}
