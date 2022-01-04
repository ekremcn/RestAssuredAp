package goRest.model;

import goRest.model.model.User;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.groovy.tools.shell.IO;
import org.testng.annotations.Test;
import pojo.ToDo;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class goRestTests {


    private int userId;

    @Test(enabled = false)
    public void getUsers() {

        List<User> userList = given()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .log().body()
                //assertions
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data", not(empty()))
                .extract().jsonPath().getList("data", User.class);

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test(enabled = false)
    public void extractingMultipleValues() {

        ExtractableResponse<Response> extract = given()
                .when()
                .get("https://gorest.co.in/public-api/users")
                .then()
                .log().body()
                //assertions
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data", not(empty()))
                .extract();


        int code = extract.jsonPath().getInt("code");
        System.out.println("code" + code);


//        List<User> userlist = extract.jsonPath().getList("data", User.class);
//        for (User user : userlist) {
//            System.out.println(user);
//        }

//        User[] data = extract.jsonPath().getObject("data",User[].class);
//        for (int i = 0; i < data.length; i++) {
//            System.out.println(data[i]);
//        }

        //extracting certain user only

        User user = extract.jsonPath().getObject("data[4]", User.class);
        System.out.println(user);
    }

    @Test
    public void createUser() {
        userId = given()
                //prerequisite data
                .header("Authorization", "Bearer 21fbbc771013c588366e85a5868b115fbeaaf35c4d16317e688158a7198e3ea6")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"ekoliiiiii\", \"gender\":\"male\", \"email\":\"" + getRandomEmail() + "\", \"status\":\"active\"}")
                .when()
                //action
                .post("https://gorest.co.in/public-api/users/")
                .then()
                // validations
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(201))
                .extract().jsonPath().getInt("data.id");
    }

    @Test(dependsOnMethods = "createUser")
    public void getUserById() {
        //get access to User Id
        given()
                .pathParam("userId", userId)
                .when()
                .get("https://gorest.co.in/public-api/users/{userId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data.id", equalTo(userId))
        ;
        // System.out.println(userId);
    }

    //task3, create a test to update the user

    @Test (dependsOnMethods = "createUser")
    public void updateUserbyId() {
        String updateText= "Update User Test";
        given()
                //prerequisite data
                .header("Authorization", "Bearer 21fbbc771013c588366e85a5868b115fbeaaf35c4d16317e688158a7198e3ea6")
                .contentType(ContentType.JSON)
                .body("{\"name\":\""+updateText+"\"}")
                .pathParam("userId", userId)
                .when()
                //action
                .put("https://gorest.co.in/public-api/users/{userId}")
                .then()
                // validations
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(200))
                .body("data.name", equalTo(updateText))
        ;
    }

    @Test (dependsOnMethods = "createUser", priority = 1)
    public void deleteUserbyId() {

        given()
                //prerequisite data
                .header("Authorization", "Bearer 21fbbc771013c588366e85a5868b115fbeaaf35c4d16317e688158a7198e3ea6")
                .pathParam("userId", userId)
                .when()
                //action
                .delete("https://gorest.co.in/public-api/users/{userId}")
                .then()
                // validations
                .statusCode(200)
                .body("code", equalTo(204))
                ;
    }
    @Test(dependsOnMethods = "deleteUserbyId")
    public void getUserByIdNegativeTest() {
        //get access to User Id
        given()
                .pathParam("userId", userId)
                .when()
                .get("https://gorest.co.in/public-api/users/{userId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("code", equalTo(404))

        ;

    }

    private String getRandomEmail() {
        return RandomStringUtils.randomAlphabetic(8) + "enazi.ramakrishnauuu@15ce.com";
    }

}