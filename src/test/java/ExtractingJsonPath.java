import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Location;

import java.util.concurrent.BlockingDeque;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class ExtractingJsonPath {

    @BeforeClass
    public void setup() {
        baseURI = "http://api.zippopotam.us/";
    }


    @Test
    public void testingJsonPathAsPojo() {
       Location location= given()
                .when()
                .get("/tr/34840")
                .then()
                .log().body()
                .extract().as(Location.class)
        ;

        System.out.println(location);

    }
}
