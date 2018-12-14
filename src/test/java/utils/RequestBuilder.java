package utils;

import client.api.Variables;
import cucumber.api.DataTable;
import org.json.simple.JSONObject;
import java.util.List;
/**
 * Manages the Request Builder
 */
public class RequestBuilder {

    /**
     * Builds the request body replacing the values from data request table
     */
    public static JSONObject build(DataTable table) {
        JSONObject body = new JSONObject();
        body.put("apiKey", Variables.apiKey);
        for (List<String> data:table.raw()) {
            body.put(data.get(0),data.get(1));
        }
        return body;
    }
}
