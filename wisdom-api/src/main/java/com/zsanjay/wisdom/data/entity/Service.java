package com.zsanjay.wisdom.data.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "services")
@Data
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID serviceId;
  private String name;
  private String price;
}
