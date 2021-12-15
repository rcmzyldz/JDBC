package be.intecbrussel.javajuni21.exams.exam06.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable, Comparable<Customer> {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String passcode;
    private java.sql.Date registryDate;
    private Integer active;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer withId(Integer id) {
        this.setId(id);
        return this;
    }

    public Customer withoutId(Integer id) {
        this.setId(null);
        return this;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Customer withFirstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public Customer withoutFirstName(String firstName) {
        this.setFirstName(null);
        return this;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer withLastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public Customer withoutLastName(String lastName) {
        this.setLastName(null);
        return this;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public Customer withoutEmail(String email) {
        this.setEmail(null);
        return this;
    }


    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Customer withPasscode(String passcode) {
        this.setPasscode(passcode);
        return this;
    }

    public Customer withoutPasscode(String passcode) {
        this.setPasscode(null);
        return this;
    }


    public java.sql.Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(java.sql.Date registryDate) {
        this.registryDate = registryDate;
    }

    public Customer withRegistryDate(java.sql.Date registryDate) {
        this.setRegistryDate(registryDate);
        return this;
    }

    public Customer withoutRegistryDate(java.sql.Date registryDate) {
        this.setRegistryDate(null);
        return this;
    }


    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Customer withActive(Integer active) {
        this.setActive(active);
        return this;
    }

    public Customer withoutActive(Integer active) {
        this.setActive(null);
        return this;
    }


    @Override
    public int compareTo(Customer otherCustomer) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer otherCustomer = (Customer) obj;
        return
                this.getId().equals(otherCustomer.getId())
                        && this.getFirstName().equals(otherCustomer.getFirstName())
                        && this.getLastName().equals(otherCustomer.getLastName())
                        && this.getEmail().equals(otherCustomer.getEmail())
                        && this.getPasscode().equals(otherCustomer.getPasscode())
                        && this.getRegistryDate().equals(otherCustomer.getRegistryDate())
                        && this.getActive().equals(otherCustomer.getActive())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getFirstName()
                , this.getLastName()
                , this.getEmail()
                , this.getPasscode()
                , this.getRegistryDate()
                , this.getActive()
        );
    }

    @Override
    public String toString() {

        return "Customer { " +
                this.getId() + ", " +
                this.getFirstName() + ", " +
                this.getLastName() + ", " +
                this.getEmail() + ", " +
                this.getPasscode() + ", " +
                this.getRegistryDate() + ", " +
                this.getActive() + ", " +
                " } ";
    }
}
