package be.intecbrussel.javajuni21.exams.exam06.repositories;

import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class CustomerRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( CustomerEntity record ) throws CustomerException {

        int rowsEffected = 0;

        try {
            String query = "insert into customer ( first_name, last_name, email, passcode, registry_date, active ) values ( ?, ?, ?, ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getFirstName());
            statement.setString(2, record.getLastName());
            statement.setString(3, record.getEmail());
            statement.setString(4, record.getPasscode());
            statement.setDate(5, record.getRegistryDate());
            statement.setInt(6, record.getActive());

             rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public CustomerEntity read( Integer id ) throws CustomerException {

        CustomerEntity item = new CustomerEntity();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );
            }
        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return item;
    }

    public List<CustomerEntity> read( CustomerEntity record ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where first_name = ? OR last_name = ? OR email = ? OR passcode = ? OR registry_date = ? OR active = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, record.getFirstName() );
            statement.setString( 2, record.getLastName() );
            statement.setString( 3, record.getEmail() );
            statement.setString( 4, record.getPasscode() );
            statement.setDate( 5, record.getRegistryDate() );
            statement.setInt( 6, record.getActive() );

            results = statement.executeQuery();
            while(results.next()){
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> read() throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public int update( Integer id, CustomerEntity record ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new CustomerException( "Customer is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set   first_name  = ?,   last_name  = ?,   email  = ?,   passcode  = ?,   registry_date  = ?,   active  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getFirstName());
            statement.setString(2, record.getLastName());
            statement.setString(3, record.getEmail());
            statement.setString(4, record.getPasscode());
            statement.setDate(5, record.getRegistryDate());
            statement.setInt(6, record.getActive());
            statement.setInt( 7, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete( Integer id ) throws CustomerException {

        int rowsEffected = 0;

        try {
            String query = "delete from customer where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateFirstName( Integer id, String firstName ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( firstName == null ) {
            throw new CustomerException( "firstName is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  first_name  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, firstName );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateLastName( Integer id, String lastName ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( lastName == null ) {
            throw new CustomerException( "lastName is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  last_name  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, lastName );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateEmail( Integer id, String email ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( email == null ) {
            throw new CustomerException( "email is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  email  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, email );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updatePasscode( Integer id, String passcode ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( passcode == null ) {
            throw new CustomerException( "passcode is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  passcode  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, passcode );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateRegistryDate( Integer id, java.sql.Date registryDate ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( registryDate == null ) {
            throw new CustomerException( "registryDate is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  registry_date  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setDate( 1, registryDate );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateActive( Integer id, Integer active ) throws CustomerException {

        if( id < 0 ) {
            throw new CustomerException( "Customer ID is required." ).requiredFields("id");
        }

        if ( active == null ) {
            throw new CustomerException( "active is required." ).nullCustomerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update customer set  active  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, active );
            statement.setInt( 2, id );

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return rowsEffected;
    }


    public List<CustomerEntity> searchByFirstName( String firstName ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where first_name LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, firstName );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> searchByLastName( String lastName ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where last_name LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, lastName );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> searchByEmail( String email ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where email LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, email );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> searchByPasscode( String passcode ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where passcode LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, passcode );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> searchByRegistryDate( java.sql.Date registryDate ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where registry_date = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setDate( 1, registryDate );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

    public List<CustomerEntity> searchByActive( Integer active ) throws CustomerException {

        final List<CustomerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, first_name, last_name, email, passcode, registry_date, active from customer where active = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, active );

            results = statement.executeQuery();
            while( results.next() ) {
            CustomerEntity item = new CustomerEntity();
                item.setId( results.getInt("id") );
                item.setFirstName( results.getString("first_name") );
                item.setLastName( results.getString("last_name") );
                item.setEmail( results.getString("email") );
                item.setPasscode( results.getString("passcode") );
                item.setRegistryDate( results.getDate("registry_date") );
                item.setActive( results.getInt("active") );

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                CustomerException ex = new CustomerException(exception.getMessage());
                     throw ex;
            }
        }

        return itemList;
    }

}
