package ua.site.dao.crud;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;
import ua.site.models.crud.Sample;

import java.util.List;

@Component
public class LocationDAO {
    private final JdbcTemplate jdbcTemplate;

    public LocationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------ Area block --------------
    public List<Area> indexAreas() {
        return jdbcTemplate.query("SELECT * FROM area", new BeanPropertyRowMapper<>(Area.class));
    }

    public void saveArea(Area area) {
        jdbcTemplate.update("INSERT INTO area (region) VALUES(?)",
                area.getRegion());
    }

    public void update(int id, Area updatedArea) {
        jdbcTemplate.update("UPDATE area SET region=? WHERE id=?", updatedArea.getRegion(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM area WHERE id=?", id);
    }


    public List<Field> indexFields() {
        return jdbcTemplate.query("SELECT * FROM field", (rs, rowNum) -> {
            Field field = new Field();
            field.setId(rs.getInt("id"));
            field.setName(rs.getString("name"));
            field.setLatitude((Double[]) rs.getArray("latitude").getArray());
            field.setLongitude((Double[]) rs.getArray("longitude").getArray());
            return field;
        });
    }

    public List<Sample> indexTests() {
        return jdbcTemplate.query("SELECT * FROM test", (rs, rowNum) -> {
            Sample sample = new Sample();
            sample.setId(rs.getInt("id"));
            sample.setLatitude(rs.getDouble("latitude"));
            sample.setLongitude(rs.getDouble("longitude"));
            sample.setField(new Field());
            return sample;
        });
    }

    public void saveField(Field field) {
        jdbcTemplate.update("INSERT INTO field (name, latitude, longitude) VALUES(?, ?, ?)", field.getName(),
                field.getLatitude(), field.getLongitude());
    }

    public void saveTest(Sample sample) {
        jdbcTemplate.update("INSERT INTO test (latitude, longitude, fk_field) VALUES(?, ?, (SELECT id from field WHERE id=?))",
                sample.getLatitude(), sample.getLongitude(), sample.getField().getId());
    }

    //    public Field showField(int id) {
//        return jdbcTemplate.query("SELECT * FROM field WHERE id=?", new Object[]{id}, (rs, rowNum) -> {
//                            Field field = new Field();
//                            field.setId(rs.getInt("id"));
//                            field.setName(rs.getString("name"));
//                            field.setLatitude((Double[]) rs.getArray("latitude").getArray());
//                            field.setLongitude((Double[]) rs.getArray("longitude").getArray());
//                            return field;
//                        })
//                .stream().findAny().orElse(null);
//    }
//
//    public Area showArea(int id) {
//        return jdbcTemplate.query("SELECT * FROM area WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Area.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Sample showTest(int id) {
//        return jdbcTemplate.query("SELECT * FROM test WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Sample.class))
//                .stream().findAny().orElse(null);
//    }

}
