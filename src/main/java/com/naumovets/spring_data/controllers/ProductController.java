package com.naumovets.spring_data.controllers;

import com.naumovets.spring_data.entities.Product;
import com.naumovets.spring_data.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Long id, @RequestParam Integer delta) {
        productService.changeCost(id, delta);
    }

    @GetMapping("products/cost_less_than")
    public List<Product> findCostLessThan(@RequestParam(defaultValue = "0") Integer value) {
        return productService.findByCostLessThan(value);
    }

    @GetMapping("products/cost_greater_than")
    public List<Product> findCostGreaterThan(@RequestParam(defaultValue = "0") Integer value) {
        return productService.findByCostGreaterThan(value);
    }

    @GetMapping("products/cost_between")
    public List<Product> findCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "1000") Integer max) {
        return productService.findByCostBetween(min, max);
    }

}
