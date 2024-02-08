package userManagement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DataProviderExample {
    @DataProvider(name = "gridsData")
    public Object[][] testData(){
        return new Object[][] {
                {"1", "John"},
                {"2", "Kite"}
        };
    }


}
