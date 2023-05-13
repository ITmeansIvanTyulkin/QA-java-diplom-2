package userLogin;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import static io.restassured.RestAssured.given;

public class UserLoginSteps {
    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String USER_CREATE_URI = BASE_URI + "/api/auth/register";
    protected final String USER_LOGIN_URI = BASE_URI + "/api/auth/login";

    @Description("Создание спецификации, общее для всех @steps.")
    private RequestSpecification getSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    @Step("Создание нового пользователя.")
    public ValidatableResponse create(UserLogin userLogin) {
        return getSpec()
                .body(userLogin)
                .when()
                .post(USER_CREATE_URI)
                .then().log().all();
    }

    @Step("Вход созданного пользователя в систему.")
    public ValidatableResponse logging(UserLogin userLogin) {
        userLogin = UserLoginFieldsGenerator.passingGeneratorData();
        ValidatableResponse responseCreate = create(userLogin);
        String accessToken = responseCreate.extract().path("accessToken");
        return getSpec()
                .auth().oauth2(accessToken)
                .and()
                .body(userLogin)
                .when()
                .post(USER_LOGIN_URI)
                .then()
                .log()
                .all();
    }

    @Step("Вход созданного пользователя в систему с неверным логином и паролем.")
    public ValidatableResponse loggingWithInvalidData(UserLogin userLogin) {
        userLogin = UserLoginFieldsGenerator.passingGeneratorData();
        ValidatableResponse responseCreate = create(userLogin);
        String accessToken = responseCreate.extract().path("accessToken");
        return getSpec()
                .auth().oauth2(accessToken)
                .and()
                .body(UserLoginFieldsGenerator.passingGeneratorInvalidData())
                .when()
                .post(USER_LOGIN_URI)
                .then()
                .log()
                .all();
    }
}