package be.intecbrussel.javajuni21.exams.exam06.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderRepositories implements TemplateRepository{


    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public int create(Object o) throws RuntimeException {
        return 0;
    }

    @Override
    public Object read(long id) throws RuntimeException {
        return null;
    }

    @Override
    public List read(Object example) throws RuntimeException {
        return null;
    }

    @Override
    public List read() throws RuntimeException {
        return null;
    }

    @Override
    public int update(long id, Object existing) throws RuntimeException {
        return 0;
    }

    @Override
    public int delete(long id) throws RuntimeException {
        return 0;
    }


}
