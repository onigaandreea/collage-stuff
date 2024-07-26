package com.example;

import java.util.Objects;

public class Order implements Comparable<Order>{

    private int id;
    private int price;
    private int quantity;

    public Order(int id, int price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && price == order.price && quantity == order.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(Order order) {
        if(this.price > order.price)
            return 1; //the current object is greater than the one we compare with
        else if (this.price == order.price) {
            return 0; //the objects are equal considering only the price
        }
        return -1; //the current object has a lower price than the one we compare with
    }
}
