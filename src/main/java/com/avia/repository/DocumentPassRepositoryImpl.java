package com.avia.repository;

import com.avia.domain.PassDoc;
import com.avia.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.avia.repository.columns.PassDoc.FULL_NAME;
import static com.avia.repository.columns.PassDoc.ID_PASS;

@Repository
@Primary
@RequiredArgsConstructor
public class DocumentPassRepositoryImpl implements DocumentPassRepository {
    @Autowired
    private DriverService driverService;
    @Override
    public List<PassDoc> findMinskRegionPass() {
        final String findByDocument = "select p.id_pass, p.full_name from passengers as p " +
                "inner join document_pass as d on d.id_pass = p.id_pass " +
                "where d.document_num like 'MC%' order by p.id_pass asc";
        List<PassDoc> result = new ArrayList<>();
        try (Connection connection = driverService.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findByDocument)
        ) {
            while (rs.next()) {
                result.add(parseResultSetPass(rs));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private PassDoc parseResultSetPass(ResultSet rs) {
        PassDoc pass;
        try {
            pass = new PassDoc();
            pass.setIdPass(rs.getLong(ID_PASS.name()));
            pass.setFullName(rs.getString(FULL_NAME.name()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pass;
    }

    @Override
    public PassDoc findById(Long id) {
        return null;
    }

    @Override
    public Optional<PassDoc> findOne(Long id) {
        return null;
    }

    @Override
    public List<PassDoc> findAll() {
        return null;
    }

    @Override
    public PassDoc create(PassDoc object) {
        return null;
    }

    @Override
    public PassDoc update(PassDoc object) {
        return null;
    }

    @Override
    public Optional<PassDoc> deleteById(Long id) {
        return null;
    }

    @Override
    public List<PassDoc> findAllWoman() {
        return null;
    }

    @Override
    public List<PassDoc> findFirstLetterSurname() {
        return null;
    }
}