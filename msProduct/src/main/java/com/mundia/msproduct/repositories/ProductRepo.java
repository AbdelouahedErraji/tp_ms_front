package com.mundia.msproduct.repositories;

import com.mundia.msproduct.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
