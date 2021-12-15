package be.intecbrussel.javajuni21.exams.exam06.repositories;

import be.intecbrussel.javajuni21.exams.exam06.exceptions.BeerException;
import com.example.models.entities.*;
import java.sql.*;
import java.util.*;
import com.example.exceptions.*;

public class BeerRepository2 {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
            Connection conn;
            conn = ConnectionFactory2.getInstance().getConnection();
            return conn;
    }

    public int create( Beer record ) throws BeerException {

        int rowsEffected = 0;

        try {
            String query = "insert into beer ( name, brewer_id, category_id, price, stock, alcohol, version, image_url ) values ( ?, ?, ?, ?, ?, ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getName());
            statement.setInt(2, record.getBrewerId());
            statement.setInt(3, record.getCategoryId());
            statement.setFloat(4, record.getPrice());
            statement.setInt(5, record.getStock());
            statement.setFloat(6, record.getAlcohol());
            statement.setInt(7, record.getVersion());
            statement.setString(8, record.getImageUrl());

             rowsEffected = statement.executeUpdate();

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

        return rowsEffected;
    }

    public Beer read( Integer id ) throws BeerException {

        Beer item = new Beer();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if(results.next()){
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );
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

        return item;
    }

    public List<Beer> read( BeerEntity record ) throws BeerException {

        final List<Beer> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where name = ? OR brewer_id = ? OR category_id = ? OR price = ? OR stock = ? OR alcohol = ? OR version = ? OR image_url = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, record.getName() );
            statement.setInt( 2, record.getBrewerId() );
            statement.setInt( 3, record.getCategoryId() );
            statement.setFloat( 4, record.getPrice() );
            statement.setInt( 5, record.getStock() );
            statement.setFloat( 6, record.getAlcohol() );
            statement.setInt( 7, record.getVersion() );
            statement.setString( 8, record.getImageUrl() );

            results = statement.executeQuery();
            while(results.next()){
            Beer item = new Beer();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<Beer> read() throws BeerException {

        final List<Beer> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while(results.next()){
                Beer item = new Beer();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public int update( Integer id, BeerEntity record ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( record == null ) {
            throw new BeerException( "Beer is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set   name  = ?,   brewer_id  = ?,   category_id  = ?,   price  = ?,   stock  = ?,   alcohol  = ?,   version  = ?,   image_url  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getName());
            statement.setInt(2, record.getBrewerId());
            statement.setInt(3, record.getCategoryId());
            statement.setFloat(4, record.getPrice());
            statement.setInt(5, record.getStock());
            statement.setFloat(6, record.getAlcohol());
            statement.setInt(7, record.getVersion());
            statement.setString(8, record.getImageUrl());
            statement.setInt( 9, id );

            rowsEffected = statement.executeUpdate();

        } catch ( SQLException sqlException ) {
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

        return rowsEffected;
    }

    public int delete( Integer id ) throws BeerException {

        int rowsEffected = 0;

        try {
            String query = "delete from beer where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
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

        return rowsEffected;
    }

    public int updateName( Integer id, String name ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( name == null ) {
            throw new BeerException( "name is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  name  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, name );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateBrewerId( Integer id, Integer brewerId ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( brewerId == null ) {
            throw new BeerException( "brewerId is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  brewer_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, brewerId );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateCategoryId( Integer id, Integer categoryId ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( categoryId == null ) {
            throw new BeerException( "categoryId is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  category_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, categoryId );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updatePrice( Integer id, Float price ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( price == null ) {
            throw new BeerException( "price is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  price  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, price );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateStock( Integer id, Integer stock ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( stock == null ) {
            throw new BeerException( "stock is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  stock  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, stock );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateAlcohol( Integer id, Float alcohol ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( alcohol == null ) {
            throw new BeerException( "alcohol is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  alcohol  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, alcohol );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateVersion( Integer id, Integer version ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( version == null ) {
            throw new BeerException( "version is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  version  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, version );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public int updateImageUrl( Integer id, String imageUrl ) throws BeerException {

        if( id < 0 ) {
            throw new BeerException( "Beer ID is required." ).requiredFields("id");
        }

        if ( imageUrl == null ) {
            throw new BeerException( "imageUrl is required." ).nullBeerException();
        }

        int rowsEffected = 0;

        try {
            String query = "update beer set  image_url  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, imageUrl );
            statement.setInt( 2, id );

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

        return rowsEffected;
    }


    public List<BeerEntity> searchByName( String name ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where name LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, name );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByBrewerId( Integer brewerId ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where brewer_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, brewerId );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByCategoryId( Integer categoryId ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where category_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, categoryId );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByPrice( Float price ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where price = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, price );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByStock( Integer stock ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where stock = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, stock );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByAlcohol( Float alcohol ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where alcohol = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setFloat( 1, alcohol );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByVersion( Integer version ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where version = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt( 1, version );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

    public List<BeerEntity> searchByImageUrl( String imageUrl ) throws BeerException {

        final List<BeerEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, name, brewer_id, category_id, price, stock, alcohol, version, image_url from beer where image_url LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString( 1, imageUrl );

            results = statement.executeQuery();
            while( results.next() ) {
            BeerEntity item = new BeerEntity();
                item.setId( results.getInt("id") );
                item.setName( results.getString("name") );
                item.setBrewerId( results.getInt("brewer_id") );
                item.setCategoryId( results.getInt("category_id") );
                item.setPrice( results.getFloat("price") );
                item.setStock( results.getInt("stock") );
                item.setAlcohol( results.getFloat("alcohol") );
                item.setVersion( results.getInt("version") );
                item.setImageUrl( results.getString("image_url") );

                itemList.add(item);
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

        return itemList;
    }

}
