package com.zsanjay.wisdom.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zsanjay.wisdom.data.entity.Product;
import com.zsanjay.wisdom.data.exception.NotFoundException;
import com.zsanjay.wisdom.data.respository.ProductRepository;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("products")
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public Iterable<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProduct(@PathVariable UUID productId) throws Exception {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new NotFoundException("Product Not Found Exception"));
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) throws Exception {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new NotFoundException("Product Not Found Exception"));
    productRepository.delete(product);
    return ResponseEntity.noContent().build();
  }
}
