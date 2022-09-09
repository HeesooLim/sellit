package com.project.sellit.service;

import com.project.sellit.model.Product;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    List<Product> getAllBySellerUsername(String username);
    Product add(Product product);
    Optional<Product> getProduct(int id);

    void deleteProduct(int id);
    void updateProduct(int id, String title, String description, Double price, String location, Timestamp timestamp, String status, String favUsernames);
}
