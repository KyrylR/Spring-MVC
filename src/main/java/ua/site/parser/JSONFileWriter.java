package ua.site.parser;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ua.site.models.crud.Location.Location;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWriter {
    private static final Location[] locations = new Location[]{new Location("Zero", 50.48369154202233, 30.538139814108778),
            new Location("First", 50.48371586973436, 30.546092325408594),
            new Location("Second", 50.47824181893395, 30.54429536372065),
            new Location("Third", 50.47816882730685, 30.53664871824006),
            new Location("Fourth", 50.48135602335844, 30.541236705528412)};

    static public void writeObjsToFile(Location[] locations) {

        JSONArray locs = new JSONArray();

        for (Location position : locations) {
            JSONObject locationJsonObj = new JSONObject();
            locationJsonObj.put("region", position.getRegion());
            locationJsonObj.put("longitude", position.getX());
            locationJsonObj.put("latitude", position.getY());
            locs.add(locationJsonObj);
        }

        try (FileWriter file = new FileWriter("src/main/resources/locations.json")) {
            file.write(locs.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + locs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeObjsToFile(locations);
    }
}
