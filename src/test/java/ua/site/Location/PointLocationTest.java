package ua.site.Location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.site.models.crud.Location.Location;
import ua.site.models.crud.Location.PointLocation;

class PointLocationTest {
    private static final Location[] locations = new Location[]{new Location("NO", 50.48369154202233, 30.538139814108778),
            new Location("NO", 50.48371586973436, 30.546092325408594),
            new Location("NO", 50.47824181893395, 30.54429536372065),
            new Location("NO", 50.47816882730685, 30.53664871824006),
            new Location("NO", 50.48135602335844, 30.541236705528412)};

    @Test
    void isInside_inPolygon_True() {
        Location point = new Location("No", 50.48221547015584, 30.543817885369943);
        Assertions.assertTrue(PointLocation.isInside(point, locations));
    }

    @Test
    void isInside_outOfPolygon_False() {
        Location point = new Location("No", 50.48240662431779, 30.528883345114306);
        Assertions.assertFalse(PointLocation.isInside(point, locations));
    }

    @Test
    void isInside_onTheCorner_True() {
        Location point = new Location("No", 50.48135602335844, 30.541236705528412);
        Assertions.assertTrue(PointLocation.isInside(point, locations));
    }

    @Test
    void isInside_onTheBorder_True() {
        Location point = new Location("No", 50.480978844334155, 30.545193844564622);
        Assertions.assertTrue(PointLocation.isInside(point, locations));
    }
}