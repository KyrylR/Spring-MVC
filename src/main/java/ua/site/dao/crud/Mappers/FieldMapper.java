package ua.site.dao.crud.Mappers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldMapper implements RowMapper<Field> {
    private final JdbcTemplate jdbcTemplate;

    public FieldMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Field mapRow(ResultSet rs, int rowNum) throws SQLException {
        Field field = new Field();
        field.setId(rs.getInt("id"));
        field.setName(rs.getString("name"));
        field.setLatitude((Double[]) rs.getArray("latitudes").getArray());
        field.setLongitude((Double[]) rs.getArray("longitudes").getArray());
        int areaID = rs.getInt("area_id");
        Area area = jdbcTemplate.query("SELECT * FROM area WHERE id=?",
                new Object[]{areaID},
                new AreaMapper()).get(0);
        field.setArea(area);
        return field;
    }
}
