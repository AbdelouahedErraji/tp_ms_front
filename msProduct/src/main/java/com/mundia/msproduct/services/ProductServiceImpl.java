package com.mundia.msproduct.services;

import com.mundia.msproduct.dto.ProductReq;
import com.mundia.msproduct.dto.ProductResponse;
import com.mundia.msproduct.entities.Product;
import com.mundia.msproduct.mappers.ProductMapper;
import com.mundia.msproduct.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductMapper productMapper;
    @Override
    public ProductResponse add(ProductReq productReq) {
        Product product = productMapper.fromProductReq(productReq);
        productRepo.save(product);
        return productMapper.fromProduct(product);
    }
    //---------------------------------------

    @Override
    public ProductResponse getProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            return productMapper.fromProduct(optionalProduct.get());
        }else{
            throw new EntityNotFoundException("Product with id="+id+" not found");
        }

    }

    @Override
    public List<ProductResponse> allProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductResponse> prods = new ArrayList<>();
        products.forEach(product -> {
            prods.add(productMapper.fromProduct(product));
        });
        return prods;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductReq productReq) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setCategory(productReq.getCategory() != null ? productReq.getCategory() : product.getCategory());
            product.setName(productReq.getName() != null ? productReq.getName() : product.getName());
            product.setPrice(productReq.getPrice() != null ? productReq.getPrice() : product.getPrice());
            productRepo.save(product);
            return productMapper.fromProduct(product);
        }else{
            throw new EntityNotFoundException("Product with id="+id+" not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            productRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException("Product with id="+id+" not found");
        }
    }
}
