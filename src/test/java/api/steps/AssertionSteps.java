package api.steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.ValidatableResponse;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.skyscreamer.jsonassert.JSONAssert;
import utils.Helper;

import static org.testng.Assert.assertEquals;

public class AssertionSteps {
    private Helper helper;
    private ValidatableResponse json;

    public AssertionSteps(final Helper helper) {
        this.helper = helper;
    }

    @Then("^The response status code should be (\\d+)$")
    public void theStatusCodeShouldBe(final int status) {
        System.out.println(helper.getResponse().body().prettyPrint());
        assertEquals(helper.getResponse().getStatusCode(), status);
    }

    @And("response includes the following in any order")
    public void response_contains_in_any_order(DataTable responseFields){
        helper.validateResponseBody(responseFields);
    }

    @And("^validate response JSON is the following$")
    public void validateResponseIsTheFollowing(String expectedJson){
        try {
            JSONAssert.assertEquals(expectedJson,helper.getResponse().getBody().asString(),false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
