package com.serialdeserial;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.PostRequestBody;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerialExample {
    @Test(description = "Passing form parameters")
    void serializationExamplePost() {
        //RestAssured.baseURI = "https://reqres.in/api";
        List<String> languagesList = new ArrayList<>();
        languagesList.add("Java");
        languagesList.add("Python");

        PostRequestBody obj = new PostRequestBody();
        obj.setJob("Leader");
        obj.setName("Morpheus");
        obj.setLanguages(languagesList);

        Response response  = given().
                header("Content-Type","application/json").
                body(obj).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                extract().response();
        System.out.println(response.body().asString());
    }

    @Test(description = "Passing form parameters")
    void serializationExamplePut() {
        //RestAssured.baseURI = "https://reqres.in/api";
        PostRequestBody patchObj = new PostRequestBody();
        patchObj.setJob("Tester");
        patchObj.setName("Rathna");

        Response response  = given().
                header("Content-Type","application/json").
                body(patchObj).
                when().
                put("https://reqres.in/api/users/7").
                then().
                statusCode(200).
                extract().response();
        System.out.println(response.body().asString());
    }
}
