package get_http_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    @Test
    public void test01() {
        String url="https://restful-booker.herokuapp.com/booking";
        Response response=given().when().get(url+"/2");//gidilen url deki bilgileri reponse değişkenine atar
        response.prettyPrint();//response variable ındaki dataları yazdırır.
        System.out.println("Status Code : "+response.statusCode());
        System.out.println("Content Type : "+response.contentType());
        System.out.println("Test zamanı : "+response.time());

        Assert.assertTrue(response.statusCode()==200);



    }
}
