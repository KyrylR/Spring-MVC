package ua.site.logic;

import ua.site.models.crud.Field;
import ua.site.models.crud.Location.Location;
import ua.site.models.crud.Location.PointLocation;
import ua.site.models.crud.Sample;

import java.util.ArrayList;
import java.util.List;

public class SampleToFieldMatcher {
    public static Field match(List<Field> fields, Sample sample) {
        for (Field field : fields) {
            Double[] lat = field.getLatitude();
            Double[] lon = field.getLongitude();
            List<Location> locations = new ArrayList<>();
            String possibleRegion = field.getArea().getRegion();
            for (int i = 0; i < lat.length; i++) {
                locations.add(new Location(possibleRegion, lat[i], lon[i], sample.getDepth()));
            }
            Location test = new Location(possibleRegion, sample.getLatitude(), sample.getLongitude(), sample.getDepth());
            var temp = locations.toArray(new Location[0]);
            boolean res = PointLocation.isInside(test, temp);
            if (res) {
                return field;
            }
        }
        return null;
    }
}
