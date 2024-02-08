package com.serialdeserial;


import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.CityBody;
import pojo.PostRequestBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DSExampleOne {
    @Test
    public void validatePatchWithResponsePojo() {
        String job = "morpheus";
        PostRequestBody  patchRequest = new PostRequestBody();
        patchRequest.setJob(job);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/596");
        PostRequestBody responseBody = response.as(PostRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), job);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }


}

