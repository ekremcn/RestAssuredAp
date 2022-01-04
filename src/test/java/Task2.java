import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.ToDo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task2 {
    /* Task 2
     * create a request to https://httpstat.us/418
     * expect status 418
     * expect content type text
     * expect body to be equal to "418 I'm a teapot"
     */

    @Test
    public void statusCodeTest() {
//        String body=given().
//                when().
//                get("https://httpstat.us/418").
//                then().
//                contentType(ContentType.TEXT)
//                .statusCode(418)
//        .extract().asString()
//        ;
//        Assert.assertEquals(body, "418 I'm a teapot");

        //2nd Way
        given().
                when().
                get("https://httpstat.us/418").
                then().
                contentType(ContentType.TEXT)
                .statusCode(418)
                .body(equalTo("418 I'm a teapot"))
        ;
    }

}

