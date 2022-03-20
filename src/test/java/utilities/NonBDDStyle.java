package utilities;


import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyle {

    @Test
    public void test01() {

        // Create a request specification
        RequestSpecification request = RestAssured.given();

        //Adding URI
        request.baseUri("http://dummy.restapiexample.com/api/v1/employees");

        // Calling GET method on URI. After hitting we get Response
        Response response = request.get();

        // Print response body.
        response.prettyPrint();

        ValidatableResponse valRes = response.then();
        // It will check if status code is 200
        valRes.statusCode(200);
        // It will check if status line is as expected
        valRes.statusLine("HTTP/1.1 200 OK");
    }
}
