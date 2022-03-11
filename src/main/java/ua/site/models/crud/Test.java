package ua.site.models.crud;

public class Test {
    private int id;
    private double latitude;
    private double longitude;
    private Field field;

    public Test(int id, double latitude, double longitude, Field field) {
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
}
