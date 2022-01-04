import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

//Task1: create a test that checks if /tr/34840 places is not empty

public class TaskHamcrestNotEmpty {

    @BeforeClass
    public void setup() {
        baseURI = "http://api.zippopotam.us/";
    }

    @Test
    public void testingJsonPathArrayIsNotEmpty() {
        given()
                .when()
                .get("/tr/34840")
                .then()
                .log().body()
                .body("places", not(empty()))
        ;

    }




}
