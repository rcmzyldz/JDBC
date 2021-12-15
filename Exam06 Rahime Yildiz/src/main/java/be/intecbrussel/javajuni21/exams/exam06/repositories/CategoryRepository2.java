package be.intecbrussel.javajuni21.exams.exam06.repositories;

import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class CategoryRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( CategoryEntity record ) throws CategoryException {

        int rowsEffected = 0;

        try {
            String query = "insert into category ( title, slug ) values ( ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getTitle());
            statement.setString(2, record.getSlug());

             rowsEffected = statement.executeUpdate();

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

        return rowsEffected;
    }

    public CategoryEntity read( Integer id ) throws CategoryException {

        CategoryEntity item = new CategoryEntity();

        try {
            String query = "select id, title, slug from category where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setTitle( results.getString("title") );
                item.setSlug( results.getString("slug") );
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

        return item;
    }

    public List<CategoryEntity> read( CategoryEntity record ) throws CategoryException {

        final List<CategoryEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, title, slug from category where title = ? OR slug = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, record.getTitle() );
            statement.setString( 2, record.getSlug() );

            results = statement.executeQuery();
            while(results.next()){
            CategoryEntity item = new CategoryEntity();
                item.setId( results.getInt("id") );
                item.setTitle( results.getString("title") );
                item.setSlug( results.getString("slug") );

                itemList.add(item);
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

        return itemList;
    }

    public List<CategoryEntity> read() throws CategoryException {

        final List<CategoryEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, title, slug from category ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                CategoryEntity item = new CategoryEntity();
                item.setId( results.getInt("id") );
                item.setTitle( results.getString("title") );
                item.setSlug( results.getString("slug") );

                itemList.add(item);
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

        return itemList;
    }

    public int update( Integer id, CategoryEntity record ) throws CategoryException {

        if( id < 0 ) {
            throw new CategoryException( "Category ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new CategoryException( "Category is required." ).nullCategoryException();
        }

        int rowsEffected = 0;

        try {
            String query = "update category set   title  = ?,   slug  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getTitle());
            statement.setString(2, record.getSlug());
            statement.setInt( 3, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
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

        return rowsEffected;
    }

    public int delete( Integer id ) throws CategoryException {

        int rowsEffected = 0;

        try {
            String query = "delete from category where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
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

        return rowsEffected;
    }

    public int updateTitle( Integer id, String title ) throws CategoryException {

        if( id < 0 ) {
            throw new CategoryException( "Category ID is required." ).requiredFields("id");
        }

        if ( title == null ) {
            throw new CategoryException( "title is required." ).nullCategoryException();
        }

        int rowsEffected = 0;

        try {
            String query = "update category set  title  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, title );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateSlug( Integer id, String slug ) throws CategoryException {

        if( id < 0 ) {
            throw new CategoryException( "Category ID is required." ).requiredFields("id");
        }

        if ( slug == null ) {
            throw new CategoryException( "slug is required." ).nullCategoryException();
        }

        int rowsEffected = 0;

        try {
            String query = "update category set  slug  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, slug );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public List<CategoryEntity> searchByTitle( String title ) throws CategoryException {

        final List<CategoryEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, title, slug from category where title LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, title );

            results = statement.executeQuery();
            while( results.next() ) {
            CategoryEntity item = new CategoryEntity();
                item.setId( results.getInt("id") );
                item.setTitle( results.getString("title") );
                item.setSlug( results.getString("slug") );

                itemList.add(item);
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

        return itemList;
    }

    public List<CategoryEntity> searchBySlug( String slug ) throws CategoryException {

        final List<CategoryEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, title, slug from category where slug LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, slug );

            results = statement.executeQuery();
            while( results.next() ) {
            CategoryEntity item = new CategoryEntity();
                item.setId( results.getInt("id") );
                item.setTitle( results.getString("title") );
                item.setSlug( results.getString("slug") );

                itemList.add(item);
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

        return itemList;
    }

}
