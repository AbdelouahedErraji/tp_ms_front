package com.mundia.msproduct.mappers;

import com.mundia.msproduct.dto.ProductReq;
import com.mundia.msproduct.dto.ProductResponse;
import com.mundia.msproduct.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponse fromProduct(Product product){
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }
    //--------------------------------------
    public Product fromProductReq(ProductReq productReq){
        Product product = new Product();
        BeanUtils.copyProperties(productReq,product);
        return product;
    }
}
