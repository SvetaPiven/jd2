package com.noirix.repository;

import com.noirix.domain.Passenger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
//bean id=userRepositoryImpl   class=UserRepositoryImpl
//@Component
public class PassengerRepositoryImpl implements PassengerRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/Aviatickets";
    public static final String DATABASE_LOGIN = "development";
    public static final String DATABASE_PASSWORD = "dev";

    private static final String ID_PASS = "id_pass";
    private static final String FULL_NAME = "full_name";
    private static final String PERSONAL_ID = "personal_id";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String IS_DELETED = "is_deleted";


    @Override
    public List<Passenger> findAll() {

        /*
         * 1) Driver Manager - getting connection from DB
         * */

        final String findAllQuery = "select * from passengers order by id_pass asc";

        List<Passenger> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Passenger parseResultSet(ResultSet rs) {

        Passenger passenger;

        try {
            passenger = new Passenger();
            passenger.setIdPass(rs.getLong(ID_PASS)); //1 or id
            passenger.setFullName(rs.getString(FULL_NAME));
            passenger.setPersonalId(rs.getString(PERSONAL_ID));
            passenger.setCreated(rs.getTimestamp(CREATED));
            passenger.setChanged(rs.getTimestamp(CHANGED));
            passenger.setDeleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return passenger;
    }

    private void registerDriver() {
        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);
        try {
            return DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passenger findOne(Long idPass) {
        return null;
    }

    @Override
    public Passenger create(Passenger object) {
        return null;
    }

    @Override
    public Passenger update(Passenger object) {
        return null;
    }

    @Override
    public void delete(Long idPass) {

    }

    @Override
    public void searchPassenger() {

    }
}
