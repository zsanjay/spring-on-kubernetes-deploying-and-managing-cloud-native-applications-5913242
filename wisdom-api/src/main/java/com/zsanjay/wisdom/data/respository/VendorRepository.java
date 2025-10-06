package com.zsanjay.wisdom.data.respository;

import com.zsanjay.wisdom.data.entity.Vendor;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
  Optional<Vendor> findByEmail(String email);
}
