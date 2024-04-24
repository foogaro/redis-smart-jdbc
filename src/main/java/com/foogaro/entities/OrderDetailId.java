package com.foogaro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class OrderDetailId implements java.io.Serializable {
    private static final long serialVersionUID = -524834186211437990L;
    @Column(name = "orderNumber", nullable = false)
    private Integer orderNumber;

    @Column(name = "productCode", nullable = false)
    private Integer productCode;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.orderNumber, entity.orderNumber) &&
                Objects.equals(this.productCode, entity.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }

}