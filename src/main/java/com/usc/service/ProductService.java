package com.usc.service;

import com.usc.model.Product;
import com.usc.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAvailableProducts() {
        return repository.getAllProducts();
    }

    public boolean sellProduct(int productId, int quantity) {
        Product product = repository.getProductById(productId);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            repository.updateProduct(product);
            return true;
        }
        return false;
    }
}
