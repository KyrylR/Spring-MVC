package ua.site.logic;

import ua.site.dao.crud.LocationDAO;
import ua.site.models.crud.Area;
import ua.site.models.crud.Field;
import ua.site.models.crud.Location.Location;
import ua.site.models.crud.Sample;

import java.util.List;

public class HandlePostRequest {
    public static String handle(Location[] locations, LocationDAO locationDAO) {
        StringBuilder result = new StringBuilder();
        for (Location location : locations) {
            String region = location.getRegion();
            double lon = location.getX();
            double lat = location.getY();
            double depth = location.getDepth();
            Area area = locationDAO.findAreaByRegion(region);
            if (area == null) {
                result.append("Location with region: '").append(region).append("' skipped, no such region in DB!\n");
                continue;
            }
            Sample sample = new Sample(lat, lon, depth, area.getId());
            List<Field> fields = locationDAO.findFieldByAreaId(sample.getAreaId());
            Field field = SampleToFieldMatcher.match(fields, sample);
            if (field == null) {
                result.append("Location with region: '").append(region).append("' skipped, because sample coordinates do not match any field in DB!\n");
                continue;
            }
            sample.setField(field);
            locationDAO.saveSample(sample);
            result.append("Location with region: '").append(region).append("' added to DB!\n");
        }
        return result.toString();
    }
}
