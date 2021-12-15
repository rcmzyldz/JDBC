package be.intecbrussel.javajuni21.exams.exam06.repositories;

import be.intecbrussel.javajuni21.exams.exam06.models.entities.Customer;

import java.sql.*;
import java.util.*;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.*;

public class CustomerRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public int create(Customer newCustomer) throws CustomerException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier ..

            Statement createStatement = getConnection().createStatement();
            String query = "INSERT INTO brewer " +
                    "(name, address, postcode, city, turnover) " +
                    "VALUES " +
                    "('" + newCustomer.getFirstName() + "  ', " +
                    "'" + newCustomer.getLastName() + "', " +
                    "'" + newCustomer.getEmail() + "', " +
                    "'" + newCustomer.getPasscode() + "', " +
                    "'" + newCustomer.getRegistryDate() + "', " +
                    "'" + newCustomer.getActive() + "')";


            noOfRecordsEffected = createStatement.executeUpdate(query);

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
        return noOfRecordsEffected;
    }

    public Customer read(long id) throws CustomerException {

        Customer result = new Customer();

        try {
		
		// TODO: voeg je code hier ..
            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT id FROM category WHERE id = ? ";
            ResultSet resultOfReading = selectStatement.executeQuery(query);

            while (resultOfReading.next()) {

                result.setId(resultOfReading.getInt("id"));
                result.setFirstName(resultOfReading.getString("first_name"));
                result.setLastName(resultOfReading.getString("last_name"));
                result.setEmail(resultOfReading.getString("email"));
                result.setPasscode(resultOfReading.getString("passcode"));
                result.setRegistryDate(resultOfReading.getDate("registry_date"));
                result.setActive(resultOfReading.getInt("active"));

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

        return result;

    }

    public List<Customer> read(Customer example) throws CustomerException {

        List<Customer> customerList = new ArrayList<Customer>();

        try {
            
            // TODO: voeg je code hier
              String query = "SELECT  id, first_name last_name, email, passcode, registry_date, active" +
                    "FROM customer " +
                    "WHERE id = ? or first_name = ? or last_name = ? or email = ? or passcode = ? or registry_date = ? or active = ? ";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setInt(1, example.getId());
            selectStatement.setString(2, example.getFirstName());
            selectStatement.setString(3, example.getLastName());
            selectStatement.setString(4, example.getEmail());
            selectStatement.setString(5, example.getPasscode());
            selectStatement.setDate(6, example.getRegistryDate());
            selectStatement.setInt(7, example.getActive());

            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setFirstName(result.getString("first_name"));
                customer.setLastName(result.getString("last_name"));
                customer.setEmail(result.getString("email"));
                customer.setPasscode(result.getString("passcode"));
                customer.setRegistryDate(result.getDate("registry_date"));
                customer.setActive(result.getInt("active"));

                customerList.add(customer);
            }

        } catch (SQLException sqlException) {
            CustomerException ex = new CustomerException(sqlException.getMessage());
            throw ex.notFound();

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

        // return empty collection if fails
        return customerList;

    }

    public List<Customer> read() throws CustomerException {

        List<Customer> customerList = new ArrayList<>();

        try {

            // TODO: voeg je code hier ..

            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT id, id, first_name, last_name, email, passcode, registry_date, active FROM customer";
            ResultSet result = selectStatement.executeQuery(query);

            while (result.next()) {

                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setFirstName(result.getString("first_name"));
                customer.setLastName(result.getString("last_name"));
                customer.setEmail(result.getString("email"));
                customer.setPasscode(result.getString("passcode"));
                customer.setRegistryDate(result.getDate("registry_date"));
                customer.setActive(result.getInt("active"));

                customerList.add(customer);
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

        return customerList;

    }

    public int update(long id, Customer existingCustomer) throws CustomerException {

        if (id < 0) {
            throw new CustomerException("Customer ID is required.").requiredFields("id");
        }

        if (existingCustomer == null) {
            throw new CustomerException("Customer is required.").nullCustomerException();
        }

        int noOfRecordsEffected = 0;

        try {
           
           // TODO: voeg je code hier ..
            String preparedQuery = "UPDATE  customer SET first_name = ?, last_name = ? WHERE id = ?";
            PreparedStatement updateStatement = getConnection().prepareStatement(preparedQuery);
            updateStatement.setString(1, existingCustomer.getFirstName());
            updateStatement.setString(2, existingCustomer.getLastName());
            noOfRecordsEffected = updateStatement.executeUpdate();


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

        return noOfRecordsEffected;

    }

    public int delete(int id) throws CustomerException {

        int noOfRecordsEffected = 0;

        try {
            
            // TODO: voeg je code hier ..


            String query = "DELETE FROM customer WHERE id= ?";
            PreparedStatement deleteStatement = getConnection().prepareStatement(query);
            deleteStatement.setInt(1, id);
            noOfRecordsEffected = deleteStatement.executeUpdate();


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

        return noOfRecordsEffected;
    }


    public List<Customer> search(String keyword) throws CustomerException {

        List<Customer> customerList = new ArrayList<>();

        try {

            // TODO: voeg je code hier ..
           Statement statement = connection.createStatement();
            String query = "SELECT  id, first_name, last_name, email, passcode,  registry_date, active" +
                    "FROM customer " +
                    "WHERE first_name like ? or last_name like ? or email like ? or passcode like ? or registry_date like ? or active like ?";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setString(1, keyword);
            selectStatement.setString(2, keyword);
            selectStatement.setString(3, keyword);
            selectStatement.setString(4, keyword);
            selectStatement.setString(5, keyword);
            selectStatement.setString(6, keyword);
            selectStatement.setString(7, keyword);
            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setFirstName(result.getString("first_name"));
                customer.setLastName(result.getString("last_name"));
                customer.setEmail(result.getString("email"));
                customer.setPasscode(result.getString("passcode"));
                customer.setRegistryDate(result.getDate("registry_date"));
                customer.setActive(result.getInt("active"));

                customerList.add(customer);
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

        return customerList;

    }

}
