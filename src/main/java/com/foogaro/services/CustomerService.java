package com.foogaro.services;

import com.foogaro.entities.Customer;
import com.foogaro.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findById(@PathVariable Integer id) {
        return repository.findById(id);
    }

}
