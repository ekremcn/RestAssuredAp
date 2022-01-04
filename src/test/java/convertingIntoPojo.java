import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.ToDo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class convertingIntoPojo {

    @Test
    public void convertingIntoPojooo(){

        ToDo toDo = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .extract().as(ToDo.class);

        System.out.println(toDo);
        System.out.println(toDo.getTitle());

    }

    @Test
    public void convertingArrayIntoArrayOfPojos(){

        ToDo [] toDos = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .extract().as(ToDo[].class);

        for (ToDo toDo : toDos) {
            System.out.println(toDo);
        }
    }


}

