package userDataChangeTests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import userDataChange.UserDataChange;
import userDataChange.UserDataChangeGenerator;
import userDataChange.UserDataChangeSteps;


public class UserDataChangeTest {

    private UserDataChangeSteps step;
    UserDataChange userDataChange;
    UserDataChangeGenerator userDataChangeGenerator;


    @Before
    @Step("Создание объектов для проведения тестов.")
    public void setUp() {
        userDataChangeGenerator = new UserDataChangeGenerator();
        step = new UserDataChangeSteps();
    }

    // Тесты на проверку того, что у АВТОРИЗОВАННОГО пользователя любое поле - email, password и name - можно изменить.
    @Test
    @DisplayName("Тест на возможность изменения email пользователя.")
    @Description("Успешное изменение в системе email пользователя с проверкой возвращаемого статус-кода 200 ОК в соответствии с документацией.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    public void wouldChangeEmail() {
        ValidatableResponse responseEmailChanged = step.changingUserEmail(userDataChange);
        responseEmailChanged.assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Тест на возможность изменения password пользователя.")
    @Description("Успешное изменение в системе password пользователя с проверкой возвращаемого статус-кода 200 ОК в соответствии с документацией.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    public void wouldChangePassword() {
        ValidatableResponse responsePasswordChanged = step.changingUserPassword(userDataChange);
        responsePasswordChanged.assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Тест на возможность изменения name пользователя.")
    @Description("Успешное изменение в системе name пользователя с проверкой возвращаемого статус-кода 200 ОК в соответствии с документацией.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    public void wouldChangeName() {
        ValidatableResponse responseNameChanged = step.changingUserName(userDataChange);
        responseNameChanged.assertThat().statusCode(HttpStatus.SC_OK);
    }

    // Тесты на проверку того, что у НЕ авторизованного пользователя любое поле - email, password и name - можно изменить.

    /* Здесь в трёх тестах получаются три бага - у существующего (созданного) пользователя можно изменить любое поле -
    - email, password и name - без авторизации в системе. Так быть не должно.

                                        МЫ ЖДЁМ ОШИБКУ 401 Unauthorized, А ПРИХОДИТ 200 ОК.
     */
    @Test
    @DisplayName("Тест на невозможность изменения email у существующего, но НЕ авторизованного пользователя.")
    @Description("Нельзя изменить в системе email у существующего, но НЕ авторизованного пользователя с проверкой возвращаемого статус-кода 401 Unauthorized в соответствии с документацией.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    @Issue("Bug report link")
    public void wouldChangeEmailNonAuthorizedUser() {
        ValidatableResponse responseEmailChanged = step.changingNotAuthorizedUserEmail(userDataChange);
        responseEmailChanged.assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Тест на невозможность изменения password у существующего, но НЕ авторизованного пользователя.")
    @Description("Нельзя изменить в системе password у существующего, но НЕ авторизованного пользователя с проверкой возвращаемого статус-кода 401 Unauthorized в соответствии с документацией.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    @Issue("Bug report link")
    public void wouldChangePasswordNonAuthorizedUser() {
        ValidatableResponse responseEmailChanged = step.changingNotAuthorizedUserPassword(userDataChange);
        responseEmailChanged.assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Тест на невозможность изменения name у существующего, но НЕ авторизованного пользователя.")
    @Description("Нельзя изменить в системе password у существующего, но НЕ авторизованного пользователя с проверкой возвращаемого статус-кода 401 Unauthorized в соответствии с документацией.")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    @Issue("Bug report link")
    public void wouldChangeNameNonAuthorizedUser() {
        ValidatableResponse responseEmailChanged = step.changingNotAuthorizedUserName(userDataChange);
        responseEmailChanged.assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}