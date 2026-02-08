package com.example.question4_e_commerce_api.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.question4_e_commerce_api.Model.Product;


@RestController()
@RequestMapping("/api/products")
public class EcommerceAPI {
    List<Product> products = new ArrayList<>();

    public EcommerceAPI(){
        products.add(new Product(
            1L, "iPhone 14", "Apple smartphone with A15 Bionic chip", 999.99, "Electronics", 25, "Apple")
        );
        products.add(new Product(
            2L, "Galaxy S23", "Samsung flagship Android phone", 899.50, "Electronics", 30, "Samsung")
        );
        products.add(new Product(
            3L, "ThinkPad X1 Carbon", "Lightweight business laptop", 1499.00, "Computers", 12, "Lenovo")
        );
        products.add(new Product(
            4L, "Air Jordan 1", "Classic high-top basketball sneakers", 180.00, "Footwear", 50, "Nike")
        );
        products.add(new Product(
            5L, "Sony WH-1000XM5", "Noise-cancelling wireless headphones", 349.99, "Audio", 40, "Sony")
        );
        products.add(new Product(
            6L, "Logitech MX Master 3S", "Advanced wireless mouse for productivity", 99.99, "Accessories", 60, "Logitech")
        );
        products.add(new Product(
            7L, "Canon EOS R8", "Mirrorless full-frame camera", 1299.00, "Cameras", 10, "Canon")
        );
        products.add(new Product(
            8L, "IKEA Markus Chair", "Ergonomic office chair", 249.00, "Furniture", 20, "IKEA")
        );
        products.add(new Product(
            9L, "Adidas Ultraboost", "Running shoes with boost cushioning", 190.00, "Footwear", 45, "Adidas")
        );
        products.add(new Product(
            10L, "Dell UltraSharp U2723QE", "27-inch 4K IPS monitor", 679.99, "Monitors", 15, "Dell")
        );
    }

    @GetMapping()
    public List<Product> getAllProducts(
        @RequestParam(required = false, defaultValue="0") int page, 
        @RequestParam(required = false, defaultValue="0") int limit 
    ){
        if (limit == 0){
            limit = products.size();
        }

        int start = page*limit;

        if (start >= products.size()){
            return List.of();
        }

        int end = Math.min(start+limit, products.size());
        return products.subList(start,end);
    }

    @GetMapping("/{id}")
    public Product getProductsById(@PathVariable long id){
        return products.stream().filter(el->el.getId() == id).findFirst()
                    .orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Prodcut with ID: "+id+", Not Found !"
                        )
                    );
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){
        return products.stream().filter(el->el.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand){
        return products.stream().filter(el->el.getBrand().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<Product> getMethodName(@RequestParam String keyword) {
        return products.stream()
                    .filter(el->
                        el.getName().toLowerCase().contains(keyword.toLowerCase())
                        || el.getDescription().toLowerCase().contains(keyword.toLowerCase())
                    )
                    .collect(Collectors.toList());
    }
    
    @GetMapping("/price-range")
    public List<Product> getProductByPriceRange(
            @RequestParam(defaultValue = "0") double min,
            @RequestParam(required = false) Double max
    ) {
        return products.stream()
                .filter(p -> p.getPrice() >= min)
                .filter(p -> max == null || p.getPrice() <= max)
                .collect(Collectors.toList());
    }


    @GetMapping("/in-stock")
    public List<Product> getProductsInStock() {
        return products.stream().filter(el->el.getStockQuantity()>0).collect(Collectors.toList()) ;
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product newProd){
        products.add(newProd);
        return newProd;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product updateProd) {
        Product prod = products.stream().filter(el->el.getId() == id).findFirst().orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Prodcut with ID: "+id+", Not Found !"
                        )
                    );
        prod.setId(updateProd.getId());
        prod.setName(updateProd.getName());
        prod.setDescription(updateProd.getDescription());
        prod.setPrice(updateProd.getPrice());
        prod.setStockQuantity(updateProd.getStockQuantity());
        prod.setCategory(updateProd.getCategory());
        prod.setBrand(updateProd.getBrand());
        return prod;
    }

    @PatchMapping("/{id}/stock")
    public Product updateProductStock(
            @PathVariable long id,
            @RequestBody int qty
    ) {
        if (qty < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Stock quantity must be zero or greater"
            );
        }

        Product prod = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product with ID " + id + " not found"
                    )
                );

        prod.setStockQuantity(qty);
        return prod;
    }

    
    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable long id){
        Product prod = products.stream().filter(el->el.getId() == id).findFirst().orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Prodcut with ID: "+id+", Not Found !"
                        )
                    );
        products.remove(prod);
        return Map.of(
            "message", "Product deleted successfully",
            "Product_Id", String.valueOf(id)
        );;
    }
}
