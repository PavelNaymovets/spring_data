package com.naumovets.spring_data.converters;

import com.naumovets.spring_data.dto.ProductDto;
import com.naumovets.spring_data.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public static Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public static ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

}
