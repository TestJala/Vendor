package utils;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.*;

/**
 * Class containing Helper Steps.
 */
public class Helper {

    private Map<String, String> storageMap;
    private Response response;
    private User user = User.getUser();

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
            if(ExpectedValue.startsWith("&userId"))ExpectedValue=user.getUserField("userId");
            if(ExpectedValue.startsWith("&username"))ExpectedValue=user.getUserField("userName");
            String field =  jsonPathEvaluator.get(ExpectedField).toString();
            Assert.assertTrue(field.equalsIgnoreCase(ExpectedValue));
        }
    }

    public void setUser(DataTable requestData) {
        for (List<String> data:requestData.raw()) {
            String Field = data.get(0);
            String Value = data.get(1);
            user.setUserField(Field,Value);
        }
    }

    @Deprecated
    public Map<String, String> AddUserIdToTable(Map<String, String> requestData) {
        Map table=new HashMap();
        table.put("userId",user.getUserField("userId"));
        for (Map.Entry<String, String> entry : requestData.entrySet()) {
            table.put(entry.getKey(),entry.getValue());
        }
        return table;
    }

    public void setUserIdUsername(Response result) {
        JsonPath jsonPathEvaluator = result.jsonPath();
        user.setUserField("userId",jsonPathEvaluator.get("userId").toString());
        user.setUserField("userName",jsonPathEvaluator.get("userName").toString());
    }

    public void setUserPasswordAndPin(Map<String, String> requestData) {
        user.setUserField("password",requestData.get("password"));
        user.setUserField("passwordRecoveryPin",requestData.get("passwordRecoveryPin"));
    }

    public Map<String,String> evaluateFields(Map<String, String> requestData){
        Map<String,String> map=new HashMap<String, String>();
        for (String field : requestData.keySet()) {
            map.put(field,requestData.get(field));
            if (requestData.get(field).equalsIgnoreCase("&userId")) {
                map.replace(field, user.getUserField("userId"));
            }
            if (requestData.get(field).equalsIgnoreCase("&username")) {
                map.replace(field, user.getUserField("userName"));
            }
        }
        System.out.println(map);
        return map;
    }
}