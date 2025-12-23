package com.pantrytracker.pantryTracker_back_java.models;

public class ProductBuilder {

    private Long id;
    private Long userId;
    private String productName;
    private String category;
    private Integer quantity;
    private Float price;
    private String expirationDate;
    private String purchaseDate;

    public ProductBuilder() {}

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public ProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder user(Long userId) {
        this.userId = userId;
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

    public ProductBuilder purchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Product build() {
        Product p = new Product();
        p.setId(this.id);
        p.setUserId(this.userId);
        p.setProductName(this.productName);
        p.setCategory(this.category);
        p.setQuantity(this.quantity);
        p.setPrice(this.price);
        p.setExpirationDate(this.expirationDate);
        p.setPurchaseDate(this.purchaseDate);
        return p;
    }
}


