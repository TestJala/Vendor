package api.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utils.Helper;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;

public class AssertionSteps {
    private Helper helper;
    private ValidatableResponse json;

    public AssertionSteps(final Helper helper) {
        this.helper = helper;
    }

    @Then("^The response status code should be (\\d+)$")
    public void theStatusCodeShouldBe(final int status) {
        assertEquals(helper.getResponse().getStatusCode(), status);
    }

    @And("response includes the following in any order")
    public void response_contains_in_any_order(DataTable responseFields){
        Response response = helper.getResponse();

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        for (List<String> data:responseFields.raw()) {
            String ExpectedField = data.get(0);
            System.out.println(ExpectedField);
            String ExpectedValue = data.get(1);
            System.out.println(ExpectedValue);


            String field = jsonPathEvaluator.get(ExpectedField);
            System.out.println(field);
            Assert.assertEquals(field, ExpectedValue, "Correct value "+ExpectedField +" for field " + field);
        }
    }
}
