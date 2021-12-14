package org.exampple.rowmapper;

import org.exampple.Model.SalePaperModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SalePaperRowMapper implements RowMapper<SalePaperModel> {

    @Override
    public SalePaperModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SalePaperModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getInt("qty")

        );
    }
}
