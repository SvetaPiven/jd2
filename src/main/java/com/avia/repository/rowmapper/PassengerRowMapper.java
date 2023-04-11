package com.avia.repository.rowmapper;

import com.avia.domain.Passenger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import static com.avia.repository.columns.PassengerColumns.CHANGED;
import static com.avia.repository.columns.PassengerColumns.CREATED;
import static com.avia.repository.columns.PassengerColumns.FULL_NAME;
import static com.avia.repository.columns.PassengerColumns.ID_PASS;
import static com.avia.repository.columns.PassengerColumns.IS_DELETED;
import static com.avia.repository.columns.PassengerColumns.PERSONAL_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PassengerRowMapper implements RowMapper<Passenger> {
    @Override
    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
        Passenger passenger;
        try {
            passenger = Passenger.builder()
                    .idPass(rs.getLong(ID_PASS.name()))
                    .fullName(rs.getString(FULL_NAME.name()))
                    .personalId(rs.getString(PERSONAL_ID.name()))
                    .created(rs.getTimestamp(CREATED.name()))
                    .changed(rs.getTimestamp(CHANGED.name()))
                    .isDeleted(rs.getBoolean(IS_DELETED.name()))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passenger;
    }
}
