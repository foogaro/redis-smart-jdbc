package com.foogaro.controllers;

import com.foogaro.entities.Order;
import com.foogaro.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

}
