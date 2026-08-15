package validators;

import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

public class RateValidator  {
    @Step("Проверить JSON схему ответа")
    public void validateSchema(String response) {
        given()
                .body(response)
                .then()
                .body(matchesJsonSchemaInClasspath(
                        "schemas/rate_schema.json"));
    }

    @Step("Проверить наличие обязательных полей amount, grow, scale")
    public void validateKeys(String response) {
        given()
                .body(response)
                .then()
                .body("$", hasKey("amount"))
                .body("$", hasKey("grow"))
                .body("$", hasKey("scale"));
    }
}
