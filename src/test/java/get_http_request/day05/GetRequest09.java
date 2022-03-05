package get_http_request.day05;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetRequest09 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
    1) Matcher CLASS ile
    2) JsonPath ile dogrulayin.
    */
    @Test
    public void test(){
        spec02.pathParams("parametre01","api","parametre02","v1","parametre03","employee","parametre04","12");
        Response response=given().spec(spec02).get("/{parametre01}/{parametre02}/{parametre03}/{parametre04}");

        response.prettyPrint();
        response.then().
                assertThat().
                body("data.employee_name",equalTo("Quinn Flynn"),
                        "data.employee_salary",equalTo(342000),
                        "data.employee_age",equalTo(22));

        JsonPath json=response.jsonPath();
        System.out.println("12. : "+json.getString("data"));
        Assert.assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        Assert.assertTrue(json.getInt("data.employee_salary")==342000);
        Assert.assertTrue(json.getInt("data.employee_age")==22);

    }
}
