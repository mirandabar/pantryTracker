package com.pantrytracker.pantryTracker_back_java.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK → products.user_id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String productName;
    private String category;
    private Integer quantity;
    private Float price;
    // formato YYYY-MM-DD
    private String expirationDate;
    // formato YYYY-MM-DD
    private String purchaseDate;

    public Product() {}

    /**
     * Constructor usado por Jackson para mapear un objeto JSON como:
     * {"product":"manzana","category":"Frutas y Verduras","quantity":"1","price":"1","expirationDate":"2026-12-10"}
     */
    @JsonCreator
    public Product(
        @JsonProperty("product") String productName,
        @JsonProperty("category") String category,
        @JsonProperty("quantity") Integer quantity,
        @JsonProperty("price") Float price,
        @JsonProperty("expirationDate") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") String expirationDate
    ) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.expirationDate = expirationDate;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public Integer getQuantity() { return quantity; }
    public Float getPrice() { return price; }
    public String getExpirationDate() { return expirationDate; }
    public String getPurchaseDate() { return purchaseDate; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Float price) { this.price = price; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

}
