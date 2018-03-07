package com.tech.up.dao;

import com.tech.up.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product, Integer> {

}
