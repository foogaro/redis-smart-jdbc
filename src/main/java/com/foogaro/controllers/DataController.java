package com.foogaro.controllers;

import com.foogaro.services.LoadDataService;
import com.foogaro.services.QueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private LoadDataService loadDataService;
    @Autowired
    private QueryDataService queryDataService;

    @GetMapping("/load")
    public ResponseEntity load() {
        loadDataService.loadAllData();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/customers/{numberOfCustomers}")
    public ResponseEntity loadSomeCustomers(@PathVariable Integer numberOfCustomers) {
        loadDataService.loadSomeCustomers(numberOfCustomers);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/products/{numberOfProducts}")
    public ResponseEntity loadSomeProducts(@PathVariable Integer numberOfProducts) {
        loadDataService.loadSomeProducts(numberOfProducts);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/orders/{numberOfOrders}")
    public ResponseEntity loadSomeOrders(@PathVariable Integer numberOfOrders) {
        loadDataService.loadSomeOrders(numberOfOrders);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/order-details/{numberOfOrderDetails}")
    public ResponseEntity loadSomeOrderDetails(@PathVariable Integer numberOfOrderDetails) {
        loadDataService.loadSomeOrderDetails(numberOfOrderDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/query")
    public ResponseEntity query() {
        queryDataService.query();
        return ResponseEntity.ok().build();
    }

}
