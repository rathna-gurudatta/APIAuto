package com.ergast;

import core.BaseTest;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import userManagement.DataProviderExample;

import static io.restassured.RestAssured.given;

public class DataProviderTest extends BaseTest {

    @DataProvider(name = "gridsData")
    public Object[][] testData(){
        return new Object[][] {
                {"1", "John"},
                {"2", "Kite"}
        };
    }

    @Test(dataProvider = "gridsData", dataProviderClass = DataProviderExample.class, groups = "RegressionSuite")
    @Parameters({"id", "name"})
    void testPIAGrids(String id, String name){
        RestAssured.baseURI = "https://reqres.in/api";
        given()
                .param("id", id)
                .param("name", name)
                .when()
                .get("/users/7")
                .then()
                .statusCode(200);
    }
}
