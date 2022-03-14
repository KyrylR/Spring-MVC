package ua.site.dao.crud.Mappers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.site.models.crud.Field;
import ua.site.models.crud.Sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleMapper implements RowMapper<Sample> {
    private final JdbcTemplate jdbcTemplate;

    public SampleMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sample sample = new Sample();
        sample.setId(rs.getInt("id"));
        sample.setLatitude(rs.getDouble("latitude"));
        sample.setLongitude(rs.getDouble("longitude"));
        int fieldId = rs.getInt("field_id");
        Field field = jdbcTemplate.query("SELECT * FROM field WHERE id=?",
                new Object[]{fieldId},
                new FieldMapper(jdbcTemplate)).get(0);
        sample.setField(field);
        return sample;
    }
}
