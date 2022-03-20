package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
    }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": 201
    }
    */
    @Test
    public void test03(){

        //1) URL OLUSTUR
        spec04.pathParam("bir", "todos");

        //2) EXPECTED DATA
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPostData();
        System.out.println("expectedRequest = " + expectedRequest);

        //3) REQUEST VE RESPONSE
        Response response = given()
                .spec(spec04)
                .contentType(ContentType.JSON)
                .body(expectedRequest.toString())
                .when()
                .post("/{bir}");
        response.prettyPrint();

        //4) DOGRULAMA
        //1. MATCHERS CLASS

        response.then().assertThat().statusCode(expectedRequest.getInt("statusCode"))
                .body("userId",equalTo(expectedRequest.get("userId"))
                        , "title",equalTo(expectedRequest.get("title"))
                        , "completed",equalTo(expectedRequest.get("completed"))
                        , "id",equalTo(expectedRequest.get("id")));

        //2. Json Path
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.get("id"), json.getInt("id"));
        Assert.assertEquals(expectedRequest.get("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"), json.getInt("statusCode"));
        Assert.assertEquals(expectedRequest.get("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.get("completed"), json.getBoolean("completed"));

        //3. De-Serialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedRequest.getBoolean("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedRequest.getInt("id"), actualDataMap.get("id"));
        Assert.assertEquals(expectedRequest.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"), response.statusCode());
    }
}