package get_http_request.day14;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper02 extends HerOkuAppBaseUrl {
    /*
 https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
 status kodun 200 ve response body’nin
{
"firstname": "Ali",
"lastname": "Can",
"totalprice": 500,
"depositpaid": true,
"bookingdates": {
"checkin": "2022-03-01",
"checkout": "2022-03-11"
},
"additionalneeds": "Breakfast"
}

Olduğunu Object Mapper kullanarak test edin.
*/
    @Test
    public void test(){
        //1) URL OLUSTUR
        spec05.pathParams("param1","booking", "param2", 13);

        //2) EXPECTED DATA
        String jsonData = "{\n" +
                "\"firstname\": \"Ali\",\n" +
                "\"lastname\": \"Can\",\n" +
                "\"totalprice\": 500,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2022-03-01\",\n" +
                "\"checkout\": \"2022-03-11\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";

        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.err.println("expectedData = " + expectedData);

        //3) Request ve Response

        Response response = given().contentType(ContentType.JSON).spec(spec05)
                .when().get("/{param1}/{param2}");

        response.prettyPrint();

        //4) DOĞRULAMA
        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("bookingdates.checkin"),actualData.get("bookingdates.checkin"));
        Assert.assertEquals(expectedData.get("bookingdates.checkout"),actualData.get("bookingdates.checkout"));
        Assert.assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),
                ((Map)actualData.get("bookingdates")).get("checkout"));


        //Matcher ile
        response.then().
                assertThat().
                statusCode(200).
                body("firstname", equalTo(expectedData.get("firstname"))
                        ,"lastname",equalTo(expectedData.get("lastname"))
                        ,"totalprice",equalTo(expectedData.get("totalprice"))
                        ,"depositpaid",equalTo(expectedData.get("depositpaid"))
                        ,"bookingdates.checkin",equalTo(((Map)expectedData.get("bookingdates")).get("checkin"))
                        ,"bookingdates.checkout",equalTo(((Map)expectedData.get("bookingdates")).get("checkout")));
    }
}