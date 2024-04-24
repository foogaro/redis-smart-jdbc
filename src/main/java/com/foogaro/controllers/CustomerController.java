package com.foogaro.controllers;

import com.foogaro.entities.Customer;
import com.foogaro.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

}
