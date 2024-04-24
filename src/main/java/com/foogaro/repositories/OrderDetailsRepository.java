package com.foogaro.repositories;

import com.foogaro.entities.OrderDetailId;
import com.foogaro.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailId> {

//    public Optional<OrderDetails> findByOrderNumber(@PathVariable Integer orderNumber);

}
