package com.avia.repository.impl;

import com.avia.domain.PassDoc;
import com.avia.repository.PassDocRepository;
import com.avia.repository.rowmapper.PassDocRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class PassDocRepositoryJdbsTemplateImpl implements PassDocRepository {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final PassDocRowMapper passDocRowMapper;
    @Override
    public List<PassDoc> findMinskRegionPass() {
        final String findByDocument = "select p.id_pass, p.full_name from passengers as p " +
                "inner join document_pass as d on d.id_pass = p.id_pass " +
                "where d.document_num like 'MC%' order by p.id_pass asc";
        return jdbcTemplate.query(findByDocument, passDocRowMapper);
    }
    @Override
    public PassDoc findById(Long id) {
        return null;
    }

    @Override
    public Optional<PassDoc> findOne(Long id) {
        return Optional.empty();
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
        return Optional.empty();
    }
}
