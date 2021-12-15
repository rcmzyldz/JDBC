package be.intecbrussel.javajuni21.exams.exam06.repositories;

import java.sql.*;
import java.util.*;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.*;
import be.intecbrussel.javajuni21.exams.exam06.models.entities.Brewer;

public class BrewerRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public int create(Brewer newBrewer) throws BrewerException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier toe..

            Statement createStatement = getConnection().createStatement();
            String query = "INSERT INTO brewer " +
                    "(name, address, postcode, city, turnover) " +
                    "VALUES " +
                    "('" + newBrewer.getName() + "  ', " +
                    "'" + newBrewer.getAddress() + "', " +
                    "'" + newBrewer.getPostcode() + "', " +
                    "'" + newBrewer.getCity() + "', " +

                    "'" + newBrewer.getTurnover() + "')";

            noOfRecordsEffected = createStatement.executeUpdate(query);


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
        return noOfRecordsEffected;
    }

    public Brewer read(long id) throws BrewerException {

        Brewer result = new Brewer();

        try {

            // TODO: voeg je code hier toe..

            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT id FROM brewer WHERE id = ? ";
            ResultSet resultOfReading = selectStatement.executeQuery(query);


            while (resultOfReading.next()) {

                result.setId(resultOfReading.getInt("id"));
                result.setName(resultOfReading.getString("username"));
                result.setAddress(resultOfReading.getString("address"));
                result.setPostcode(resultOfReading.getString("postcode"));
                result.setCity(resultOfReading.getString("city"));
                result.setTurnover(resultOfReading.getInt("turnover"));


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

        return result;

    }

    public List<Brewer> read(Brewer example) throws BrewerException {

        try {
            List<Brewer> brewerList = new ArrayList<>();

            // TODO: voeg je code hier toe..

            String query = "SELECT  id, name, address, postcode, city, turnover" +
                    "FROM brewer " +
                    "WHERE id = ? or name = ? or address = ? or postcode = ? or city = ? or turnover = ? ";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setInt(1, example.getId());
            selectStatement.setString(2, example.getName());
            selectStatement.setString(3, example.getAddress());
            selectStatement.setString(4, example.getPostcode());
            selectStatement.setString(5, example.getCity());
            selectStatement.setInt(6, example.getTurnover());

            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Brewer brewer = new Brewer();
                brewer.setId(result.getInt("id"));
                brewer.setName(result.getString("username"));
                brewer.setAddress(result.getString("address"));
                brewer.setPostcode(result.getString("postcode"));
                brewer.setCity(result.getString("city"));
                brewer.setTurnover(result.getInt("turnover"));

                brewerList.add(brewer);
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

        // return empty collection if fails
        return Collections.emptyList();

    }

    public List<Brewer> read() throws BrewerException {

        List<Brewer> brewerList = new ArrayList<>();

        try {

            // TODO: voeg je code hier toe ..

            Statement selectStatement = getConnection().createStatement();
            String query = "SELECT id, name, address, postcode, city, turnover FROM brewer";
            ResultSet result = selectStatement.executeQuery(query);

            while (result.next()) {

                Brewer brewer = new Brewer();
                brewer.setId(result.getInt("id"));
                brewer.setName(result.getString("username"));
                brewer.setAddress(result.getString("address"));
                brewer.setPostcode(result.getString("postcode"));
                brewer.setCity(result.getString("city"));
                brewer.setTurnover(result.getInt("turnover"));

                brewerList.add(brewer);
            }


        } catch (SQLException sqlException) {
            BrewerException ex = new BrewerException(sqlException.getMessage());
            throw ex.notFound();

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

        return brewerList;
    }

    public int update(long id, Brewer existingBrewer) throws BrewerException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier toe ..

            String preparedQuery = "UPDATE  brewer SET name = ?, city = ? WHERE id = ?";
            PreparedStatement updateStatement = getConnection().prepareStatement(preparedQuery);
            updateStatement.setString(1, existingBrewer.getName());
            updateStatement.setString(2, existingBrewer.getCity());
            noOfRecordsEffected = updateStatement.executeUpdate();


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

        return noOfRecordsEffected;

    }

    public int delete(int id) throws BrewerException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier toe ..


            String query = "DELETE FROM brewer WHERE id= ?";
            PreparedStatement deleteStatement = getConnection().prepareStatement(query);
            deleteStatement.setInt(1, id);
            noOfRecordsEffected = deleteStatement.executeUpdate();


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

        return noOfRecordsEffected;
    }

}
