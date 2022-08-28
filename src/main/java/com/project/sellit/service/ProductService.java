package com.project.sellit.service;

import com.project.sellit.model.Product;
import com.project.sellit.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getAllBySellerId(int id);
}
