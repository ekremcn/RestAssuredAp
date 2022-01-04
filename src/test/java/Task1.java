import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

// Task 1
//            * create a request to https://httpstat.us/203
//            * expect status 203
//            * expect content type text

public class Task1 {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeClass
    public void setup() {
        baseURI = "https://httpstat.us";

        requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setAccept(ContentType.TEXT)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(203)
                .build();
    }

    @Test
    public void contentTypeTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get("/203")
                .then()
                .spec(responseSpecification)
        ;
    }

    //2nd way
    @Test
    public void statusCodeTest() {
        given().
                when().
                get("https://httpstat.us/203").
                then().
                contentType(ContentType.TEXT)
                .statusCode(203);

    }


}
