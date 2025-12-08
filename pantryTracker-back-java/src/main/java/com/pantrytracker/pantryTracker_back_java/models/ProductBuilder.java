package com.pantrytracker.pantryTracker_back_java.models;

public class ProductBuilder {

    private Long id;
    private String userName;
    private String productName;
    private String category;
    private Integer quantity;
    private Float price;
    private String expirationDate;

    public ProductBuilder() {}

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public ProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public ProductBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ProductBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder price(Float price) {
        this.price = price;
        return this;
    }

    public ProductBuilder expirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Product build() {
        Product p = new Product();
        p.setId(this.id);
        p.setUserName(this.userName);
        p.setProductName(this.productName);
        p.setCategory(this.category);
        p.setQuantity(this.quantity);
        p.setPrice(this.price);
        p.setExpirationDate(this.expirationDate);
        return p;
    }
}

