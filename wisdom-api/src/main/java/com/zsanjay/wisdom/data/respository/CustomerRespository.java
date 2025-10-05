package com.zsanjay.wisdom.data.respository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zsanjay.wisdom.data.entity.Customer;
import java.util.Optional;

public interface CustomerRespository extends JpaRepository<Customer, UUID> {
  Optional<Customer> findByEmail(String email);
}
