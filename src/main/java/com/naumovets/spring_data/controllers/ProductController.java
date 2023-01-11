package com.naumovets.spring_data.controllers;

import com.naumovets.spring_data.dto.ProductDto;
import com.naumovets.spring_data.entities.Product;
import com.naumovets.spring_data.exceptions.ResourceNotFoundException;
import com.naumovets.spring_data.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAll(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        System.out.println(page + " " + minPrice + " " + maxPrice + " " + namePart);
        if(page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, namePart, page).map(ProductDto::new);
    }

    @PostMapping
    public ProductDto addNewProduct(@RequestBody Product product) {
        return new ProductDto(productService.addNewProduct(product));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findById(id).map(ProductDto::new).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public void changeCost(@RequestParam Long id, @RequestParam Integer delta) {
        productService.changeCost(id, delta);
    }
}
