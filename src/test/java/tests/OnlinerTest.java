package tests;

import enums.Currency;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import validators.RateSteps;
import validators.RateValidator;

@Listeners(AllureTestNg.class)
@Epic("Курсы валют")
@Feature("API проверки")
@Owner("Fatikhov Dinar")

public class OnlinerTest {

    private final RateSteps steps = new RateSteps();
    private final RateValidator validator = new RateValidator();

    @DataProvider(name = "currencies")
    public Object[][] currencies() {
        return new Object[][]{
                {Currency.USD},
                {Currency.EUR},
                {Currency.RUB}
        };
    }

    @Test(dataProvider = "currencies")
    public void checkRates(Currency currency) {
        String response = steps.getResponse(currency);

        validator.validateSchema(response);
        validator.validateKeys(response);
    }
}
