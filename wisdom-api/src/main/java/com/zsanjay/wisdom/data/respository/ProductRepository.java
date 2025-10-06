package com.zsanjay.wisdom.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zsanjay.wisdom.data.entity.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findByName(String name);
}
