package com.foogaro.services;

import com.foogaro.entities.Order;
import com.foogaro.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(@PathVariable Integer id) {
        return repository.findById(id);
    }

}
