package com.example.mongodb.controllers.product;

import com.example.mongodb.controllers.product.dtos.SaveProductDto;
import com.example.mongodb.controllers.product.dtos.UpdateProductDto;
import com.example.mongodb.models.Product;
import com.example.mongodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //rota que lista todos os produtos
    @GetMapping()
    public ResponseEntity<List<Product>> returnProducts() {
        return new ResponseEntity<>(this.productService.returnProduct(), HttpStatus.OK);
    }

    //rota que traz lista de produtos pelo nome


    @GetMapping("/byName")
    public ResponseEntity<List<Product>> returnProductsByName(@RequestParam(required = false) String name) {
        if (name != null) {
            List<Product> productsByName = this.productService.returnProductsByName(name);
            return new ResponseEntity<>(productsByName, HttpStatus.OK);
        } else {
            List<Product> allProducts = this.productService.returnProduct();
            return new ResponseEntity<>(allProducts, HttpStatus.OK);
        }
    }


    @PostMapping("/save")
    public ResponseEntity saveProduct(@RequestBody SaveProductDto saveproductDto) {
        this.productService.save(saveproductDto.name(), saveproductDto.description());
        return ResponseEntity.ok("Product save");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) throws Exception {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProductById(@PathVariable String id, @RequestBody UpdateProductDto updateProductDto) throws Exception {
        productService.updateProduct(id, updateProductDto);
        return ResponseEntity.noContent().build();
    }


}



