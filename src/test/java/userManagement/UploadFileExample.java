package userManagement;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.File;

import static io.restassured.RestAssured.given;


public class UploadFileExample {

    @Test
    public void FileUploadExample(){
        File file = new File("resources/TestData/demo.txt");
        Response response =
                given()
                        .multiPart(file)
                        .when().post("https://reqres.in/api/users");
    }
}
