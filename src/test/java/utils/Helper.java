package utils;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing Helper Steps.
 */
public class Helper {

    private Map<String, String> storageMap;
    private Response response;

    public Helper() {
        storageMap = new HashMap<>();
    }

    /**
     * Sets the response object.
     *
     * @param response response object.
     */
    public void setResponse(final Response response) {
        this.response = response;
    }

    /**
     * Gets the response object.
     *
     * @return response object.
     */
    public Response getResponse() {
        return response;
    }

    public void putInStorageMap(final String key, final String value) {
        storageMap.put(key, value);
    }

    public String getFromStorageMap(final String key) {
        return storageMap.get(key);
    }

    public Map<String, String> getStorageMap() {
        return storageMap;
    }

    public void validateResponseBody(DataTable table){
        JsonPath jsonPathEvaluator = response.jsonPath();
        for (List<String> data:table.raw()) {
            String ExpectedField = data.get(0);
            String ExpectedValue = data.get(1);
            String field =  jsonPathEvaluator.get(ExpectedField).toString();
            Assert.assertTrue(field.equalsIgnoreCase(ExpectedValue));
        }
    }
}