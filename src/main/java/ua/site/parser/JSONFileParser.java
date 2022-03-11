package ua.site.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.site.models.crud.Location.Location;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileParser {
    public static JSONArray parse(String filepath) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/locations.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    public static Location[] readJSONArray(String filepath) {
        JSONArray jsonArray = parse(filepath);
        List<Location> locationList = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject location = (JSONObject) obj;
            String region = (String) location.get("region");
            double lon = (double) location.get("longitude");
            double lat = (double) location.get("latitude");

            locationList.add(new Location(region, lat, lon));
        }

        return locationList.toArray(Location[]::new);
    }
}
