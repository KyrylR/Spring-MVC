package ua.site.models.crud;

import ua.site.models.crud.Location.Location;
import ua.site.validation.FieldsValueMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "latitude",
                fieldMatch = "longitude",
                message = "Longitude and latitude must be the same size"
        )
})
public class Field implements Display {
    private int id;
    @NotEmpty(message = "The Name parameter cannot be empty")
    @Size(min = 5, max = 16, message = "The Name parameter can be from 5 to 16 characters")
    private String name;
    private Double[] latitude;
    private Double[] longitude;
    private Area area;

    private int areaId;

    public Field() {
        latitude = new ArrayList<Double>(1).toArray(Double[]::new);
        longitude = new ArrayList<Double>(1).toArray(Double[]::new);
    }

    public Field(int id, String name, Location[] locations, Area area) {
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
        this.area = area;
        this.areaId = area.getId();
    }

    public Field(int id, String name, Double[] latitude, Double[] longitude, Area area) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.areaId = area.getId();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String display() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + Arrays.toString(latitude) +
                ", longitude=" + Arrays.toString(longitude) +
                '}';
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

    @Override
    public String getObject() {
        return "field";
    }

    public Double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(Double[] latitude) {
        this.latitude = latitude;
    }

    public String getLatitudeStr() {
        return Arrays.toString(latitude);
    }

    public Double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(Double[] longitude) {
        this.longitude = longitude;
    }

    public String getLongitudeStr() {
        return Arrays.toString(longitude);
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
