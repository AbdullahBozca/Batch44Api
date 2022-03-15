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

public class PutRequest01 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body'İ PUT ettiğinizde

    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false
    }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false,
    "id": 198
    }
    */

    @Test
    public void test(){
        //1) URL OLUSTUR
        spec04.pathParams("bir","todos","iki","198");

        //2) EXPECTED DATA
        JsonPlaceHolderTestData testObje = new JsonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);
        //expectedRequest = {"completed":false,"title":"Wash the dishes","userId":21}
        //TEST DATA İÇERİSİNDEKİ VERİLER

        //3) REQUEST VE RESPONSE
        Response response = given().contentType(ContentType.JSON)
                .spec(spec04)
                .body(expectedRequest.toString())
                .when()
                .put("/{bir}/{iki}");
        response.prettyPrint();


        //4) DOGRULAMA
        //JSON PATH
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"), json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"), json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"), json.getBoolean("completed"));

        // De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedRequest.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("userId"), actualData.get("userId"));
    }
}
