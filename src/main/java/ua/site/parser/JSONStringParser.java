package ua.site.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ua.site.models.crud.Location.Location;

import java.util.ArrayList;
import java.util.List;

public class JSONStringParser {
    /**
     * @param data - must be String JSONArray
     * @return JSONArray
     */
    public static JSONArray parse(String data) {
        JSONParser jsonParser = new JSONParser();

        try {
            return (JSONArray) jsonParser.parse(data);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    public static Location[] readJSONArray(String data) {
        JSONArray jsonArray = parse(data);
        List<Location> locationList = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject location = (JSONObject) obj;
            String region = (String) location.get("region");
            double lon = (double) location.get("longitude");
            double lat = (double) location.get("latitude");
            Long depth = (Long) location.get("depth");

            locationList.add(new Location(region, lat, lon, depth.doubleValue()));
        }

        return locationList.toArray(Location[]::new);
    }
}
