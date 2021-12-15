package be.intecbrussel.javajuni21.exams.exam06.repositories;

import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class BrewerRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( BrewerEntity record ) throws BrewerException {

        int rowsEffected = 0;

        try {
            String query = "insert into brewer ( name, address, postcode, city, turnover ) values ( ?, ?, ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getName());
            statement.setString(2, record.getAddress());
            statement.setString(3, record.getPostcode());
            statement.setString(4, record.getCity());
            statement.setInt(5, record.getTurnover());

             rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public BrewerEntity read( Integer id ) throws BrewerException {

        BrewerEntity item = new BrewerEntity();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );
            }
        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return item;
    }

    public List<BrewerEntity> read( BrewerEntity record ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where name = ? OR address = ? OR postcode = ? OR city = ? OR turnover = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, record.getName() );
            statement.setString( 2, record.getAddress() );
            statement.setString( 3, record.getPostcode() );
            statement.setString( 4, record.getCity() );
            statement.setInt( 5, record.getTurnover() );

            results = statement.executeQuery();
            while(results.next()){
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<BrewerEntity> read() throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public int update( Integer id, BrewerEntity record ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new BrewerException( "Brewer is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set   name  = ?,   address  = ?,   postcode  = ?,   city  = ?,   turnover  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getName());
            statement.setString(2, record.getAddress());
            statement.setString(3, record.getPostcode());
            statement.setString(4, record.getCity());
            statement.setInt(5, record.getTurnover());
            statement.setInt( 6, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete( Integer id ) throws BrewerException {

        int rowsEffected = 0;

        try {
            String query = "delete from brewer where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateName( Integer id, String name ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( name == null ) {
            throw new BrewerException( "name is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set  name  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, name );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateAddress( Integer id, String address ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( address == null ) {
            throw new BrewerException( "address is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set  address  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, address );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updatePostcode( Integer id, String postcode ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( postcode == null ) {
            throw new BrewerException( "postcode is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set  postcode  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, postcode );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateCity( Integer id, String city ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( city == null ) {
            throw new BrewerException( "city is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set  city  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, city );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateTurnover( Integer id, Integer turnover ) throws BrewerException {

        if( id < 0 ) {
            throw new BrewerException( "Brewer ID is required." ).requiredFields("id");
        }

        if ( turnover == null ) {
            throw new BrewerException( "turnover is required." ).nullBrewerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update brewer set  turnover  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, turnover );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public List<BrewerEntity> searchByName( String name ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where name LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, name );

            results = statement.executeQuery();
            while( results.next() ) {
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<BrewerEntity> searchByAddress( String address ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where address LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, address );

            results = statement.executeQuery();
            while( results.next() ) {
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<BrewerEntity> searchByPostcode( String postcode ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where postcode LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, postcode );

            results = statement.executeQuery();
            while( results.next() ) {
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<BrewerEntity> searchByCity( String city ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where city LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, city );

            results = statement.executeQuery();
            while( results.next() ) {
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<BrewerEntity> searchByTurnover( Integer turnover ) throws BrewerException {

        final List<BrewerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, address, postcode, city, turnover from brewer where turnover = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, turnover );

            results = statement.executeQuery();
            while( results.next() ) {
            BrewerEntity item = new BrewerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setAddress( results.getString("address") );
                item.setPostcode( results.getString("postcode") );
                item.setCity( results.getString("city") );
                item.setTurnover( results.getInt("turnover") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                BrewerException ex = new BrewerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

}
