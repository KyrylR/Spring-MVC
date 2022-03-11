package ua.site.Location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.site.models.crud.Location.Location;

import java.text.DecimalFormat;

class LocationTest {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /*
     * Obtained confirmed distance data from http://www.movable-type.co.uk/scripts/latlong.html
     */
    @Test
    void shouldShowSimpleAssertion() {
        Location loc1 = new Location("First", 55.177771, -13.25572);
        Location loc2 = new Location("Second", 55.18629, -13.31715);
        double distance = (double) Math.round(loc1.distanceTo(loc2) * 1000) / 1000;
        Assertions.assertEquals(4.014, distance);
    }

}
