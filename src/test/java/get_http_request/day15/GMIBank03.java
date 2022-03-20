package get_http_request.day15;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.WriteToText;

import static io.restassured.RestAssured.given;

public class GMIBank03 extends GMIBankBaseUrl {
        /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin
    First Name,
    Last Name,
    email,
    mobilePhone,
    Adres
    city
    Bilgilerini text dosyasına yazdırın
     */

    @Test
    public void test() throws JsonProcessingException {
        Customer [] customers;

        spec03.pathParam("parametre1", "tp-customers");

        Response response = given().headers("Authorization", "Bearer " + generateToken())
                .when().spec(spec03).get("/{parametre1}")
                .then().contentType(ContentType.JSON).extract().response();

        response.prettyPrint();

        ObjectMapper obj = new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);

        String fileName = "src/test/java/get_http_request/day15/GMIBankTextData/CustomesData.txt";
        WriteToText.saveCustomersData(fileName, customers);
    }


}