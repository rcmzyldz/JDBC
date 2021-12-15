package be.intecbrussel.javajuni21.exams.exam06.repositories;

import be.intecbrussel.javajuni21.exams.exam06.models.entities.Beer;

import java.sql.*;
import java.util.*;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.*;

public class BeerRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public int create(Beer newBeer) throws BeerException {

        int noOfRecordsEffected = 0;

        try {


            // TODO: voeg je code hier toe ..
            Statement createStatement = getConnection().createStatement();
            String query = "INSERT INTO beer " +
                    "(name, price, stock, alcohol, version, image_url) " +
                    "VALUES " +
                    "('" + newBeer.getName() + "  ', " +
                    "'" + newBeer.getPrice() + "', " +
                    "'" + newBeer.getStock() + "', " +
                    "'" + newBeer.getAlcohol() + "', " +
                    "'" + newBeer.getVersion() + "', " +
                    "'" + newBeer.getImageUrl() + "')";

            noOfRecordsEffected = createStatement.executeUpdate(query);


        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }
        return noOfRecordsEffected;
    }

    public Beer read(int id) throws BeerException {

        Beer result = new Beer();

        try {

            // TODO: voeg je code hier toe ..
            Statement selectStatement = getConnection().createStatement();

            String query = "SELECT id FROM beer WHERE id = ? ";
            ResultSet resultOfReading = selectStatement.executeQuery(query);

            while (resultOfReading.next()) {


                result.setId(resultOfReading.getInt("id"));
                result.setName(resultOfReading.getString("name"));
                result.setBrewerId(resultOfReading.getInt("brewer_id"));
                result.setCategoryId(resultOfReading.getInt("category_id"));
                result.setPrice(resultOfReading.getFloat("price"));
                result.setStock(resultOfReading.getInt("stock"));
                result.setAlcohol(resultOfReading.getFloat("alcohol"));
                result.setVersion(resultOfReading.getInt("version"));
                result.setImageUrl(resultOfReading.getString("image_url"));


            }

        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }

        return result;

    }

    public List<Beer> read(Beer example) throws BeerException {

        List<Beer> beerList = new ArrayList<>();

        try {

            // TODO: voeg je code hier toe ..

            String query = "SELECT id, name, brewer_id, category_id, price, stock, alcohol, version, image_url " +
                    "FROM beer " +
                    "WHERE id = ? or name = ? or brewer_id = ? or category_id = ? or price = ? or stock = ? or alcohol = ? or version = ? or image_url = ?";
            PreparedStatement selectStatement = getConnection().prepareStatement(query);

            selectStatement.setInt(1, example.getId());
            selectStatement.setString(2, example.getName());
            selectStatement.setInt(3, example.getBrewerId());
            selectStatement.setInt(4, example.getCategoryId());
            selectStatement.setFloat(5, example.getPrice());
            selectStatement.setInt(6, example.getStock());
            selectStatement.setFloat(7, example.getAlcohol());
            selectStatement.setInt(8, example.getVersion());
            selectStatement.setString(9, example.getImageUrl());

            ResultSet result = selectStatement.executeQuery();

            while (result.next()) {

                Beer beer = new Beer();
                beer.setId(result.getInt("id"));
                beer.setName(result.getString("username"));
                beer.setBrewerId(result.getInt("brewer_id"));
                beer.setCategoryId(result.getInt("category_id"));
                beer.setPrice(result.getFloat("price"));
                beer.setStock(result.getInt("stock"));
                beer.setAlcohol(result.getFloat("alcohol"));
                beer.setVersion(result.getInt("version"));
                beer.setImageUrl(result.getString("image_url"));

                beerList.add(beer);
            }


        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }

        return beerList;

    }

    public List<Beer> read() throws BeerException {

        List<Beer> beerList = new ArrayList<>();

        try {

            // TODO: voeg je code hier toe ..
            Statement selectStatement = getConnection().createStatement();

            String query = "SELECT id, name,brewer_id, category_id, price, stock, alcohol, version, image_url FROM beer";
            ResultSet result = selectStatement.executeQuery(query);

            while (result.next()) {

                Beer beer = new Beer();
                beer.setId(result.getInt("id"));
                beer.setName(result.getString("username"));
                beer.setBrewerId(result.getInt("brewer_id"));
                beer.setCategoryId(result.getInt("category_id"));
                beer.setPrice(result.getFloat("price"));
                beer.setStock(result.getInt("stock"));
                beer.setAlcohol(result.getFloat("alcohol"));
                beer.setVersion(result.getInt("version"));
                beer.setImageUrl(result.getString("image_url"));

                beerList.add(beer);
            }


        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }

        return beerList;

    }

    public int update(long id, Beer existingBeer) throws BeerException {

        if (id < 0) {
            throw new BeerException("Beer ID is required.").requiredFields("id");
        }

        if (existingBeer == null) {
            throw new BeerException("Beer is required.").nullBeerException();
        }

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier toe ..

            String preparedQuery = "UPDATE  beer SET name = ?, price = ? WHERE id = ?";
            PreparedStatement updateStatement = getConnection().prepareStatement(preparedQuery);
            updateStatement.setString(1, existingBeer.getName());
            updateStatement.setFloat(2, existingBeer.getPrice());
            noOfRecordsEffected = updateStatement.executeUpdate();


        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }

        return noOfRecordsEffected;

    }

    public int delete(int id) throws BeerException {

        int noOfRecordsEffected = 0;

        try {

            // TODO: voeg je code hier toe ..

            String query = "DELETE FROM beer WHERE id= ?";
            PreparedStatement deleteStatement = getConnection().prepareStatement(query);
            deleteStatement.setInt(1, id);
            noOfRecordsEffected = deleteStatement.executeUpdate();


        } catch (SQLException sqlException) {
            BeerException ex = new BeerException(sqlException.getMessage());
            throw ex;

        } finally {

            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();

            } catch (Exception exception) {
                BeerException ex = new BeerException(exception.getMessage());
                throw ex;
            }

        }

        return noOfRecordsEffected;
    }

}
