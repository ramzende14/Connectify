package com.resumemaker.filtersort.Controller;

import com.resumemaker.filtersort.Entity.Product;
import com.resumemaker.filtersort.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String Category,
            @RequestParam(required = false)BigDecimal minPrice,
            @RequestParam(required = false)BigDecimal maxPrice,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc")String sortDirection
            ){
        return productService.getFilteredProduct(name,Category,minPrice,maxPrice,sortBy,sortDirection);
    }
    @PostMapping
    public ResponseEntity<Product>addProduct(@RequestBody Product product){
        return  ResponseEntity.ok(productService.addProduct(product));
    }

}
