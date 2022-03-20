package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
Dönen response ‘un status kodunun 200 ve body kısmının
 {
 "userId": 10,
 "id": 198,
 "title": "quis eius est sint explicabo",
 "completed": true
 }

Olduğunu Object Mapper kullanarak test edin
*/

    @Test
    public void test(){
        //1) URL OLUŞTUR
        spec04.pathParams("param1", "todos", "param2", 198);

        //2) EXPECTED DATA

        String jsonData = " {\n" +
                " \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                " \"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                " }";

        Map<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, Map.class);
        System.err.println("jsonData = " + jsonData);
        System.out.println("expectedData = " + expectedData);

        //3) REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec04)
                            .when().get("/{param1}/{param2}");

        response.prettyPrint();

        //4) DOĞRULAMA
        //De-Serialization
        //Onceki derslerde ogrendigimiz yöntem
        HashMap<String, Object> actualData2 = response.as(HashMap.class);
        System.out.println("actualData2 = " + actualData2);

        //JsonUtill reuseable classını kullanarak de-serialization yaptık.
        Map<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //Matchers
        response.then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(expectedData.get("userId"))
                        ,"id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));

    }
}