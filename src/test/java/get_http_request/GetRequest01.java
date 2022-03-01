package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    @Test
    public void test01() {
        String url="https://restful-booker.herokuapp.com/booking";
        Response response=given().when().get(url+"/2");//gidilen url deki bilgileri reponse değişkenine atar
        response.prettyPrint();//response variable ındaki dataları yazdırır.

    }
}
