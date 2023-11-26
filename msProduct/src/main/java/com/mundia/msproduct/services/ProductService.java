package com.mundia.msproduct.services;

import com.mundia.msproduct.dto.ProductReq;
import com.mundia.msproduct.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse add(ProductReq productReq);
    public ProductResponse getProduct(Long id);

    public List<ProductResponse> allProducts();
    public ProductResponse updateProduct(Long id, ProductReq productReq);
    public void deleteProduct(Long id);
}
