package ua.site.models.crud;

import ua.site.validation.SampleValueMatch;

@SampleValueMatch.List({
        @SampleValueMatch(
                lon = "latitude",
                lan = "longitude",
                depth = "depth"
        )
})
public class Sample implements Display {
    private int id;
    private double latitude;
    private double longitude;
    private double depth;
    private Field field;

    private int areaId;

    public Sample() {
    }

    public Sample(int id, double latitude, double longitude, double depth, Field field) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.field = field;
        this.areaId = field.getId();
    }

    public Sample(double latitude, double longitude, double depth, int areaId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.areaId = areaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
        this.areaId = field.getId();
    }


    @Override
    public String getName() {
        return String.format("Longitude is %.4f and Latitude is %.4f", longitude, latitude);
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", field=" + field +
                '}';
    }

    @Override
    public String getObject() {
        return "sample";
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
