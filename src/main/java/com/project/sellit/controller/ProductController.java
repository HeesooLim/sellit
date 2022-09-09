package com.project.sellit.controller;

import com.project.sellit.model.JWTPayload;
import com.project.sellit.model.Product;
import com.project.sellit.service.ProductService;
import com.project.sellit.utils.Auth;
import com.project.sellit.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/user/product/all")
    public List<Product> getProductByUser(@RequestHeader (name = "Authorization") String token) {
        JWTPayload payload = Auth.getUserFromToken(token);
        return productService.getAllBySellerUsername(payload.getUsername());
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestHeader (name = "Authorization") String token, @RequestBody Product product) {
        // get username from token
        JWTPayload payload = Auth.getUserFromToken(token);

        product.setUsername(payload.getUsername());
        product.setTimestamp(DateUtil.getCurrentTimestamp());

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/product/add").toUriString());
        return ResponseEntity.created(uri).body(productService.add(product));
    }

    @GetMapping("/product/all")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/product")
    public Optional<Product> getProductDetail(@RequestParam (name = "id") int productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/product/edit")
    public ResponseEntity<?> editProduct(@RequestHeader (name= "Authorization") String token, @RequestBody Product product) {
        JWTPayload payload = Auth.getUserFromToken(token);
        Optional<Product> originalProd = productService.getProduct(product.getId());

        if (originalProd.isEmpty())
            return ResponseEntity.ok(null);

        // prevent other users from editing someone else's product info
        if (Auth.isAdmin(payload.getAuthorities()) || payload.getUsername().equals(originalProd.get().getUsername())) {
            productService.updateProduct(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getLocation(), DateUtil.getCurrentTimestamp(), product.getStatus(), product.getFavouriteIds());
            return ResponseEntity.ok("successfully updated");
        }
        return ResponseEntity.status(403).body("Access to the requested resource is forbidden.");
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<?> deleteProduct(@RequestHeader (name= "Authorization") String token, @RequestParam (name = "id") int id) {
        JWTPayload payload = Auth.getUserFromToken(token);
        Optional<Product> originalProd = productService.getProduct(id);

        if (originalProd.isEmpty())
            return ResponseEntity.ok(null);

        // prevent other users from editing someone else's product info
        if (Auth.isAdmin(payload.getAuthorities()) || payload.getUsername().equals(originalProd.get().getUsername())) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("successfully updated");
        }
        return ResponseEntity.status(403).body("Access to the requested resource is forbidden.");
    }
}
