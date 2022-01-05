import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.ToDo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;


    @BeforeClass
    public void setup() {
        baseURI = "http://api.zippopotam.us/";
        requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setAccept(ContentType.JSON)
                .addQueryParam("page", "1")
                .addHeader("randomHeader", "randomValue")
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

    }

    @Test
    public void test() {
        given()
                .when()
                .then()
        ;

    }

    @Test
    public void statusCodeTest() {
        given().
                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                statusCode(200);
    }

    @Test
    public void contentTypeTest() {
        given().
                spec(requestSpecification).
                when().
                get("/us/90210").
                then().
                contentType(ContentType.JSON);
    }

    @Test
    public void logTest() {
        given().
                log().all().  //print out request
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .spec(responseSpecification)
        ;
    }

    @Test
    public void bodyJsonPathTest() {
        given().
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .body("country", equalTo("United States"))
                .spec(responseSpecification)
        ;
    }

    @Test
    public void bodyJsonPathTest2() {
        given().
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .body("places[0].state", equalTo("California"))
                .spec(responseSpecification)
        ;
    }

    @Test
    public void bodyJsonPathTest3() {
        given().
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .body("places[0].'place name'", equalTo("Beverly Hills"))
        ;
    }

    @Test
    public void bodyArrazSizeTest() {
        given().
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .body("places", hasSize(1))
        ;
    }

    @Test
    public void bodyArraySizeTest() {
        given().
                when().
                get("/us/90210").
                then().
                log().all()  //print out response
                .body("places", hasSize(1))
        ;
    }
    @Test
    public void chainingTest() {
        given().
                when().
                get("/us/90210").
                then().
                statusCode(200)
                .contentType(ContentType.JSON)
                .body("places[0].state", equalTo("California"))
                .body("places.'place name'", hasItem("Beverly Hills"))
                .body("places[0].'state abbreviation'", equalTo("CA"))
                .body("places", hasSize(1))
        ;
    }

    @Test
    public void pathParametersTest() {
        String country = "us";
        String zipCode = "90210";

        given().
                pathParam("country", country).
                pathParam("zipCode", zipCode).
                log().uri().
                when().
                get("/{country}/{zipCode}").
                then().
                body("places", hasSize(1));
    }

    @Test
    public void queryParametersTest() {
        String country = "us";
        String zipCode = "90210";

        given().
                param("country", country).
                param("zipCode", zipCode).
                log().uri().
                when().
                get("/us/90210").
                then().
                body("places", hasSize(1));
    }

    @Test
    public void queryParametersTest2() {
        String format = "json";
        //   int page = 10;

        for (int page = 1; page < 10; page++) {
            given().
                    param("_format", format).
                    param("page", page).
                    log().uri().
                    when().
                    get("https://gorest.co.in/public-api/users").
                    then().
                    log().body().
                    body("meta.pagination.page", equalTo(page));
        }

    }

    @Test
    public void extractingJsonPath() {

        String extractedValue = given().
                when().
                get("us/90210").
                then().
                log().body()
                .extract().path("places[0].'place name'");

        System.out.println(extractedValue);
        Assert.assertEquals(extractedValue, "Beverly Hills");


    }

    @Test
    public void extractingValuesFromJsonPath() {

       List <String> extractedValues = given().
                when().
                get("tr/34840").
                then().
                log().body()
                .extract().path("places.'place name'");

        System.out.println(extractedValues);
        Assert.assertTrue(extractedValues.contains("Altintepe Mah."));


    }

    @Test
    public void testingValuesFromJsonPath() {

         given().
                when().
                get("tr/34840").
                then().
                log().body()
                .body("places.'place name'",hasItem("Altintepe Mah."));

    }


}


