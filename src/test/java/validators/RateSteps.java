package validators;

import api.ApiClient;
import enums.Currency;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class RateSteps  {
    @Step("Получить курс валюты")
    public String getResponse(Currency currency) {
        return given()
                .baseUri(ApiClient.BASE_URL)
                .log().all()
                .queryParam("currency", currency.getCode())
                .queryParam("type", "nbrb")
                .when()
                .get(ApiClient.RATE_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .asString();
    }
}
