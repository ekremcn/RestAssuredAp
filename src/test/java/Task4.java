import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task4 {

    /* Task 4
            * create a request to https://jsonplaceholder.typicode.com/todos/2
            * expect status 200
            * expect content type json
     * expect response completed status to be false
            */

    @Test
    public void statusCodeTest() {

        given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/2").
                then().
                contentType(ContentType.JSON)
                .statusCode(200)
                .body("completed",equalTo(false))
        ;
    }
}
