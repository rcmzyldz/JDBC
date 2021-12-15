package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable, Comparable<Category> {

    private Integer id;
    private String title;
    private String slug;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Category withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public Category withoutTitle(String title) {
        this.setTitle(null);
        return this;
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Category withSlug(String slug) {
        this.setSlug(slug);
        return this;
    }

    public Category withoutSlug(String slug) {
        this.setSlug(null);
        return this;
    }


    @Override
    public int compareTo(Category otherCategory) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category otherCategory = (Category) obj;
        return
                this.getId().equals(otherCategory.getId())
                        && this.getTitle().equals(otherCategory.getTitle())
                        && this.getSlug().equals(otherCategory.getSlug())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getTitle()
                , this.getSlug()
        );
    }

    @Override
    public String toString() {

        return "Category { " +
                this.getId() + ", " +
                this.getTitle() + ", " +
                this.getSlug() + ", " +
                " } ";
    }
}
