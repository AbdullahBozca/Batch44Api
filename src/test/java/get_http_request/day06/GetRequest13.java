package get_http_request.day06;

import base_url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends GMIBankBaseUrl {
    /*
        http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
    “firstName”: “Della”,
    “lastName”: “Heaney”,
    “mobilePhoneNumber”: “123-456-7893”,
    “address”: “75164 McClure Stream”,
    “country” : “USA”
    “state”: “New York43"
    “CREDIT_CARD”,hesabında 69700$ ,
    “CHECKING” hesabında 11190$
     */
    @Test
    public void test(){
        spec03.pathParams("parametre01","tp-customers","parametre02","114351");
        Response response=given().spec(spec03).
                header("Authorization","Bearer "+generateToken()).
                when().
                get("{parametre01}/{parametre02}");

        response.prettyPrint();
    }
}
