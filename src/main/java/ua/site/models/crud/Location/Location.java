package ua.site.models.crud.Location;

public class Location {
    private final String region;
    private final double longitude;
    private final double latitude;
    private final double depth;

    public String getRegion() {
        return region;
    }

    /**
     * @return Longitude
     */
    public double getX() {
        return longitude;
    }

    /**
     * @return Latitude
     */
    public double getY() {
        return latitude;
    }

    /**
     * create and initialize a point with given region and
     * (latitude, longitude) specified in degrees
     *
     * @param region    - The region in which the point was obtained
     * @param latitude  - Latitude is the measurement of distance north or south of the Equator.
     * @param longitude - Longitude is the measurement east or west of the prime meridian.
     * @param depth     - Depth of the processed ground measured by penetrometer
     */
    public Location(String region, double latitude, double longitude, double depth) {
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
    }

    /**
     * @param other - Point location to which we have to find the distance
     * @return - Distance between the first location and the other location, measured in reference miles
     */
    public double distanceTo(Location other) {

        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(other.latitude);
        double lon2 = Math.toRadians(other.longitude);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    /**
     * @return string representation of the specified point
     */
    public String toString() {
        return region + " (" + latitude + ", " + longitude + ")";
    }

    public double getDepth() {
        return depth;
    }
}
