package ua.site.dao.crud;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;
import ua.site.models.crud.Test;

import java.util.List;

@Component
public class LocationDAO {
    private final JdbcTemplate jdbcTemplate;

    public LocationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Field showField(int id) {
        return jdbcTemplate.query("SELECT * FROM field WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Field.class))
                .stream().findAny().orElse(null);
    }

    public Area showArea(int id) {
        return jdbcTemplate.query("SELECT * FROM area WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Area.class))
                .stream().findAny().orElse(null);
    }

    public Test showTest(int id) {
        return jdbcTemplate.query("SELECT * FROM test WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Test.class))
                .stream().findAny().orElse(null);
    }

    public List<Field> indexFields() {
        return jdbcTemplate.query("SELECT * FROM field", new BeanPropertyRowMapper<>(Field.class));
    }

    public List<Test> indexTests() {
        return jdbcTemplate.query("SELECT * FROM test", new BeanPropertyRowMapper<>(Test.class));
    }

    public List<Area> indexAreas() {
        return jdbcTemplate.query("SELECT * FROM area", new BeanPropertyRowMapper<>(Area.class));
    }

    public void saveField(Field field) {
        jdbcTemplate.update("INSERT INTO field (name, latitude, longitude) VALUES(?, ?, ?)", field.getName(),
                field.getLatitude(), field.getLongitude());
    }

    public void saveArea(Area area) {
        jdbcTemplate.update("INSERT INTO area (region, fk_field) VALUES(?, (SELECT id from field WHERE id=?))",
                area.getRegion(), area.getField().getId());
    }

    public void saveTest(Test test) {
        jdbcTemplate.update("INSERT INTO test (latitude, longitude, fk_field) VALUES(?, ?, (SELECT id from field WHERE id=?))",
                test.getLatitude(), test.getLongitude(), test.getField().getId());
    }

//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
}
