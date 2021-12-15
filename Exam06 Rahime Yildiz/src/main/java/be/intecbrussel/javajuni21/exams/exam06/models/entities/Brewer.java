package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class Brewer implements Serializable, Comparable<Brewer> {

    private Integer id;
    private String name;
    private String address;
    private String postcode;
    private String city;
    private Integer turnover;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Brewer withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Brewer withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brewer withName(String name) {
        this.setName(name);
        return this;
    }

    public Brewer withoutName(String name) {
        this.setName(null);
        return this;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Brewer withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public Brewer withoutAddress(String address) {
        this.setAddress(null);
        return this;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Brewer withPostcode(String postcode) {
        this.setPostcode(postcode);
        return this;
    }

    public Brewer withoutPostcode(String postcode) {
        this.setPostcode(null);
        return this;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Brewer withCity(String city) {
        this.setCity(city);
        return this;
    }

    public Brewer withoutCity(String city) {
        this.setCity(null);
        return this;
    }


    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public Brewer withTurnover(Integer turnover) {
        this.setTurnover(turnover);
        return this;
    }

    public Brewer withoutTurnover(Integer turnover) {
        this.setTurnover(null);
        return this;
    }


    @Override
    public int compareTo(Brewer otherBrewer) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Brewer)) return false;
        Brewer otherBrewer = (Brewer) obj;
        return
                this.getId().equals(otherBrewer.getId())
                        && this.getName().equals(otherBrewer.getName())
                        && this.getAddress().equals(otherBrewer.getAddress())
                        && this.getPostcode().equals(otherBrewer.getPostcode())
                        && this.getCity().equals(otherBrewer.getCity())
                        && this.getTurnover().equals(otherBrewer.getTurnover())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getName()
                , this.getAddress()
                , this.getPostcode()
                , this.getCity()
                , this.getTurnover()
        );
    }

    @Override
    public String toString() {

        return "Brewer { " +
                this.getId() + ", " +
                this.getName() + ", " +
                this.getAddress() + ", " +
                this.getPostcode() + ", " +
                this.getCity() + ", " +
                this.getTurnover() + ", " +
                " } ";
    }
}
