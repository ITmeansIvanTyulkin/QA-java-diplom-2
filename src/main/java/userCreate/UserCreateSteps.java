package userCreate;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;

import static io.restassured.RestAssured.given;

public class UserCreateSteps {
    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String USER_CREATE_URI = BASE_URI + "/api/auth/register";

    @Description("Создание спецификации, общее для всех @steps")
    private RequestSpecification getSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    @Step("Создание нового пользователя.")
    public ValidatableResponse create(UserCreate userCreate) {
        return getSpec()
                .body(userCreate)
                .when()
                .post(USER_CREATE_URI)
                .then().log().all();
    }






}