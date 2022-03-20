package get_http_request.day15;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadText;
import utilities.WriteToText;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank02 extends GMIBankBaseUrl {
        /*
    http://www.gmibank.com/api/tp-customers end point'ine
    request gönderin

     1) Tüm Customer emaillerini ekrana yazdırın.

     2) Tüm Customer emaillerini text dosyası olarak kaydedin

     3) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
        E-maillerinin olduğunu doğrulayın
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

        //1) Tüm Customer emaillerini ekrana yazdırın.
        for (int i = 0; i<customers.length; i ++ )
            System.out.println(i+1 + ". Customers Email = " + customers[i].getEmail());

        //2) Tüm Customer emaillerini text dosyası olarak kaydedin
        String fileName = "src/test/java/get_http_request/day15/GMIBankTextData/EmailList.txt";
        WriteToText.saveEmailData(fileName, customers);

        //3) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
        //E-maillerinin olduğunu doğrulayın
        List<String> expectedEmails = new ArrayList<>();
        expectedEmails.add("winonaabernathy@gmail.com");
        expectedEmails.add("MerrillPrice@gmail.com");
        expectedEmails.add("LesleyKing@gmail.com");

        List<String> actualEmails = ReadText.readCustomerEmailList(fileName);
        Assert.assertTrue("E-mail ADRESLERi ESLESMiYOR", actualEmails.containsAll(expectedEmails));
    }
}