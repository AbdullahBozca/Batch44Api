package get_http_request.day13;

import base_url.DummyBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestPojo01 extends DummyBaseUrl {
    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }

*/
    @Test
    public void test(){
        //1) URL OLUSTUR
        spec02.pathParams("bir", "api", "iki", "v1", "uc", "employee", "dort", 1);

        //2) EXPECTED DATA
        Data data = new Data(1, "Tiger Nixon", 320800, 61,"");
        System.out.println("data = " + data);

        DummyPojo expextedData = new DummyPojo("success", data, "Successfully! Record has been fetched.");
        System.out.println("expextedData = " + expextedData);

        //3) REQUEST ve RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec02)
                            .when()
                            .get("/{bir}/{iki}/{uc}/{dort}");

        response.prettyPrint();

        //4) DOĞRULAMA
        DummyPojo actual=response.as(DummyPojo.class);
        Assert.assertEquals(expextedData.getStatus(),actual.getStatus());
        Assert.assertEquals(expextedData.getData().getId(),actual.getData().getId());
        Assert.assertEquals(expextedData.getData().getEmployee_name(),actual.getData().getEmployee_name());
        Assert.assertEquals(expextedData.getData().getEmployee_salary(),actual.getData().getEmployee_salary());
        Assert.assertEquals(expextedData.getData().getEmployee_age(),actual.getData().getEmployee_age());
        Assert.assertEquals(expextedData.getData().getProfile_image(),actual.getData().getProfile_image());
        Assert.assertEquals(expextedData.getMessage(),actual.getMessage());


        //Serialization -> Java yapısındaki datayı JSON formatına dönüştürme
        Gson gson = new Gson();
        String jsonFromJava = gson.toJson(actual);
        System.err.println("jsonFromJava = " + jsonFromJava);
        //jsonFromJava = {
        // "status":"success",
        // "data":{"id":1,
        // "employee_name":"Tiger Nixon",
        // "employee_salary":320800,
        // "employee_age":61,
        // "profile_image":""},
        // "message":"Successfully! Record has been fetched."}
    }
}