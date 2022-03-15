package ua.site.models.crud;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Area implements Display {
    private int id;
    @NotEmpty(message = "The Region parameter cannot be empty")
    @Size(min = 5, max = 16, message = "The Region parameter can be from 5 to 16 characters")
    private String region;

    public Area() {
    }

    public Area(int id, String region) {
        this.id = id;
        this.region = region;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", region='" + region + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String getName() {
        return String.format("Region: %s with id: %d", region, id);
    }

    @Override
    public String getObject() {
        return "area";
    }
}
