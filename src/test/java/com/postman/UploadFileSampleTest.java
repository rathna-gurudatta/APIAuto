package com.postman;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import java.io.File;

import static io.restassured.RestAssured.given;


public class UploadFileSampleTest {

    @Test(description="Testing File upload", groups = {"SmokeSuite", "EndToEndTest"})
    public void FileUploadExample(){
        ExtentReport.extentlog = ExtentReport.extentreport.startTest("FileUploadExample", "Validate File upload");
        File file = new File("resources/TestData/demo.txt");
//        Response response =
//                given()
//                        .multiPart(file)
//                        .when().post("https://reqres.in/api/users");
    }
}
