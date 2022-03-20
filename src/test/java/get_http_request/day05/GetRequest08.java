package get_http_request.day05;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyBaseUrl {
    /*
   http://dummy.restapiexample.com/api/v1/employees url’inde bulunan

  1) Butun calisanlarin isimlerini consola yazdıralim
  2) 3. calisan kisinin ismini konsola yazdıralim
  3) Ilk 5 calisanin adini konsola yazdiralim
  4) En son calisanin adini konsola yazdiralim
*/

    @Test
    public void test08(){

        spec02.pathParams("first", "api", "second", "v1", "third", "employees");

        // http://dummy.restapiexample.com
        Response response = given().spec(spec02).when().get("/{first}/{second}/{third}");
        // "/{first}/{second}/{third}" => /api/v1/employees

        //response.prettyPrint();

        //1) Butun calisanlarin isimlerini consola yazdıralim
        JsonPath json = response.jsonPath();
        System.out.println(json.getString("data.employee_name"));

        //2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(json.getString("data[2].employee_name"));


        //3) Ilk 5 calisanin adini konsola yazdiralim

        //1. Yol
        for (int i = 0; i < 5; i++) {
            System.out.println(i+1 + ". calisan: " +json.getString("data[" + i + "].employee_name"));
        }

        //2. Yol
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));

        //3. Yol
        System.out.println(json.getList("data[0,1,2,3,4].employee_name"));

        //4) En son calisanin adini konsola yazdiralim
        System.out.println(json.getString("data[-1].employee_name"));
        System.out.println(json.getString("data.employee_name[-1]"));
    }
}