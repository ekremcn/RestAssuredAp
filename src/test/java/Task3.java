import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task3 {

    /* Task 3
            * create a request to https://jsonplaceholder.typicode.com/todos/2
            * expect status 200
            * expect content type json
     * expect title in response body to be "quis ut nam facilis et officia qui"
            */

    @Test
    public void statusCodeTest() {

        given().
                when().
                get("https://jsonplaceholder.typicode.com/todos/2").
                then().
                contentType(ContentType.JSON)
                .statusCode(200)
                .body("title",equalTo("quis ut nam facilis et officia qui"))
        ;
    }
}
