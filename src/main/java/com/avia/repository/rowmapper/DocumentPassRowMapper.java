package com.avia.repository.rowmapper;

import com.avia.domain.DocumentPass;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.avia.repository.columns.DocumentPassColumns.CHANGED;
import static com.avia.repository.columns.DocumentPassColumns.CREATED;
import static com.avia.repository.columns.DocumentPassColumns.DOCUMENT_NUM;
import static com.avia.repository.columns.DocumentPassColumns.ID_DOCUMENT_PASS;
import static com.avia.repository.columns.DocumentPassColumns.ID_DOCUMENT_TYPE;
import static com.avia.repository.columns.DocumentPassColumns.IS_DELETED;
import static com.avia.repository.columns.DocumentPassColumns.ID_PASS;


@Component
public class DocumentPassRowMapper implements RowMapper<DocumentPass> {
    @Override
    public DocumentPass mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentPass documentPass;
        try {
            documentPass = DocumentPass.builder()
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
        return documentPass;
    }
}
