package com.project.sellit.service;

import com.project.sellit.model.Product;
import com.project.sellit.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllBySellerUsername(String username) {
        return productRepository.getProductsByUsername(username);
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateProduct(int id, String title, String description, Double price, String location, Timestamp timestamp, String status, String favUsernames) {
        productRepository.updateProduct(id, title, description, price, location, timestamp, status, favUsernames);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
