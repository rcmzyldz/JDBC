package be.intecbrussel.javajuni21.exams.exam06.repositories;

import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class OrderDetailRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( OrderDetailEntity record ) throws OrderDetailException {

        int rowsEffected = 0;

        try {
            String query = "insert into order_detail ( order_id, quantity, discount ) values ( ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getOrderId());
            statement.setInt(2, record.getQuantity());
            statement.setFloat(3, record.getDiscount());

             rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public OrderDetailEntity read( Integer id ) throws OrderDetailException {

        OrderDetailEntity item = new OrderDetailEntity();

        try {
            String query = "select id, order_id, quantity, discount from order_detail where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );
            }
        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return item;
    }

    public List<OrderDetailEntity> read( OrderDetailEntity record ) throws OrderDetailException {

        final List<OrderDetailEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, order_id, quantity, discount from order_detail where order_id = ? OR quantity = ? OR discount = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, record.getOrderId() );
            statement.setInt( 2, record.getQuantity() );
            statement.setFloat( 3, record.getDiscount() );

            results = statement.executeQuery();
            while(results.next()){
            OrderDetailEntity item = new OrderDetailEntity();
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderDetailEntity> read() throws OrderDetailException {

        final List<OrderDetailEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, order_id, quantity, discount from order_detail ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                OrderDetailEntity item = new OrderDetailEntity();
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public int update( Integer id, OrderDetailEntity record ) throws OrderDetailException {

        if( id < 0 ) {
            throw new OrderDetailException( "OrderDetail ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new OrderDetailException( "OrderDetail is required." ).nullOrderDetailException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order_detail set   order_id  = ?,   quantity  = ?,   discount  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getOrderId());
            statement.setInt(2, record.getQuantity());
            statement.setFloat(3, record.getDiscount());
            statement.setInt( 4, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete( Integer id ) throws OrderDetailException {

        int rowsEffected = 0;

        try {
            String query = "delete from order_detail where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateOrderId( Integer id, Integer orderId ) throws OrderDetailException {

        if( id < 0 ) {
            throw new OrderDetailException( "OrderDetail ID is required." ).requiredFields("id");
        }

        if ( orderId == null ) {
            throw new OrderDetailException( "orderId is required." ).nullOrderDetailException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order_detail set  order_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, orderId );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateQuantity( Integer id, Integer quantity ) throws OrderDetailException {

        if( id < 0 ) {
            throw new OrderDetailException( "OrderDetail ID is required." ).requiredFields("id");
        }

        if ( quantity == null ) {
            throw new OrderDetailException( "quantity is required." ).nullOrderDetailException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order_detail set  quantity  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, quantity );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateDiscount( Integer id, Float discount ) throws OrderDetailException {

        if( id < 0 ) {
            throw new OrderDetailException( "OrderDetail ID is required." ).requiredFields("id");
        }

        if ( discount == null ) {
            throw new OrderDetailException( "discount is required." ).nullOrderDetailException();
        }

        int rowsEffected = 0;

        try {
            String query = "update order_detail set  discount  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, discount );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public List<OrderDetailEntity> searchByOrderId( Integer orderId ) throws OrderDetailException {

        final List<OrderDetailEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, order_id, quantity, discount from order_detail where order_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, orderId );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderDetailEntity item = new OrderDetailEntity();
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderDetailEntity> searchByQuantity( Integer quantity ) throws OrderDetailException {

        final List<OrderDetailEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, order_id, quantity, discount from order_detail where quantity = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, quantity );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderDetailEntity item = new OrderDetailEntity();
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<OrderDetailEntity> searchByDiscount( Float discount ) throws OrderDetailException {

        final List<OrderDetailEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, order_id, quantity, discount from order_detail where discount = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, discount );

            results = statement.executeQuery();
            while( results.next() ) {
            OrderDetailEntity item = new OrderDetailEntity();
                item.setId( results.getInt("id") );
                item.setOrderId( results.getInt("order_id") );
                item.setQuantity( results.getInt("quantity") );
                item.setDiscount( results.getFloat("discount") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            OrderDetailException ex = new OrderDetailException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                OrderDetailException ex = new OrderDetailException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

}
