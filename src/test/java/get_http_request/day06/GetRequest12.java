package get_http_request.day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends Authentication {
    String endPoint = "http://www.gmibank.com/api/tp-customers";
    @Test
    public void test(){
        Response response=given().
                header("Authorization","Bearer "+generateToken()).
                when().get(endPoint).
                then().extract().response();
        response.prettyPrint();
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);
    }
}
