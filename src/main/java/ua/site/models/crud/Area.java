package ua.site.models.crud;

public class Area {
    private int id;
    private String region;
    private Field field;

    public Area(int id, String region, Field field) {
        this.id = id;
        this.region = region;
        this.field = field;
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
