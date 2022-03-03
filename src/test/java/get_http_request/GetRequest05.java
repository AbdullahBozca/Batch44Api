package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest05 {

        /*
        https://jsonplaceholder.typicode.com/todos/123 url’ine
        accept type’i “application/json” olan GET request’i yolladigimda
        gelen response’un
		status kodunun 200
   		ve content type’inin “application/json”
		ve Headers’daki “Server” in “cloudflare”
		ve response body’deki “userId”’nin 7
		ve “title” in “esse et quis iste est earum aut impedit”
		ve “completed” bolumunun false oldugunu test edin
         */
        @Test
        public void test05(){
            String url ="https://jsonplaceholder.typicode.com/todos/123";
            Response response = given().when().get(url);
            response.prettyPrint();
            response.then()
                    .assertThat()
                    .contentType("application/json")
                    .statusCode(200)
                    .headers("Server", equalTo("cloudflare"), "Pragma", equalTo("no-cache"));
            response.then().assertThat().body("userId", equalTo(7),
                    "title", equalTo("esse et quis iste est earum aut impedit"),
                    "completed", equalTo(false));
        }
}
