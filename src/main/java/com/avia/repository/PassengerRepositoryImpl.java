package com.avia.repository;

import com.avia.domain.Passenger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
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
        final String findOne = "select * from passengers where id_pass = ?";
        List<Passenger> pass = findAll();
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(findOne)) {
            statement.setLong(1, idPass);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        Long finalIdPass = idPass;
        return pass.stream().filter(passenger -> passenger.getIdPass() == finalIdPass).findAny().orElse(null);
    }

    @Override
    public Passenger create(Passenger passenger) {
        final String createQuery = "INSERT INTO passengers (full_name, personal_id, created, changed)" +
                " values (?, ?, ?, ?)";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(createQuery)) {
            statement.setString(1, passenger.getFullName());
            statement.setString(2, passenger.getPersonalId());
            statement.setTimestamp(3, passenger.getCreated());
            statement.setTimestamp(4, passenger.getChanged());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        final String updateQuery = "UPDATE passengers SET full_name = ?, personal_id = ?, changed = ? WHERE id_pass = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, passenger.getFullName());
            statement.setString(2, passenger.getPersonalId());
            statement.setTimestamp(3, passenger.getChanged());
            statement.setLong(4, passenger.getIdPass());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return passenger;
    }

    @Override
    public void delete(Long idPass) {
        final String deleteQuery = "update passengers set is_deleted = true, changed = ? WHERE id_pass = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            Timestamp changed = null;
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setLong(2, idPass);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Passenger> findAllWoman() {
        final String findAllWoman = "select * from passengers " +
                "where MOD(CAST(SUBSTRING(personal_id, 1, 1) as int),2) = 0 order by id_pass asc";
        List<Passenger> woman = new ArrayList<>();
        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllWoman)
        ) {
            while (rs.next()) {
                woman.add(parseResultSet(rs));
            }
            return woman;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Passenger> findFirstLetterSurname() {
        final String findLetter = "select * from passengers where full_name like 'M%' order by id_pass asc";
        List<Passenger> result = new ArrayList<>();
        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findLetter)
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

    @Override
    public void searchPassenger() {

    }
}