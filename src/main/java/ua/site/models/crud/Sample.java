package ua.site.models.crud;

public class Sample implements Display {
    private int id;
    private double latitude;
    private double longitude;
    private Field field;

    public Sample() {
    }

    public Sample(int id, double latitude, double longitude, Field field) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.field = field;
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
}
