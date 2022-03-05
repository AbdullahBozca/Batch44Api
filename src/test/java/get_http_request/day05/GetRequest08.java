package get_http_request.day05;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends DummyBaseUrl {
    /*
     http://dummy.restapiexample.com/api/v1/employees url'inde bulunan

    1) Butun calisanlarin isimlerini consola yazdıralim
    2) 3. calisan kisinin ismini konsola yazdıralim
    3) Ilk 5 calisanin adini konsola yazdiralim
    4) En son calisanin adini konsola yazdiralim
    */
    @Test
    public void test(){
        spec02.pathParams("parametre01","api","parametre02","v1","parametre03","employees");
        Response response=given().spec(spec02).when().get("/{parametre01}/{parametre02}/{parametre03}");
        response.prettyPrint();
        JsonPath json=response.jsonPath();
        System.out.println("Calisan isimleri : "+json.getString("data.employee_name"));
        System.out.println("3. calisan : "+json.getString("data[2].employee_name"));
        //3) Ilk 5 calisanin adini konsola yazdiralim
        for (int i = 0; i < 5; i++) {
            System.out.println(i+1+". calisan : "+json.getString("data[" + i + "].employee_name"));
        }
        //4) En son calisanin adini konsola yazdiralim
        System.out.println("Son calisan isim : "+json.getString("data[-1].employee_name"));

    }
}
