package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.junit.Assert;
import resources.ResourcesAPI;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;

import static io.restassured.RestAssured.given;


public class StepDefinitions extends Utils {
    ResponseSpecification resSpec;
    RequestSpecification reqSpecific;
    Response response;
    TestDataBuild testData = new TestDataBuild();
    public static String place_id;


    @Given("Add Place payload with {string} {string} {string} {string} {string} {double}")
    public void add_place_payload(String name, String language, String address, String typesOne, String typesTwo, double lat) throws IOException {
        reqSpecific = given().spec(requestSpecification())
                .body(testData.addPlacePayload(name, language, address, typesOne, typesTwo, lat));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String requestType) throws IOException {
        ResourcesAPI resourcesAPI = ResourcesAPI.valueOf(resource);
        resourcesAPI.getResource();
        resSpec = new ResponseSpecBuilder().build();
        if(requestType.equalsIgnoreCase("Post")) {
            response = reqSpecific.when().post(resourcesAPI.getResource())
                    .then().spec(resSpec).extract().response();
        } else if( requestType.equalsIgnoreCase("Get")) {
            response = reqSpecific.when().get(resourcesAPI.getResource())
                    .then().spec(resSpec).extract().response();
        }
    }

    @Then("the API call gets success with status code {int}")
    public void the_api_call_gets_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        Assert.assertEquals(getJsonPath(response,keyValue), expectedValue);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        reqSpecific = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_post_http_request(resource, "Get");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("Delete Place payload")
    public void delete_place_payload() throws IOException {
        reqSpecific = given().spec(requestSpecification())
                .body(testData.deleteAddress(place_id));
    }
}
