package be.intecbrussel.javajuni21.exams.exam06.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TemplateRepository<T> {

    Connection getConnection() throws SQLException;

    int create(T t) throws RuntimeException;

    T read(long id) throws RuntimeException;

    List<T> read(T example) throws RuntimeException;

    List<T> read() throws RuntimeException;

    int update(long id, T existing) throws RuntimeException;

    public int delete(long id) throws RuntimeException;
}
