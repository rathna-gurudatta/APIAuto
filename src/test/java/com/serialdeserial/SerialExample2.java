package com.serialdeserial;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.CityBody;
import pojo.PostRequestBody;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerialExample2 {

    @Test(description = "Passing form parameters")
    void serializationExamplePost() {
        List<String> languagesList = new ArrayList<>();
        languagesList.add("Java");
        languagesList.add("Python");

        CityBody cityPayload1 = new CityBody();
        cityPayload1.setCityName("bangalore");
        cityPayload1.setCityTemperature("30");

        CityBody cityPayload2 = new CityBody();
        cityPayload2.setCityName("delhi");
        cityPayload2.setCityTemperature("60");

        List<CityBody> citiesList = new ArrayList<>();
        citiesList.add(cityPayload1);
        citiesList.add(cityPayload1);

        PostRequestBody obj = new PostRequestBody();
        obj.setJob("Leader");
        obj.setName("Morpheus");
        obj.setLanguages(languagesList);
        obj.setCityPayload(citiesList);

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
}
