package com.naumovets.spring_data.controllers;

import com.naumovets.spring_data.entities.Product;
import com.naumovets.spring_data.exceptions.ResourceNotFoundException;
import com.naumovets.spring_data.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long id, @RequestParam Integer delta) {
        productService.changeCost(id, delta);
    }

    @GetMapping("/products/cost_less_than")
    public List<Product> findCostLessThan(@RequestParam(defaultValue = "0") Integer value) {
        return productService.findByCostLessThan(value);
    }

    @GetMapping("/products/cost_greater_than")
    public List<Product> findCostGreaterThan(@RequestParam(defaultValue = "0") Integer value) {
        return productService.findByCostGreaterThan(value);
    }

    @GetMapping("/products/cost_between")
    public List<Product> findCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "1000") Integer max) {
        return productService.findByCostBetween(min, max);
    }
}
