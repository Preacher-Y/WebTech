package com.example.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.e_commerce.model.Product;
import com.example.e_commerce.service.ProductService;




@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> insertNewProduct(@RequestBody Product prod) {
        String saveProduct = productService.addNewProd(prod);

        if(saveProduct.equals("Product added successful")){
            return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(saveProduct, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProds() {
        List<Product> prods = productService.getAllProducts();
        if(prods.isEmpty()){
            return new ResponseEntity<>("There is not Product", HttpStatus.OK);
        } else{
            return new ResponseEntity<>(prods,HttpStatus.OK);
        }
    }
    
    @GetMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        Product prod = productService.getProductById(id);

        if(prod != null ){
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product with ID: "+id+", Not Found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/category/{category}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByCategory(@PathVariable String category){
        List<Product> products = productService.getAllProductsByCategory(category);
        
        if(products != null){
            return new ResponseEntity<>(products,HttpStatus.OK);
        } else{
            return new ResponseEntity<>("No Products Found for "+category, HttpStatus.OK);
        }
    }

    @GetMapping(value="/price-brand", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsByBrandAndPrice(@RequestParam Double price, @RequestParam String brand){
        List<Product> prods = productService.getProductByPriceAndBrand(price, brand);

        if(prods != null){
            return new ResponseEntity<>(prods, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("No Products Found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/brand/{brand}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByBrand(@PathVariable String brand){
        List<Product> products = productService.getAllProductsByBrand(brand);
        
        if(products.isEmpty()){
            return new ResponseEntity<>("No Products Found for "+brand, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    
    @GetMapping(value={"/search"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByKeyWord(@RequestParam String keyWord){
        List<Product> products = productService.getAllProductsByKeyWord(keyWord);
        
        if(products.isEmpty()){
            return new ResponseEntity<>("No Product Found", HttpStatus.OK);
        } else{
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }

    @GetMapping(value="/price-range", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByPriceRange(
            @RequestParam(defaultValue = "0") double min,
            @RequestParam(required = false) Double max
    ) {
        List<Product> products = productService.getAllProductsByMinAndMax(min,max);
        
        if(products.isEmpty()){
            return new ResponseEntity<>("There is not Product", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }

    @GetMapping(value="/in-stock", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsInStock() {
        List<Product> products = productService.getAllProductsInStock();
        
        if(products.isEmpty()){
            return new ResponseEntity<>("There is not Product", HttpStatus.OK);
        } else{
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }

    @PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody Product updateProd) {
        String res = productService.UpdateTheProduct(id,updateProd);

        if(res.equalsIgnoreCase("Failed to Update")){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PatchMapping(value="/{id}/stock", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProductStock(
            @PathVariable long id,
            @RequestBody int qty
    ) {
        String res = productService.UpdateTheStock(id,qty);

        if(res.equalsIgnoreCase("Stock quantity must be zero or greater")){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        if(res.equalsIgnoreCase("Failed to Update")){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        String res = productService.DeleteTheProduct(id);

        if(res.equalsIgnoreCase("Failed to Delete")){
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
