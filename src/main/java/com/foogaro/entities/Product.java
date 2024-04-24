package com.foogaro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "productCode", nullable = false)
    private Integer id;

    @Column(name = "productName", nullable = false, length = 70)
    private String productName;

    @Column(name = "quantityInStock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "MSRP", nullable = false, precision = 10)
    private Double msrp;

    @Column(name = "buyPrice", nullable = false, precision = 10)
    private Double buyPrice;

    @Column(name = "productVendor", nullable = false, length = 50)
    private String productVendor;

    @Column(name = "productLine", nullable = false, length = 50)
    private String productLine;

    @Column(name = "productScale", nullable = false, length = 10)
    private String productScale;

    @Lob
    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getProductVendor() {
        return productVendor;
    }

    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getProductScale() {
        return productScale;
    }

    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}