package com.usc.model;

public class Order {
    private Product product;
    private Buyer buyer;
    private Seller seller;
    private int quantity;

    public Order(Product product, Buyer buyer, Seller seller, int quantity) {
        this.product = product;
        this.buyer = buyer;
        this.seller = seller;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public Buyer getBuyer() { return buyer; }
    public Seller getSeller() { return seller; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Orden: " + quantity + " x " + product.getName() +
                " | Buyer: " + buyer.getName() +
                " | Seller: " + seller.getName();
    }
}
