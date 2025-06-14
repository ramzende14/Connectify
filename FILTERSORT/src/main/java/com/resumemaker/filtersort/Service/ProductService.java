package com.resumemaker.filtersort.Service;

import com.resumemaker.filtersort.Entity.Product;
import com.resumemaker.filtersort.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getFilteredProduct(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, String sortBy, String sortDirection) {
        Sort sort = Sort.by(getSortDirection(sortDirection), sortBy);
        List<Product> products = productRepository.findAll(sort);

        return products.stream()
                .filter(product -> (name == null || product.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(product -> (category == null || product.getCategory().equalsIgnoreCase(category)))
                .filter(product -> (minPrice == null || product.getPrice().compareTo(minPrice) >= 0))
                .filter(product -> (maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0))
                .toList();
    }

    private Sort.Direction getSortDirection(String direction) {
        return "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
