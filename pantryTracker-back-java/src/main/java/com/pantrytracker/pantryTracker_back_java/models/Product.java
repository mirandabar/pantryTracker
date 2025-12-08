package com.pantrytracker.pantryTracker_back_java.models;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String productName;
    private String category;
    private Integer quantity;
    private Float price;
    private String expirationDate; // formato YYYY-MM-DD

    public Product() {}

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public Integer getQuantity() { return quantity; }
    public Float getPrice() { return price; }
    public String getExpirationDate() { return expirationDate; }

    public void setId(Long id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Float price) { this.price = price; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

}
