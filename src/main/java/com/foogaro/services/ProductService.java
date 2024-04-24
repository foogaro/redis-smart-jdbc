package com.foogaro.services;

import com.foogaro.entities.Product;
import com.foogaro.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(@PathVariable Integer id) {
        return repository.findById(id);
    }

}
