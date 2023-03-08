package com.avia.repository;

import com.avia.configuration.PropertiesDB;
import com.avia.domain.Passenger;
import com.avia.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.avia.repository.columns.PassengerColumns.CHANGED;
import static com.avia.repository.columns.PassengerColumns.CREATED;
import static com.avia.repository.columns.PassengerColumns.FULL_NAME;
import static com.avia.repository.columns.PassengerColumns.ID_PASS;
import static com.avia.repository.columns.PassengerColumns.IS_DELETED;
import static com.avia.repository.columns.PassengerColumns.PERSONAL_ID;

@Repository
@Primary
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {
    @Autowired
    private DriverService driverService;

    @Override
    public List<Passenger> findAll() {
        final String findAllQuery = "select * from passengers order by id_pass asc";
        List<Passenger> result = new ArrayList<>();
        try (Connection connection = driverService.getConnection();
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
            passenger.setIdPass(rs.getLong(ID_PASS.name()));
            passenger.setFullName(rs.getString(FULL_NAME.name()));
            passenger.setPersonalId(rs.getString(PERSONAL_ID.name()));
            passenger.setCreated(rs.getTimestamp(CREATED.name()));
            passenger.setChanged(rs.getTimestamp(CHANGED.name()));
            passenger.setIsDeleted(rs.getBoolean(IS_DELETED.name()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passenger;
    }

    @Override
    public Passenger findById(Long idPass) {
        final String findOneById = "select * from passengers where id_pass = ?";
        List<Passenger> passenger = new ArrayList<>();
        try (Connection connection = driverService.getConnection();
             PreparedStatement statement = connection.prepareStatement(findOneById)) {
            statement.setLong(1, idPass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passenger.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return passenger.size() == 1 ? passenger.get(0) : null;
    }

    @Override
    public Optional<Passenger> findOne(Long idPass) {
        final String findOne = "select * from passengers where id_pass = ?";
        String fullName = null;
        String personalId = null;
        Timestamp created = null;
        Timestamp changed = null;
        boolean isDeleted = false;
        try (Connection connection = driverService.getConnection();
             PreparedStatement statement = connection.prepareStatement(findOne)) {
            statement.setLong(1, idPass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                fullName = resultSet.getString("full_name");
                personalId = resultSet.getString("personal_id");
                created = resultSet.getTimestamp("created");
                changed = resultSet.getTimestamp("changed");
                isDeleted = resultSet.getBoolean("is_deleted");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return Optional.of(new Passenger(idPass, fullName, personalId, created, changed, isDeleted));
    }

    @Override
    public Passenger create(Passenger passenger) {
        final String createQuery = "INSERT INTO passengers (full_name, personal_id, created, changed)" +
                " values (?, ?, ?, ?)";
        final String sql = "SELECT id_pass FROM passengers ORDER BY id_pass DESC LIMIT 1";
        try (Connection connection = driverService.getConnection();
             PreparedStatement statement = connection.prepareStatement(createQuery)) {
            statement.setString(1, passenger.getFullName());
            statement.setString(2, passenger.getPersonalId());
            statement.setTimestamp(3, passenger.getCreated());
            statement.setTimestamp(4, passenger.getChanged());
            statement.executeUpdate();
            PreparedStatement lastIdStatement = connection.prepareStatement(sql);
            ResultSet resultSet = lastIdStatement.executeQuery();
            if (resultSet.next()) {
                passenger.setIdPass(resultSet.getLong("id_pass"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        final String updateQuery = "update passengers SET full_name = ?, personal_id = ?, changed = ? WHERE id_pass = ?";
        try (Connection connection = driverService.getConnection();
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
    public Passenger deleteById(Long idPass) {
        final String deleteQuery = "update passengers set is_deleted = true, changed = ? WHERE id_pass = ?";
        try (Connection connection = driverService.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            statement.setLong(2, idPass);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return new Passenger();
    }

    @Override
    public List<Passenger> findAllWoman() {
        final String findAllWoman = "select * from passengers " +
                "where MOD(CAST(SUBSTRING(personal_id, 1, 1) as int),2) = 0 order by id_pass asc";
        List<Passenger> woman = new ArrayList<>();
        try (Connection connection = driverService.getConnection();
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
        try (Connection connection = driverService.getConnection();
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
    public List<Passenger> findMinskRegionPass() {
        return null;
    }

    @Override
    public List<Passenger> searchPassengerCitizenBelarus() {
        final String findLetter = "select * from passengers where personal_id like '%PB%' order by id_pass asc";
        List<Passenger> belarus = new ArrayList<>();
        try (Connection connection = driverService.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findLetter)
        ) {
            while (rs.next()) {
                belarus.add(parseResultSet(rs));
            }
            return belarus;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}