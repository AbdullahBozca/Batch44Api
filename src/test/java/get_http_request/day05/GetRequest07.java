package get_http_request.day05;

import base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Array;

import static io.restassured.RestAssured.given;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends RegresinBaseUrl {
    @Test
    public void test(){
        spec01.pathParams("parametre1","api","parametre2","users");
        Response response=given().spec(spec01).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        response.then().
                assertThat().
                body("data[4].email", equalTo("charles.morris@reqres.in"),
                        "data[4].first_name",equalTo("Charles"),
                        "data[4].last_name",equalTo("Morris"),
                        "data[4].avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));

        //JsonPath
        JsonPath json=response.jsonPath();
        System.out.println("Email adresleri : "+json.getString("data.email"));
        System.out.println("İsimler : "+json.getString("data.first_name"));


        assertEquals("charles.morris@reqres.in",json.getString("data[4].email"));
        assertEquals("Charles",json.getString("data[4].first_name"));

    }
}
