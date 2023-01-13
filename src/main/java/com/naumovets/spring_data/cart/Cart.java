package com.naumovets.spring_data.cart;

import com.naumovets.spring_data.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
@Scope("singleton")
public class Cart {
    private List<Product> list;

    @PostConstruct
    public void setList() {
        list = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return list;
    }

    public void addProduct(Product product) {
        list.add(product);
    }

    public void deleteById(Product product) {
        list.remove(product);
    }
}
