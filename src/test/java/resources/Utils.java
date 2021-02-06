package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;
    public RequestSpecification requestSpecification() throws IOException {
        if (reqSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }
        return reqSpec;
    }

    public String getGlobalValue(String value) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/Global.properties");
        properties.load(fileInputStream);
       String getValue = properties.getProperty(value);
        return getValue;
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        String getJsonString = jsonPath.get(key).toString();
        return getJsonString;
    }
}
