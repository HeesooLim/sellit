package com.project.sellit.repository;

import com.project.sellit.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Products p where p.username = ?1")
    List<Product> getProductsByUsername(String username);

    @Modifying
    @Query("update Products p set p.title = ?2, p.description = ?3, p.price = ?4, p.location = ?5, p.timestamp = ?6, p.status = ?7, p.favUsernames = ?8 where p.id = ?1")
    void updateProduct(int id, String title, String description, Double price, String location, Timestamp timestamp, String status, String favUsernames);

}
