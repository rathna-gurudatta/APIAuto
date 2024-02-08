package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PassReqBodyAsJson {

    private static FileInputStream passDataFromJsonFile(String fileName) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/resources/TestData/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }
    @Test
    public void sendReqBodyAsJsonPost() throws IOException {
        RestAssured.baseURI = "https://reqres.in/api";
        Response response  =
                given()
                        .header("Content-Type", "application/json")
                        .body(IOUtils.toString(passDataFromJsonFile("PostRequestJson.json")))
                        .when()
                        .post("/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());
    }

    @Test
    public void sendReqBodyAsJsonPatch() throws IOException {
        RestAssured.baseURI = "https://reqres.in/api";
        Response response  =
                given()
                        .header("Content-Type", "application/json")
                        .body(IOUtils.toString(passDataFromJsonFile("PatchRequestJson.json")))
                        .when()
                        .patch("users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }
}
