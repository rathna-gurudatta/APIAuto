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
import org.testng.asserts.SoftAssert;
import utils.JsonReader;
import utils.PropertyReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



public class getUsers {

    @Test
    void getUserData(){

        /*
        equalTo, contains, ContainsString, hasItems, hasSize
         */
        given().when().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200);

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given().
                when().get("/todos/1").
                then().
                assertThat().statusCode(200).
                body(not(isEmptyString())).
                body("title", equalTo("delectus aut autem")).body("id", equalTo(1));


        Response response = given().
                when().
                get("/posts").
                then().extract().response();
        //System.out.println(response);
        //System.out.println(response.asPrettyString());
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));


        response = given().
                when().
                get("/comments?postId=1").
                then().extract().response();

        List<String> expectedList = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedList.toArray(new String[0])) );

    }

    @Test
    void exampleUsingIsMatchers() {

        RestAssured.baseURI = "https://reqres.in";

        Response response = given().
                when().get("/api/users?page=2").
                then().statusCode(200).extract().response();

        response.then().body("data", hasSize(6));

        response.then().body("data[0].id", equalTo(7));
        response.then().body("data[0].email", is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name", is("Michael"));
        response.then().body("data[0].last_name", is("Lawson"));
        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));


    }

    @Test(description = "Passing path parameters")
    void passPathParam() {
        int yearValue = 2017;
        RestAssured.baseURI = "http://ergast.com";

        Response response = given().
                pathParam("yearKey", yearValue).
                when().get("/api/f1/{yearKey}/circuits.json").
                then().extract().response();

        //response.prettyPrint();
        System.out.println(response.body().asString());
    }

    @Test
    void exampleUsingMultipleQueryParams(){

        /*
        equalTo, contains, ContainsString, hasItems, hasSize
         */

        RestAssured.baseURI  = "https://reqres.in";

        Response response  = given().
                queryParam("page", 2).
                queryParam("per_page", 1).
                when().get("/api/users").
                then().extract().response();
        //response.prettyPrint();

    }

    @Test(description = "Passing form parameters")
    void passFormParam() {
        //RestAssured.baseURI = "https://reqres.in/api";

        Response response  = given().
                contentType("application/x-www-form-urlencoded").
                formParam("name", "Testtg user").
                formParam("job", "Developer").
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                extract().response();
        System.out.println(response.body().asString());
    }

    @Test
    public void testWithMultipleHeader() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";

        Response response = given()
                .header("Content-Type","application/json")
                .header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
                .when()
                .get("")
                .then()
                .statusCode(200)
                .extract()
                .response();

        response.prettyPrint();
    }

    @Test(description = "Passing form parameters")
    void passMultipleHeadersWithMap() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type","application/json");
        headersMap.put("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx");


        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
                given()
                        .headers(headersMap)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();


    }

    @Test(description = "Passing form parameters")
    void testResponseHeaders() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type","application/json");
        headersMap.put("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx");


        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
                given()
                        .headers(headersMap)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders.size());
        for(Header item : responseHeaders){
            if(item.getName().equals("Server")) {
                System.out.println(item.getName() + " --> " + item.getValue());
                assertEquals(item.getValue(), "cloudflare", "Actual value is " + item.getValue());
            }
        }
    }

    @Test(description = "Passing form parameters")
    void passCookiesIndividually() {

        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
                given()
                        .cookie("cookie1", "value1")
                        .cookie("cookie2", "value2")
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        //response.prettyPrint();
    }

    @Test(description = "Passing form parameters")
    void passCookiesUsingCookieBuilder() {

        RestAssured.baseURI = "https://reqres.in/api";
        Cookie cookieObj = new Cookie.Builder("cookie3", "value3").setComment("Test using cookies").build();
        Response response =
                given()
                        .cookie(cookieObj)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        //response.prettyPrint();
    }

    @Test(description = "Passing form parameters")
    void getCookiesFromResponse() {

        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
                given()
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        Map<String, String> cookiesObj = response.getCookies();
        //assertThat(cookiesObj, hasKey("JSESSIONID"));
        //assertThat(cookiesObj, hasValue("ABCDEF123456"));
        //response.prettyPrint();

        Cookies cookiesObj2 = response.getDetailedCookies();
        cookiesObj2.getValue("Server");
        assertEquals(cookiesObj2.getValue("Server"), "CloudFlare");
    }

    @Test(description = "Passing form parameters")
    void digetAuthExample() {

        RestAssured.baseURI = "https://postman-echo.com";
        Response response =
                        given()
                        .auth()
                        .digest("postman", "password")
                        .when()
                        .get("/basic-auth") ;
        assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
    }

    @Test
    void digestAuthExample() throws IOException, ParseException {

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

    @Test
    void deleteMethodExample() {

        RestAssured.baseURI = "https://reqres.in/api";
        Response response =
                given().delete("/users/7");
        assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT.code);
        System.out.println(response.getStatusCode());
    }

    @Test
    void propFileExample() throws IOException, ParseException {

        RestAssured.baseURI = PropertyReader.propertyReader("config.properties", "server");
        Response response =
                given()
                        .auth()
                        .digest(JsonReader.getTestData("username"),JsonReader.getTestData("password"))
                        .when()
                        .get("/digest-auth") ;
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        response.prettyPrint();
    }

    @Test
    void softAssertExample() throws IOException, ParseException {

        RestAssured.baseURI = PropertyReader.propertyReader("config.properties", "server");
        Response response =
                given()
                        .auth()
                        .digest(JsonReader.getTestData("username"),JsonReader.getTestData("password"))
                        .when()
                        .get(JsonReader.getTestData("digestEndPoint")) ;
        SoftAssert obj = new SoftAssert();
        obj.assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT.code);
        obj.assertAll();
    }

    @Test(dataProvider = "gridsData", dataProviderClass = DataProviderExample.class)
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
