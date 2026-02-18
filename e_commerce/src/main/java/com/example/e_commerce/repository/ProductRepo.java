package com.example.e_commerce.repository;

import com.example.e_commerce.model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import java.util.List;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    List<Product> findByPriceAndBrand(Double price, String brand);
}
