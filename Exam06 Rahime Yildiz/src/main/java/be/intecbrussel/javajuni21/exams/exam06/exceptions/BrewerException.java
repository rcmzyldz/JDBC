package be.intecbrussel.javajuni21.exams.exam06.exceptions;

import java.util.Arrays;

public class BrewerException extends RuntimeException {

    public BrewerException(String message) {
        super(message);
    }

    public BrewerException notFound() {
        return new BrewerException("Brewer not found");
    }

    public BrewerException alreadyExists() {
        return new BrewerException("Brewer already exists");
    }

    public BrewerException requiredFields(String... fields) {
        return new BrewerException("Required fields: " + Arrays.toString(fields));
    }

    public BrewerException nullBrewerException() {
        return new BrewerException("Brewer cannot be null");
    }

}
