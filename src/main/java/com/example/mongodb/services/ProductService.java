package com.example.mongodb.services;

import com.example.mongodb.controllers.product.dtos.DeleteProductDto;
import com.example.mongodb.controllers.product.dtos.UpdateProductDto;
import com.example.mongodb.models.Product;

import java.util.List;

public interface ProductService {
    void save(String nome, String description);
    List<Product> returnProduct();

    void delete(String id) throws Exception;


    void updateProduct(String id, UpdateProductDto updateProductDto) throws Exception;


    List<Product> returnProductsByName(String name);


}

