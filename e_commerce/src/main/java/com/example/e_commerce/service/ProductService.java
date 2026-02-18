package com.example.e_commerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public String addNewProd(Product prod){

        Optional<Product> existing = productRepo.findById(prod.getId());

        if (existing.isPresent()){
            return "Product with ID: "+prod.getId()+", Already exist";
        } else{
            productRepo.save(prod);
            return "Product added successful";
        }
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepo.findAll();

        return products;
    }

    public Product getProductById(Long id){
        Product prod = productRepo.findById(id).orElse(null);
        return prod;
    }

    public List<Product> getAllProductsByCategory(String category){
        List<Product> prods = productRepo.findByCategory(category);
        
        if(prods != null && !prods.isEmpty()){
            return prods;
        } else {
            return null;
        }
    }

    public List<Product> getAllProductsByBrand(String brand){
        List<Product> products = productRepo
                                    .findAll()
                                    .stream()
                                    .filter(el->el.getBrand().equalsIgnoreCase(brand))
                                    .collect(Collectors.toList());
        return products;
    }

    public List<Product> getProductByPriceAndBrand(Double price, String brand){
        List<Product> prods = productRepo.findByPriceAndBrand(price, brand);

        if(prods == null || prods.isEmpty()){
            return null;
        } else {
            return prods;
        }
    }

    public List<Product> getAllProductsByKeyWord(String keyWord){
        List<Product> products = productRepo
                                    .findAll()
                                    .stream()
                                    .filter(el ->
                                        el.getName().toLowerCase().contains(keyWord.toLowerCase()) || 
                                        el.getDescription().toLowerCase().contains(keyWord.toLowerCase()))
                                    .collect(Collectors.toList());
        return products;
    }

    public List<Product> getAllProductsByMinAndMax(Double min , Double max){
        List<Product> products = productRepo
                                    .findAll()
                                    .stream()
                                    .filter(p -> p.getPrice() >= min)
                                    .filter(p -> max == null || p.getPrice() <= max)
                                    .collect(Collectors.toList());
        return products;
    }

    public List<Product> getAllProductsInStock(){
        List<Product> products = productRepo
                                    .findAll()
                                    .stream().filter(el->el.getStockQuantity()>0).collect(Collectors.toList());
        return products;
    }

    public String UpdateTheProduct(long id,Product body){
        Product prod = productRepo.findById(id).orElse(null);
        
        if(prod != null){
            prod.setId(body.getId());
            prod.setName(body.getName());
            prod.setDescription(body.getDescription());
            prod.setPrice(body.getPrice());
            prod.setStockQuantity(body.getStockQuantity());
            prod.setCategory(body.getCategory());
            prod.setBrand(body.getBrand());

            return "Product Updated Successfuly";
        } else {
            return "Failed to Update";
        }
    }

    public String UpdateTheStock(long id, int qty){
        Product prod = productRepo.findById(id).orElse(null);
        
        if (qty < 0) {
            return "Stock quantity must be zero or greater";
        }

        if(prod != null){
            
            prod.setStockQuantity(qty);
            return "Product Updated Successfuly";
        } else {
            return "Failed to Update";
        }
    }

    public String DeleteTheProduct(long id){
        Product prod = productRepo.findById(id).orElse(null);

        if(prod != null){
            productRepo.delete(prod);
            return "Product Deleted Successfully";
        } else {
            return "Failed to Delete";
        }
    }
}
