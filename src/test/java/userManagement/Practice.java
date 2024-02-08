package userManagement;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonReader;
import utils.PropertyReader;

public class Practice {
    //Enum is a special class that represents group of constants
    @Test(dataProvider = "gridsData", dataProviderClass = DataProviderExample.class)
    @Parameters({"rollNum", "username"})
    void testPIAGrids(String rollNum, String username){
        RestAssured.baseURI = "https://reqres.in/api";
        given()
                .param("id", rollNum)
                .param("name", username)
                .when()
                .get("/users/7")
                .then()
                .statusCode(200);
    }
}
