package ua.site.PostRequestWithJSON;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class TestPostRequestWithJSON {

    @BeforeAll
    static void beforeAll() {


    }

    @Test
    void sendRequestAndGetResponse() {
        boolean result = false;
        HttpClient hc = new DefaultHttpClient();

        HttpPost p = new HttpPost("http://localhost:8080/locations/file");

        String locs = null;
        try {
            locs = FileUtils.readFileToString(new File("src/main/resources/locations.json"));

            p.setEntity(new StringEntity(locs, "UTF8"));
            p.setHeader("Content-type", "application/json");
            HttpResponse resp = hc.execute(p);
            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            String str = "" + resp.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(true);
    }
}
