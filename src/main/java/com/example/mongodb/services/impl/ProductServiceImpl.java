package com.example.mongodb.services.impl;

import com.example.mongodb.controllers.product.dtos.DeleteProductDto;
import com.example.mongodb.controllers.product.dtos.UpdateProductDto;
import com.example.mongodb.models.Product;
import com.example.mongodb.repositories.ProductRepository;
import com.example.mongodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(String nome, String description) {
        Product product = new Product(nome, description);
        this.productRepository.save(product);
    }


   /* @Override
    public void delete(String id,String nome, String description) throws Exception {
        if (!this.productRepository.existsById(id)) {
            throw new Exception("Produto não encontrado");
        }
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        Product product = optionalProduct.get();
        product.setId();
        this.productRepository.delete(product);
    }*/

    //refatorando metodo delete
    public void delete(String id) throws Exception{
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        //verifica se o produto existe na base de dados antes de tentar excluir
        if (!optionalProduct.isPresent()) {
            throw new Exception("Produto não encontrado");
        }

        Product product = optionalProduct.get();
        this.productRepository.delete(optionalProduct.get());

    }


    @Override
    public void updateProduct(String id, UpdateProductDto updateProductDto) throws Exception {
        if (!this.productRepository.existsById(id)) {
            throw new Exception("Produto não encontrado");
        }

        Optional<Product> optionalProduct = this.productRepository.findById(id);

        Product product = optionalProduct.get();
        product.setName(updateProductDto.name());
        product.setDescription(updateProductDto.description());
        this.productRepository.save(product);
    }

    //implementacao da lista que retorna pelo nome
    @Override
    public List<Product> returnProductsByName(String name) {
        return this.productRepository.findByName(name);
    }


    @Override
    public List<Product> returnProduct() {
        return this.productRepository.findAll();
    }



}