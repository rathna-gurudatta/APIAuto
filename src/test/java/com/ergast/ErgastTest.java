package com.ergast;

import core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ErgastTest extends BaseTest {
    @Test(description = "Ergast path parameters", groups = {"SmokeSuite"})
    void passPathParam() throws InterruptedException {
        ExtentReport.extentlog = ExtentReport.extentreport.startTest("passPathParam", "Ergast path parameters");
        int yearValue = 2017;
        RestAssured.baseURI = "http://ergast.com";

        Response response = given().
                pathParam("yearKey", yearValue).
                when().get("/api/f1/{yearKey}/circuits.json").
                then().extract().response();
        assertEquals(response.getStatusCode(), 201);

        //response.prettyPrint();
        //Thread.sleep(20000);

        System.out.println(response.body().asString());
    }
}
