package com.foogaro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    @EmbeddedId
    private OrderDetailId id;

    @MapsId("orderNumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderNumber", nullable = false)
    private Order orderNumber;

    @MapsId("productCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productCode", nullable = false)
    private Product productCode;

    @Column(name = "quantityOrdered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "orderLineNumber", nullable = false)
    private Integer orderLineNumber;

    @Column(name = "priceEach", nullable = false, precision = 10)
    private Double priceEach;

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public Order getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Order orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Product getProductCode() {
        return productCode;
    }

    public void setProductCode(Product productCode) {
        this.productCode = productCode;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public Double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Double priceEach) {
        this.priceEach = priceEach;
    }

}