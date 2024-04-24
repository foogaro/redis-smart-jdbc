package com.foogaro.services;

import com.foogaro.entities.OrderDetails;
import com.foogaro.repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository repository;

    @GetMapping
    public List<OrderDetails> findAll() {
        return repository.findAll();
    }

//    @GetMapping("/{orderNumber}")
//    public Optional<OrderDetails> findByOrderNumber(@PathVariable Integer orderNumber) {
//        return repository.findByOrderNumber(orderNumber);
//    }

}
