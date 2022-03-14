package ua.site.dao.crud.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.site.models.crud.Area;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AreaMapper implements RowMapper<Area> {
    @Override
    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area = new Area();
        area.setId(rs.getInt("id"));
        area.setRegion(rs.getString("region"));
        return area;
    }
}
