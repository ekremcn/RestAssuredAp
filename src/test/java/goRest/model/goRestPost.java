package goRest.model;

import goRest.model.model.Posts;
import goRest.model.model.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class goRestPost {

    private int postId;
    private String token;
    private RequestSpecification requestSpec;

    @BeforeClass
    public void init(){
        token = "Bearer 21fbbc771013c588366e85a5868b115fbeaaf35c4d16317e688158a7198e3ea6";
        baseURI = "https://gorest.co.in/public-api/posts";

        requestSpec= new RequestSpecBuilder()
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void getPosts() {

        List<Posts> postsList = given()
                .when()
                .get()
                .then()
                .spec(getStatusCodeSpec(200))
                .body("data", not(empty()))
                .extract().jsonPath().getList("data", Posts.class);

        for (Posts posts : postsList) {
            System.out.println(posts);
        }
    }

    @Test
    public void createPosts() {
        Posts post= new Posts();
        post.setUser_id(4);
        post.setTitle("Hello Dud");
        post.setBody("naber");

        postId = given()
                //prerequisite data
                .spec(requestSpec)
                .body(post)
                .when()
                //action
                .post()
                .then()
                .log().body()
                // validations
                .spec(getStatusCodeSpec(201))
                .extract().jsonPath().getInt("data.id");

        System.out.println(postId);
    }


    @Test (dependsOnMethods =  "createPosts")
    public void getPostById() {
        //get access to User Id
        given()
                .pathParam("postId", postId)
                .when()
                .get("/{postId}")
                .then()
                .spec(getStatusCodeSpec(200))
                .body("data.id", equalTo(postId))
        ;
    }

 //task3, create a test to update the user

    @Test(dependsOnMethods = "createPosts")
    public void updatePostbyId() {
        String updateText = "Update Post Test";
        Map<String, String> body = new HashMap<>();
        body.put("body", updateText);

        given()
                //prerequisite data
                .spec(requestSpec)
                .body(body)
                .pathParam("postId", postId)
                .when()
                //action
                .put("/{postId}")
                .then()
                // validations
                .spec(getStatusCodeSpec(200))
                .body("data.body", equalTo(updateText))
        ;
    }

    @Test(dependsOnMethods = "createPosts", priority = 1)
    public void deletePostbyId() {

        given()
                //prerequisite data
                .spec(requestSpec)
                .pathParam("postId", postId)
                .when()
                //action
                .delete("/{postId}")
                .then()
                // validations
               .spec(getStatusCodeSpec(204))
        ;
    }

    @Test(dependsOnMethods = "deletePostbyId")
    public void getPostByIdNegativeTest() {
        //get access to User Id
        given()
                .pathParam("postId", postId)
                .when()
                .get("/{postId}")
                .then()
                .spec(getStatusCodeSpec(404))
        ;

    }

    private ResponseSpecification getStatusCodeSpec(Integer statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody("code", equalTo(statusCode))
                .build();

    }
}