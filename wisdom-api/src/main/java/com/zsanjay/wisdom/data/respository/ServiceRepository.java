package com.zsanjay.wisdom.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zsanjay.wisdom.data.entity.Service;

import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
  Optional<Service> findByName(String name);
}
