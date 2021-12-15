package be.intecbrussel.javajuni21.exams.exam06.repositories;

import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class OrderRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( OrderEntity record ) throws OrderException {

        int rowsEffected = 0;

        try {
            String query = "insert into order ( customer_id, brewer_id, registered, updated, payment ) values ( ?, ?, ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getCustomerId());
            statement.setInt(2, record.getBrewerId());
            statement.setTimestamp(3, record.getRegistered());
            statement.setTimestamp(4, record.getUpdated());
            statement.setFloat(5, record.getPayment());

             rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public OrderEntity read( Integer id ) throws OrderException {

        OrderEntity item = new OrderEntity();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );
            }
        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return item;
    }

    public List<OrderEntity> read( OrderEntity record ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where customer_id = ? OR brewer_id = ? OR registered = ? OR updated = ? OR payment = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, record.getCustomerId() );
            statement.setInt( 2, record.getBrewerId() );
            statement.setTimestamp( 3, record.getRegistered() );
            statement.setTimestamp( 4, record.getUpdated() );
            statement.setFloat( 5, record.getPayment() );

            results = statement.executeQuery();
            while(results.next()){
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderEntity> read() throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public int update( Integer id, OrderEntity record ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new OrderException( "Order is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set   customer_id  = ?,   brewer_id  = ?,   registered  = ?,   updated  = ?,   payment  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getCustomerId());
            statement.setInt(2, record.getBrewerId());
            statement.setTimestamp(3, record.getRegistered());
            statement.setTimestamp(4, record.getUpdated());
            statement.setFloat(5, record.getPayment());
            statement.setInt( 6, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete( Integer id ) throws OrderException {

        int rowsEffected = 0;

        try {
            String query = "delete from order where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateCustomerId( Integer id, Integer customerId ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( customerId == null ) {
            throw new OrderException( "customerId is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set  customer_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, customerId );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateBrewerId( Integer id, Integer brewerId ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( brewerId == null ) {
            throw new OrderException( "brewerId is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set  brewer_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, brewerId );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateRegistered( Integer id, java.sql.Timestamp registered ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( registered == null ) {
            throw new OrderException( "registered is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set  registered  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setTimestamp( 1, registered );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateUpdated( Integer id, java.sql.Timestamp updated ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( updated == null ) {
            throw new OrderException( "updated is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set  updated  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setTimestamp( 1, updated );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updatePayment( Integer id, Float payment ) throws OrderException {

        if( id < 0 ) {
            throw new OrderException( "Order ID is required." ).requiredFields("id");
        }

        if ( payment == null ) {
            throw new OrderException( "payment is required." ).nullOrderException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order set  payment  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, payment );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public List<OrderEntity> searchByCustomerId( Integer customerId ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where customer_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, customerId );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderEntity> searchByBrewerId( Integer brewerId ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where brewer_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, brewerId );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderEntity> searchByRegistered( java.sql.Timestamp registered ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where registered = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setTimestamp( 1, registered );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderEntity> searchByUpdated( java.sql.Timestamp updated ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where updated = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setTimestamp( 1, updated );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderEntity> searchByPayment( Float payment ) throws OrderException {

        final List<OrderEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, customer_id, brewer_id, registered, updated, payment from order where payment = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, payment );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderEntity item = new OrderEntity();
                item.setId( results.getInt("id") );
                item.setCustomerId( results.getInt("customer_id") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setRegistered( results.getTimestamp("registered") );
                item.setUpdated( results.getTimestamp("updated") );
                item.setPayment( results.getFloat("payment") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderException ex = new OrderException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderException ex = new OrderException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

}
