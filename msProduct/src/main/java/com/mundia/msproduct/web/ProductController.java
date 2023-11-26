package com.mundia.msproduct.web;

import com.mundia.msproduct.dto.ProductReq;
import com.mundia.msproduct.dto.ProductResponse;
import com.mundia.msproduct.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ProductResponse addprod(@RequestBody ProductReq productReq){
        return productService.add(productReq);
    }
    //----------------------------
    @GetMapping("/{id}")
    public ProductResponse getprod(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<ProductResponse> allProducts() {
        return productService.allProducts();
    }
    @PutMapping("/update/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductReq productReq) {
        return productService.updateProduct(id, productReq);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
