package be.intecbrussel.javajuni21.exams.exam06.exceptions;

import java.util.Arrays;

public class BeerException extends RuntimeException {

    public BeerException(String message) {
        super(message);
    }

    public BeerException notFound() {
        return new BeerException("Beer not found");
    }

    public BeerException alreadyExists() {
        return new BeerException("Beer already exists");
    }

    public BeerException requiredFields(String... fields) {
        return new BeerException("Required fields: " + Arrays.toString(fields));
    }

    public BeerException nullBeerException() {
        return new BeerException("Beer cannot be null");
    }

}
