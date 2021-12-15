package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class Order implements Serializable, Comparable<Order> {

    private Integer id;
    private Integer customerId;
    private Integer brewerId;
    private Timestamp registered;
    private Timestamp updated;
    private Float payment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Order withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Order withCustomerId(Integer customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public Order withoutCustomerId(Integer customerId) {
        this.setCustomerId(null);
        return this;
    }


    public Integer getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(Integer brewerId) {
        this.brewerId = brewerId;
    }

    public Order withBrewerId(Integer brewerId) {
        this.setBrewerId(brewerId);
        return this;
    }

    public Order withoutBrewerId(Integer brewerId) {
        this.setBrewerId(null);
        return this;
    }


    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    public Order withRegistered(Timestamp registered) {
        this.setRegistered(registered);
        return this;
    }

    public Order withoutRegistered(Timestamp registered) {
        this.setRegistered(null);
        return this;
    }


    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Order withUpdated(Timestamp updated) {
        this.setUpdated(updated);
        return this;
    }

    public Order withoutUpdated(Timestamp updated) {
        this.setUpdated(null);
        return this;
    }


    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Order withPayment(Float payment) {
        this.setPayment(payment);
        return this;
    }

    public Order withoutPayment(Float payment) {
        this.setPayment(null);
        return this;
    }


    @Override
    public int compareTo(Order otherOrder) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order otherOrder = (Order) obj;
        return
                this.getId().equals(otherOrder.getId())
                        && this.getCustomerId().equals(otherOrder.getCustomerId())
                        && this.getBrewerId().equals(otherOrder.getBrewerId())
                        && this.getRegistered().equals(otherOrder.getRegistered())
                        && this.getUpdated().equals(otherOrder.getUpdated())
                        && this.getPayment().equals(otherOrder.getPayment())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getCustomerId()
                , this.getBrewerId()
                , this.getRegistered()
                , this.getUpdated()
                , this.getPayment()
        );
    }

    @Override
    public String toString() {

        return "Order { " +
                this.getId() + ", " +
                this.getCustomerId() + ", " +
                this.getBrewerId() + ", " +
                this.getRegistered() + ", " +
                this.getUpdated() + ", " +
                this.getPayment() + ", " +
                " } ";
    }
}
