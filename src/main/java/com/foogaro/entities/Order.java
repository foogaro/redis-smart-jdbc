package com.foogaro.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderNumber", nullable = false)
    private Integer id;

    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Column(name = "requiredDate", nullable = false)
    private Date requiredDate;

    @Column(name = "shippedDate")
    private Date shippedDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customerNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

}