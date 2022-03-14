package ua.site.models.crud;

public class Area implements Display {
    private int id;
    private String region;

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
