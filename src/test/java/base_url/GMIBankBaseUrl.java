package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.Authentication;

public class GMIBankBaseUrl extends Authentication {

    protected RequestSpecification spec03;

    @Before
    public void setUp(){

        spec03 = new RequestSpecBuilder().setBaseUri("https://www.gmibank.com/api").build();
    }
}
