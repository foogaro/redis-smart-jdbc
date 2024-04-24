package com.foogaro.controllers;

import com.foogaro.entities.OrderDetails;
import com.foogaro.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService service;

    @GetMapping
    public List<OrderDetails> findAll() {
        return service.findAll();
    }

//    @GetMapping("/{orderNumber}")
//    public Optional<OrderDetails> findByOrderNumber(@PathVariable Integer orderNumber) {
//        return service.findByOrderNumber(orderNumber);
//    }

}
