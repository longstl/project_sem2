package com.example.hoang.project_demo_3.entity;

public class Order {
    private int id;
    private int ProductId;
    private String ProductName;
    private int Quantity;
    private String Price;
    private String Discount;

    public Order() {
    }

    public Order(int productId, String productName, int quantity, String price) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
