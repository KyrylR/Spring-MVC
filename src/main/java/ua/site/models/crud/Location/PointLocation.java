package ua.site.models.crud.Location;

public class PointLocation {
    public static boolean isInside(Location p, Location[] polygon) {
        int intersections = 0;
        Location prev = polygon[polygon.length - 1];
        for (Location next : polygon) {
            if ((prev.getY() <= p.getY() && p.getY() < next.getY()) || (prev.getY() >= p.getY() && p.getY() > next.getY())) {
                double dy = next.getY() - prev.getY();
                double dx = next.getX() - prev.getX();
                double x = (p.getY() - prev.getY()) / dy * dx + prev.getX();
                if (x > p.getX()) {
                    intersections++;
                }
            }
            prev = next;
        }

        boolean result = (intersections % 2 == 1);
        if (!result) {
            result = isOnBorder(p, polygon);
        }
        return result;
    }

    private static boolean isOnBorder(Location p, Location[] polygon) {
        int index = 1;
        for (Location first : polygon) {
            Location second = polygon[1];
            double distanceFromPoint = p.distanceTo(first) + p.distanceTo(second);
            double distanceBetweenEndPoints = first.distanceTo(second);
            double subtraction = distanceFromPoint - distanceBetweenEndPoints;
            if (subtraction < 1e-6)
                return true;
            index++;
            if (index == polygon.length - 1)
                break;
        }
        return false;
    }

}


