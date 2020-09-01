package steps.api;

import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class BlazeDemoAPISteps {
    @Step
    public void verifyThatAppIsRunning() {
        given().get("http://gopitestdemo.com").then().statusCode(200);
    }
}