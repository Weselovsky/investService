package org.exampple.rowmapper;

import org.exampple.model.PaperModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaperRowMapper implements RowMapper<PaperModel> {

    @Override
    public PaperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PaperModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("type"),
                rs.getString("ticker"),
                rs.getInt("price"),
                rs.getInt("qty"),
                rs.getInt("profit"),
                rs.getString("sector"),
                rs.getString("image")
        );
    }
}
