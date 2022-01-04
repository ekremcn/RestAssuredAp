import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task5 {

    /* Task 5
     * create a request to https://jsonplaceholder.typicode.com/todos
     * expect status 200
     * expect content type json
     * expect third item have:
     *      title = "fugiat veniam minus"
     *      userId = 1
     */


    @Test
    public void statusCodeTest() {

        given().
                when().
                get("https://jsonplaceholder.typicode.com/todos").
                then().
                contentType(ContentType.JSON)
                .statusCode(200)
                .body("title[2]", equalTo("fugiat veniam minus"))
                .body("userId[2]", equalTo(1))
        ;
    }
}
