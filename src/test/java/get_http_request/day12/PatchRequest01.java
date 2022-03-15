package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest01 extends JsonPlaceHolderBaseUrl {
    /*
 https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
 {
  "title": "Batch44"
 }
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "userId": 10,
 "title": "Batch44"
 "completed": true,
 "id": 198
 }
 */
    @Test
    public void test() {

        //1) URL OLUSTUR
        spec04.pathParams("first", "todos", "second", 198);

        //2) EXPECTED DATA
        JsonPlaceHolderTestData testData = new JsonPlaceHolderTestData();
        JSONObject requestData = testData.setUpPatchRequestData();
        System.out.println("requestData = " + requestData);

        JSONObject expectedData = testData.setUpPatchExpectedData();
        System.out.println("expectedData = " + expectedData);

        //3) REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec04)
                            .body(requestData.toString())
                            .when()
                            .patch("/{first}/{second}");

        response.prettyPrint();

        //4) DOGRULAMA
        // 1) JSonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
        Assert.assertEquals(expectedData.get("id"), json.getInt("id"));

        //Matcher
        response.then().statusCode(200);
        response.then().body("title", equalTo(expectedData.getString("title"))
                ,"completed",equalTo(expectedData.getBoolean("completed"))
                ,"userId",equalTo(expectedData.getInt("userId"))
                ,"id",equalTo(expectedData.getInt("id")));

        // 3) De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("id"));
    }
}