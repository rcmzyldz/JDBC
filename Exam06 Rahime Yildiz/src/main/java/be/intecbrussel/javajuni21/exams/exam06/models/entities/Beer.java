package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class Beer implements Serializable, Comparable<Beer> {

    private Integer id;
    private String name;
    private Integer brewerId;
    private Integer categoryId;
    private Float price;
    private Integer stock;
    private Float alcohol;
    private Integer version;
    private String imageUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Beer withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Beer withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Beer withName(String name) {
        this.setName(name);
        return this;
    }

    public Beer withoutName(String name) {
        this.setName(null);
        return this;
    }


    public Integer getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(Integer brewerId) {
        this.brewerId = brewerId;
    }

    public Beer withBrewerId(Integer brewerId) {
        this.setBrewerId(brewerId);
        return this;
    }

    public Beer withoutBrewerId(Integer brewerId) {
        this.setBrewerId(null);
        return this;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Beer withCategoryId(Integer categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public Beer withoutCategoryId(Integer categoryId) {
        this.setCategoryId(null);
        return this;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Beer withPrice(Float price) {
        this.setPrice(price);
        return this;
    }

    public Beer withoutPrice(Float price) {
        this.setPrice(null);
        return this;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Beer withStock(Integer stock) {
        this.setStock(stock);
        return this;
    }

    public Beer withoutStock(Integer stock) {
        this.setStock(null);
        return this;
    }


    public Float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Float alcohol) {
        this.alcohol = alcohol;
    }

    public Beer withAlcohol(Float alcohol) {
        this.setAlcohol(alcohol);
        return this;
    }

    public Beer withoutAlcohol(Float alcohol) {
        this.setAlcohol(null);
        return this;
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Beer withVersion(Integer version) {
        this.setVersion(version);
        return this;
    }

    public Beer withoutVersion(Integer version) {
        this.setVersion(null);
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Beer withImageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public Beer withoutImageUrl(String imageUrl) {
        this.setImageUrl(null);
        return this;
    }


    @Override
    public int compareTo(Beer otherBeer) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Beer)) return false;
        Beer otherBeer = (Beer) obj;
        return
                this.getId().equals(otherBeer.getId())
                        && this.getName().equals(otherBeer.getName())
                        && this.getBrewerId().equals(otherBeer.getBrewerId())
                        && this.getCategoryId().equals(otherBeer.getCategoryId())
                        && this.getPrice().equals(otherBeer.getPrice())
                        && this.getStock().equals(otherBeer.getStock())
                        && this.getAlcohol().equals(otherBeer.getAlcohol())
                        && this.getVersion().equals(otherBeer.getVersion())
                        && this.getImageUrl().equals(otherBeer.getImageUrl())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getName()
                , this.getBrewerId()
                , this.getCategoryId()
                , this.getPrice()
                , this.getStock()
                , this.getAlcohol()
                , this.getVersion()
                , this.getImageUrl()
        );
    }

    @Override
    public String toString() {

        return "Beer { " +
                this.getId() + ", " +
                this.getName() + ", " +
                this.getBrewerId() + ", " +
                this.getCategoryId() + ", " +
                this.getPrice() + ", " +
                this.getStock() + ", " +
                this.getAlcohol() + ", " +
                this.getVersion() + ", " +
                this.getImageUrl() + ", " +
                " } ";
    }
}
