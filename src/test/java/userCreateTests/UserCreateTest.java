package userCreateTests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import userCreate.UserCreate;
import userCreate.UserCreateFieldsGenerator;
import userCreate.UserCreateSteps;

import static groovy.xml.dom.DOMCategory.name;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.step;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreateTest {

    private UserCreateSteps step;
    UserCreateFieldsGenerator userCreateFieldsGenerator;

    @Before
    @Step("create objects for tests")
    public void setUp() {
        userCreateFieldsGenerator = new UserCreateFieldsGenerator();
        step = new UserCreateSteps();
    }

    @Test
    @DisplayName("Тест на создание уникального пользователя.")
    @Description("Успешное создание уникального пользователя с проверкой возвращаемого статус-кода 200 ОК в соответствии с документацией.")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("https://practicum.yandex.ru/learn/qa-automation-engineer-java/courses/5c87a15a-37d9-4d06-8e7d-3ebe49aba2fb/sprints/72940/topics/7ec6ef07-a3d5-4923-a8ac-64313ac438e1/lessons/311b7751-0b28-438a-adb4-732ca7080912/")
    public void shouldCreateAUser() {
        UserCreate userCreate = UserCreateFieldsGenerator.passingGeneratorData();
        ValidatableResponse responseCreate = step.create(userCreate);
        responseCreate.assertThat().statusCode(200);
    }


}