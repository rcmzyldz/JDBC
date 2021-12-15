package be.intecbrussel.javajuni21.exams.exam06.exceptions;

import java.util.Arrays;

public class CategoryException extends RuntimeException {

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException notFound() {
        return new CategoryException("Category not found");
    }

    public CategoryException alreadyExists() {
        return new CategoryException("Category already exists");
    }

    public CategoryException requiredFields(String... fields) {
        return new CategoryException("Required fields: " + Arrays.toString(fields));
    }

    public CategoryException nullCategoryException() {
        return new CategoryException("Category cannot be null");
    }

}
