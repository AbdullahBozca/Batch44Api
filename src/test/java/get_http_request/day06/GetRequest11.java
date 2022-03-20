package get_http_request.day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest11 {

    String endPoint = "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDQ0YXBpIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NDcwOTgyMDR9.MQksKVvs4cqHbVngthUJSwGVf04pg2Iw4zcFhDXKy5whW4fjBaYa5Ujz2qcx9x__K-BOxvff58HI6q9oIzHPOw";

    @Test
    public void test(){

        Response response = given().accept(ContentType.JSON)
                .header("Authorization","Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}