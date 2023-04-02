package com.avia.repository.rowmapper;

import com.avia.domain.PassDoc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.avia.repository.columns.PassDoc.DOCUMENT_NUM;
import static com.avia.repository.columns.PassDoc.ID_DOCUMENT_PASS;
import static com.avia.repository.columns.PassDoc.ID_DOCUMENT_TYPE;
import static com.avia.repository.columns.PassDoc.ID_PASS;
import static com.avia.repository.columns.PassDoc.CREATED;
import static com.avia.repository.columns.PassDoc.CHANGED;
import static com.avia.repository.columns.PassDoc.IS_DELETED;

@Component
public class PassDocRowMapper implements RowMapper<PassDoc> {
    @Override
    public PassDoc mapRow(ResultSet rs, int rowNum) throws SQLException {
         PassDoc passDoc;
        try {
            passDoc = PassDoc.builder()
                    .idDocumentPass(rs.getLong(ID_DOCUMENT_PASS.name()))
                    .idDocumentType(rs.getLong(ID_DOCUMENT_TYPE.name()))
                    .documentNum(rs.getString(DOCUMENT_NUM.name()))
                    .idPass(rs.getLong(ID_PASS.name()))
                    .created(rs.getTimestamp(CREATED.name()))
                    .changed(rs.getTimestamp(CHANGED.name()))
                    .isDeleted(rs.getBoolean(IS_DELETED.name()))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passDoc;
    }
}

