package com.zsanjay.wisdom.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zsanjay.wisdom.data.entity.Customer;
import com.zsanjay.wisdom.data.exception.NotFoundException;
import com.zsanjay.wisdom.data.exception.BadRequestException;
import com.zsanjay.wisdom.data.respository.CustomerRespository;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer createCustomer(@RequestBody Customer customer) {
    return this.customerRespository.save(customer);
  }

  @GetMapping("/{customerId}")
  public Customer getCustomer(@PathVariable UUID customerId) {
    Optional<Customer> customer = this.customerRespository.findById(customerId);
    if (!customer.isPresent()) {
      throw new NotFoundException("customer not found with id: " + customerId);
    }
    return customer.get();
  }

  @PutMapping("/{customerId}")
  public Customer updateCustomer(@PathVariable UUID customerId, @RequestBody Customer customer)
      throws BadRequestException {
    if (!customerId.equals(customer.getCustomerId())) {
      throw new BadRequestException("customerId on path must match body");
    }
    return this.customerRespository.save(customer);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteCustomer(@PathVariable UUID customerId) {
    this.customerRespository.deleteById(customerId);
  }

}
