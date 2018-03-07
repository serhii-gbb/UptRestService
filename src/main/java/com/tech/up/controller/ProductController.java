package com.tech.up.controller;

import com.tech.up.entity.Product;
import com.tech.up.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    public static final int DEFAULT_DB_ID = 0;

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product productById = productService.getProductById(id);

        return new ResponseEntity<>(productById, HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public String addProduct(@RequestBody Product product) {
        product.setId(DEFAULT_DB_ID);
        productService.save(product);

        return "Product saved successfully";
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public String updateProduct(@RequestBody Product product) {
        productService.update(product);

        return "Product updated successfully";
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);

        return "Product deleted successfully";
    }
}
