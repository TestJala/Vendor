package api.steps;

import client.api.RequestManager;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.Helper;
import utils.RequestBuilder;

public class RequestSteps {
    private Helper helper;
    private Response response;

    public RequestSteps(final Helper helper) {
        this.helper = helper;
    }

    @Given("^A POST request to \"([^\"]*)\" endpoint with the following values$")
    public void aPOSTRequestToEndpointWithTheFollowingValues(final String endpoint, final DataTable requestData){
        JSONObject body = RequestBuilder.build(requestData);
        response = RequestManager.postJson(endpoint, body);
        helper.setResponse(response);
    }
}