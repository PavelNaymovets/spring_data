package com.naumovets.spring_data.services;

import com.naumovets.spring_data.entities.Product;
import com.naumovets.spring_data.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Transactional
    public void changeCost(Long id, Integer delta) {
        Product product = productRepository.findById(id).get();
        product.setCost(product.getCost() + delta);
    }

    public List<Product> findByCostBetween(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

    public List<Product> findByCostLessThan(Integer value) {
        return productRepository.findByCostLessThan(value);
    }

    public List<Product> findByCostGreaterThan(Integer value) {
        return productRepository.findByCostGreaterThan(value);
    }
}
