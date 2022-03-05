package get_http_request.day05;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest06 {
    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine
accept type’i “application/json” olan GET request’i yolladigimda
gelen response’un
status kodunun 200
ve content type’inin “application/json”
ve firstname’in “Jim”
ve totalprice’in 600
ve checkin date’in 2015-06-12"oldugunu test edin
     */
    @Test
    public void test06(){
        Response response=given().when().get("https://restful-booker.herokuapp.com/booking/4");

        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType("application/json");

        response.then().assertThat().body("firstname", Matchers.equalTo("Jim")
                ,"totalprice",Matchers.equalTo(411),
                "bookingdates.checkin",Matchers.equalTo("2019-11-11"));


    }
}
