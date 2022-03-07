package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {

       /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    @Test
    public void test16(){

        //1) URL OLUSTURMA
        spec04.pathParams("bir", "todos", "iki", 7);

        //2) EXPECTED(BEKLENEN) DATA OLUSTUR

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 7);
        expectedData.put("title", "illo expedita consequatur quia in");
        expectedData.put("completed", false);

        System.out.println("EXPECTED DATA: " + expectedData);
        //EXPECTED DATA: {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        //3) REQUEST VE RESPONSE

        //https://jsonplaceholder.typicode.com
        Response response = given().spec(spec04).when().get("/{bir}/{iki}");
        //"/{bir}/{iki}" -> adrese bunu ekle /todos/7
        //https://jsonplaceholder.typicode.com/todos/7

        response.prettyPrint();

        //DATAYI JSON FORMATI -> JAVA'YA: De-Serialization
        //DATAYI JAVA'DAN -> JSON'A: Serialization

        Map<String, Object> actualData = response.as(HashMap.class);    //De-Serialization YAPTIK

        /*

    De-Serialization aşağıdaki JSON formatındaki datayı map'e dönüştür.
    {
    "userId": 1,
    "id": 7,
    "title": "illo expedita consequatur quia in",
    "completed": false
    }

    JSON DATASININ MAP'E DÖNÜŞMÜŞ HALİ
    ACTUAL DATA: {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}
         */

        System.out.println("ACTUAL DATA: " + actualData);
        //ACTUAL DATA: {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
