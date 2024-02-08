package com.postman;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostmanEchoTest {
    @Test(description="Testing digest auth", groups = "RegressionSuite")
    void digestAuthExample() throws IOException, ParseException {
        ExtentReport.extentlog = ExtentReport.extentreport.startTest("digestAuthExample", "Validate Digest Auth");

        RestAssured.baseURI = "https://postman-echo.com";
        Response response =
                given()
                        .auth()
                        .digest(JsonReader.getTestData("username"),JsonReader.getTestData("password"))
                        .when()
                        .get("/digest-auth") ;
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        response.prettyPrint();
    }
}
