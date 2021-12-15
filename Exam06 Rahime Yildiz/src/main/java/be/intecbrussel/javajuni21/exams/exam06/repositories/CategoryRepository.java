package be.intecbrussel.javajuni21.exams.exam06.repositories;

import be.intecbrussel.javajuni21.exams.exam06.models.entities.Category;
import java.sql.*;
import java.util.*;
import be.intecbrussel.javajuni21.exams.exam06.exceptions.*;

public class CategoryRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory.getInstance().getConnection();
            return conn;
    }

    public int create(Category newCategory) throws CategoryException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier ..

             Statement createStatement = getConnection().createStatement();
            String query = "INSERT INTO category " +
                    "(title, slug) " +
                    "VALUES " +
                    "('" + newCategory.getTitle() + "  ', " +
                    "'" + newCategory.getSlug() + "')";

            noOfRecordsEffected = createStatement.executeUpdate(query);


        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }
        return noOfRecordsEffected;
    }

    public Category read(long id) throws CategoryException {

        Category result = new Category();

        try {
            
            // TODO: voeg je code hier ..
              Statement selectStatement = getConnection().createStatement();

            String query = "SELECT id FROM category WHERE id = ? ";
            ResultSet resultOfReading = selectStatement.executeQuery(query);

            while (resultOfReading.next()) {


                result.setId(resultOfReading.getInt("id"));
                result.setTitle(resultOfReading.getString("title"));
                result.setSlug(resultOfReading.getString("slug"));

            }


        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }

        return result;

    }

    public List<Category> read(Category example) throws CategoryException {

	List<Category> categoryList = new ArrayList<>();

        try {
            
            // TODO: voeg je code hier ..

               String query = "SELECT  id, title, slug" +
                    "FROM category " +
                    "WHERE id = ? or title = ? or slug = ? ";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setInt(1, example.getId());
            selectStatement.setString(2, example.getTitle());
            selectStatement.setString(3, example.getSlug());

            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setTitle(result.getString("title"));
                category.setSlug(result.getString("slug"));

                categoryList.add(category);
            }


        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }

        return categoryList;

    }

    public List<Category> read() throws CategoryException {
    
    	List<Category> categoryList = new ArrayList<>();

        try {

            // TODO: voeg je code hier..

            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT id, title, slug FROM category";
            ResultSet result = selectStatement.executeQuery(query);

            while (result.next()) {

                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setTitle(result.getString("title"));
                category.setSlug(result.getString("slug"));

                categoryList.add(category);
            }

        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }

        return categoryList;

    }

    public int update(long id, Category existingCategory) throws CategoryException {

        if(id < 0) {
            throw new CategoryException("Category ID is required.").requiredFields("id");
        }

        if (existingCategory == null) {
            throw new CategoryException("Category is required.").nullCategoryException();
        }

        int noOfRecordsEffected = 0;

        try {
            
            // TODO: voeg je code hier ..

            String preparedQuery = "UPDATE  category SET title = ?, slug = ? WHERE id = ?";
            PreparedStatement updateStatement = getConnection().prepareStatement(preparedQuery);
            updateStatement.setString(1, existingCategory.getTitle());
            updateStatement.setString(2, existingCategory.getSlug());
            noOfRecordsEffected = updateStatement.executeUpdate();


        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }

        return noOfRecordsEffected;

    }

    public int delete(int id) throws CategoryException {

        int noOfRecordsEffected = 0;

        try {
            
            // TODO: voeg je code hier ..

            String query = "DELETE FROM category WHERE id= ?";
            PreparedStatement deleteStatement = getConnection().prepareStatement(query);
            deleteStatement.setInt(1, id);
            noOfRecordsEffected = deleteStatement.executeUpdate();

        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                     throw ex;
            }

        }

        return noOfRecordsEffected;
    }

    public List<Category> search(String keyword) throws CategoryException {

        List<Category> categoryList = new ArrayList<>();

        try {

            // TODO: voeg je code hier ..
           Statement statement = connection.createStatement();

            String query = "SELECT  id, title, slug" +
                    "FROM category " +
                    "WHERE title like ? or slug like ? ";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setString(1, keyword);
            selectStatement.setString(2, keyword);
            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setTitle(result.getString("title"));
                category.setSlug(result.getString("slug"));

                categoryList.add(category);
            }


        } catch (SQLException sqlException) {
            CategoryException ex = new CategoryException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                CategoryException ex = new CategoryException(exception.getMessage());
                throw ex;
            }

        }

        return categoryList;

    }


}
