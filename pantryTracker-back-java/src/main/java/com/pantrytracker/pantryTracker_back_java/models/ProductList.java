package com.pantrytracker.pantryTracker_back_java.models;

public class ProductList {

    private Long id;
    private Long userId; // FK a users.id

    private String productName;
    private String category;
    private Integer quantity;
    private String purchaseDate;

    public ProductList() {}

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public Integer getQuantity() { return quantity; }
    public String getPurchaseDate() { return purchaseDate; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }
}
