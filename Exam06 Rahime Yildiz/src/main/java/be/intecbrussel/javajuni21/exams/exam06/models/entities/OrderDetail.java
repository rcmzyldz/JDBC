package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetail implements Serializable, Comparable<OrderDetail> {

    private Integer id;
    private Integer orderId;
    private Integer quantity;
    private Float discount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDetail withId(Integer id) {
        this.setId(id);
        return this;
    }

    public OrderDetail withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderDetail withOrderId(Integer orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public OrderDetail withoutOrderId(Integer orderId) {
        this.setOrderId(null);
        return this;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderDetail withQuantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public OrderDetail withoutQuantity(Integer quantity) {
        this.setQuantity(null);
        return this;
    }


    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public OrderDetail withDiscount(Float discount) {
        this.setDiscount(discount);
        return this;
    }

    public OrderDetail withoutDiscount(Float discount) {
        this.setDiscount(null);
        return this;
    }


    @Override
    public int compareTo(OrderDetail otherOrderDetail) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OrderDetail)) return false;
        OrderDetail otherOrderDetail = (OrderDetail) obj;
        return
                this.getId().equals(otherOrderDetail.getId())
                        && this.getOrderId().equals(otherOrderDetail.getOrderId())
                        && this.getQuantity().equals(otherOrderDetail.getQuantity())
                        && this.getDiscount().equals(otherOrderDetail.getDiscount())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getOrderId()
                , this.getQuantity()
                , this.getDiscount()
        );
    }

    @Override
    public String toString() {

        return "OrderDetail { " +
                this.getId() + ", " +
                this.getOrderId() + ", " +
                this.getQuantity() + ", " +
                this.getDiscount() + ", " +
                " } ";
    }
}
