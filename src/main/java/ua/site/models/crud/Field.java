package ua.site.models.crud;

import ua.site.models.crud.Location.Location;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private int id;
    private String name;
    private Double[] latitude;
    private Double[] longitude;

    public Field(int id, String name, Location[] locations) {
        this.id = id;
        this.name = name;
        List<Double> locationListLat = new ArrayList<>();
        List<Double> locationListLon = new ArrayList<>();
        for (Location loc : locations) {
            locationListLat.add(loc.getY());
            locationListLon.add(loc.getX());
        }
        this.latitude = locationListLat.toArray(Double[]::new);
        this.longitude = locationListLon.toArray(Double[]::new);
    }

    public Field(int id, String name, Double[] latitude, Double[] longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(Double[] latitude) {
        this.latitude = latitude;
    }

    public Double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(Double[] longitude) {
        this.longitude = longitude;
    }
}
