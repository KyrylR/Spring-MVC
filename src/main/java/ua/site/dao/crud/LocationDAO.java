package ua.site.dao.crud;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.site.dao.crud.Mappers.AreaMapper;
import ua.site.dao.crud.Mappers.FieldMapper;
import ua.site.dao.crud.Mappers.SampleMapper;
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
    public List<Area> indexArea() {
        return jdbcTemplate.query("SELECT * FROM area", new AreaMapper());
    }

    public Area showArea(int id) {
        return jdbcTemplate.query("SELECT * FROM area WHERE id=?", new Object[]{id}, new AreaMapper())
                .stream().findAny().orElse(null);
    }

    public void saveArea(Area area) {
        jdbcTemplate.update("INSERT INTO area (region) VALUES(?)",
                area.getRegion());
    }

    public void updateArea(int id, Area updatedArea) {
        jdbcTemplate.update("UPDATE area SET region=? WHERE id=?", updatedArea.getRegion(), id);
    }

    public void deleteArea(int id) {
        jdbcTemplate.update("DELETE FROM area WHERE id=?", id);
    }

    // ------------ Field block --------------
    public List<Field> indexField() {
        return jdbcTemplate.query("SELECT * FROM field", new FieldMapper(jdbcTemplate));
    }

    public Field showField(int id) {
        return jdbcTemplate.query("SELECT * FROM field WHERE id=?", new Object[]{id}, new FieldMapper(jdbcTemplate))
                .stream().findAny().orElse(null);
    }

    public List<Field> findFieldByAreaId(int id) {
        return jdbcTemplate.query("SELECT * FROM field WHERE area_id=?", new Object[]{id}, new FieldMapper(jdbcTemplate));
    }

    public void saveField(Field field) {
        jdbcTemplate.update("INSERT INTO field (name, latitudes, longitudes, area_id) VALUES(?, ?, ?, (SELECT id from area WHERE id=?))", field.getName(),
                field.getLatitude(), field.getLongitude(), field.getArea().getId());
    }

    public void updateField(int id, Field updatedField) {
        jdbcTemplate.update("UPDATE field SET name=?, area_id=? WHERE id=?", updatedField.getName(), updatedField.getArea().getId(), id);
    }

    public void deleteField(int id) {
        jdbcTemplate.update("DELETE FROM field WHERE id=?", id);
    }

    // ------------ Sample block --------------
    public List<Sample> indexSample() {
        return jdbcTemplate.query("SELECT * FROM test", new SampleMapper(jdbcTemplate));
    }


    public Sample showSample(int id) {
        return jdbcTemplate.query("SELECT * FROM test WHERE id=?", new Object[]{id}, new SampleMapper(jdbcTemplate))
                .stream().findAny().orElse(null);
    }

    public void saveSample(Sample sample) {
        jdbcTemplate.update("INSERT INTO test (latitude, longitude, field_id) VALUES(?, ?, (SELECT id from field WHERE id=?))",
                sample.getLatitude(), sample.getLongitude(), sample.getField().getId());
    }

    public void updateSample(int id, Sample updatedSample) {
        jdbcTemplate.update("UPDATE test SET field_id=? WHERE id=?", updatedSample.getField().getId(), id);
    }

    public void deleteSample(int id) {
        jdbcTemplate.update("DELETE FROM test WHERE id=?", id);
    }


}
