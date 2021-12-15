package be.intecbrussel.javajuni21.exams.exam06.exceptions;

import java.util.Arrays;

public class CustomerException extends RuntimeException {

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException notFound() {
        return new CustomerException("Customer not found");
    }

    public CustomerException alreadyExists() {
        return new CustomerException("Customer already exists");
    }

    public CustomerException requiredFields(String... fields) {
        return new CustomerException("Required fields: " + Arrays.toString(fields));
    }

    public CustomerException nullCustomerException() {
        return new CustomerException("Customer cannot be null");
    }

}
