package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PassReqBodyAsStringTest {

    @Test
    public void sendReqBodyAsStringPost(){
        RestAssured.baseURI = "https://reqres.in/api";
        Response response  =
                given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"User1\",\"job\":\"Tester\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println(response.body().asString());
    }

    @Test
    public void sendReqBodyAsStringPut(){
        RestAssured.baseURI = "https://reqres.in/api";
        Response response  =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\"name\":\"User2\",\"job\":\"Developer\"}")
                        .when()
                        .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }


    @Test
    public void sendReqBodyAsStringPatch(){
        RestAssured.baseURI = "https://reqres.in/api";
        Response response  =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\"name\":\"User3\",\"job\":\"Carpenter\"}")
                        .when()
                        .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.body().asString());
    }
}
