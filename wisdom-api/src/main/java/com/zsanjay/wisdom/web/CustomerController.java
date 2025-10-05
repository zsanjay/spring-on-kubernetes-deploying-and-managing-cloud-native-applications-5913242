package com.zsanjay.wisdom.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zsanjay.wisdom.data.entity.Customer;
import com.zsanjay.wisdom.data.respository.CustomerRespository;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerController {

  private final CustomerRespository customerRespository;

  public CustomerController(CustomerRespository customerRespository) {
    this.customerRespository = customerRespository;
  }

  @GetMapping
  public Iterable<Customer> getAllCustomers(@RequestParam(required = false) String email) {
    if (StringUtils.isNotBlank(email)) {
      List<Customer> customers = new ArrayList<>();
      Optional<Customer> customer = this.customerRespository.findByEmail(email);
      if (customer.isPresent()) {
        customers.add(customer.get());
      }
      return customers;
    }
    return this.customerRespository.findAll();
  }
}
