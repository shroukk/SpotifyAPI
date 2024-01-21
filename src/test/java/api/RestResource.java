package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static api.Routes.API;
import static api.Routes.TOKEN;
import static api.SpecBuilder.*;
import static io.restassured.RestAssured.given;
public class RestResource {



    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccRequestSpec()).
                formParams(formParams)
                .when()
                .post(API+TOKEN)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public static Response post(String path, String token, Object requestPayload){
        return given(getRequestSpec()).
                 auth().oauth2(token)
                .body(requestPayload)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
    public static Response get(String path, String token){
        return given(getRequestSpec())
                .auth().oauth2(token).
        when().get(path).
        then().spec(getResponseSpec())
                .extract()
                .response();
    }
    public static Response update(String path, String token, Object requestPayload){
        return given(getRequestSpec()).
                auth().oauth2(token)
                .body(requestPayload)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response delete(String path, String token, Object requestPayload){
        return given(getRequestSpec()).
                auth().oauth2(token)
                .body(requestPayload)
                .when()
                .delete(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }



}
