import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssure {

    @Test
    public void statusCodeTest() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200); // Status Code Testi
    }

    @Test
    public void jsonStructureValidationTest() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat()
                .statusCode(200) // Status Code Testi
                .body("$", is(not(empty()))) // Array içerdiğini doğrulama Testi
                .body("userId", everyItem(notNullValue())) //Gönderinin userid değerinin olduğunu doğrulama testi
                .body("id", everyItem(notNullValue()))  //Gönderinin id değerinin olduğunu doğrulama testi
                .body("title", everyItem(notNullValue())) //Gönderinin title değerinin olduğunu doğrulama testi
                .body("body", everyItem(notNullValue())); //Gönderinin body değerinin olduğunu doğrulama testi
    }

    @Test
    void ValueValidationTest() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("find { it.id == 3 }.title", equalTo("ea molestias quasi exercitationem repellat qui ipsa sit aut"));   //Belirli bir değeri doğrulama testi
    }

    @Test
    void listLengthTest() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("size()", greaterThanOrEqualTo(10)); // Liste Uzunluğu Testi
    }

    @Test
    void dynamicDataValidationTest() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("userId", everyItem(greaterThan(0))); // Dinamik Veri Kontrol Testi
    }
}
