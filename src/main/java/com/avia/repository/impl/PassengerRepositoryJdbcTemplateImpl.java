package com.avia.repository.impl;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import com.avia.repository.rowmapper.PassengerRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class PassengerRepositoryJdbcTemplateImpl implements PassengerRepository {
    private static final Logger logger = Logger.getLogger(PassengerRepositoryJdbcTemplateImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final PassengerRowMapper passengerRowMapper;

    @Override
    public Passenger findById(Long idPass) {
        try {
            return jdbcTemplate.queryForObject("select * from passengers where id_pass = " + idPass, passengerRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("User not found with id " + idPass);
            throw new RuntimeException("Error!");
        }
    }

    @Override
    public Optional<Passenger> findOne(Long idPass) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from passengers where id_pass = " + idPass,
                passengerRowMapper));
    }

    @Override
    public List<Passenger> findAll() {
        return jdbcTemplate.query("select * from passengers order by id_pass asc", passengerRowMapper);
    }

    @Override
    public Passenger create(Passenger passenger) {
        final String sql = "INSERT INTO passengers (full_name, personal_id, created) values (?, ?, ?)";
        jdbcTemplate.update(sql, passenger.getFullName(),
                passenger.getPersonalId(),
                passenger.getCreated());
        final String sqlLastPass = "SELECT id_pass FROM passengers ORDER BY id_pass DESC LIMIT 1";
        jdbcTemplate.queryForObject(sqlLastPass, Long.class);
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        final String sql = "update passengers set full_name = ?, personal_id = ?, changed = ? WHERE id_pass = ?";
        jdbcTemplate.update(sql, passenger.getFullName(),
                passenger.getPersonalId(),
                passenger.getChanged(),
                passenger.getIdPass());
        return findById(passenger.getIdPass());
    }

    @Override
    public Optional<Passenger> deleteById(Long idPass) {
        final String sql = "update passengers set is_deleted = true, changed = ?  WHERE id_pass = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()), idPass);
        return findOne(idPass);
    }

    @Override
    public List<Passenger> searchPassengerCitizenBelarus() {
        return jdbcTemplate.query("select *" +
                        " from passengers" +
                        " where personal_id like '%PB%'" +
                        " order by id_pass asc",
                passengerRowMapper);
    }

    @Override
    public List<Passenger> findAllWoman() {
        return jdbcTemplate.query("select * from passengers where MOD(CAST(SUBSTRING(personal_id, 1, 1) as int),2) = 0 " +
                "order by id_pass asc", passengerRowMapper);
    }

    @Override
    public List<Passenger> searchPassSurname(String query) {
        return jdbcTemplate.query("select * " +
                        " from passengers " +
                        " where lower(full_name) ilike '%" + query + "%'",
                passengerRowMapper);
    }
}