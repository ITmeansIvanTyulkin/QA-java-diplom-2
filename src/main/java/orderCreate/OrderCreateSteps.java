package orderCreate;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import userDataChange.UserDataChangeGenerator;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static orderCreate.InvalidOrder.getData;

public class OrderCreateSteps {

    ListOfIngredients listOfIngredients = new ListOfIngredients();
    protected final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected final String USER_CREATE_URI = BASE_URI + "/api/auth/register";
    protected final String USER_LOGIN_URI = BASE_URI + "/api/auth/login";
    protected final String GET_INGREDIENTS_LIST = BASE_URI + "/api/ingredients";
    protected final String POST_ORDER_CREATE = BASE_URI + "/api/orders";


    @Description("Создание спецификации, общее для всех @steps.")
    private RequestSpecification getSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    @Step("Простое получение списка ингредиентов.")
    public ValidatableResponse getIngredients() {
        return getSpec()
                .when()
                .get(GET_INGREDIENTS_LIST)
                .then().log().all();
    }

    @Step("Получение списка ингредиентов в виде объекта для последующей работы с ним.")
    public ListOfIngredients getIngredientsObject() {
        return listOfIngredients =
                given().log().all()
                        .contentType(ContentType.JSON)
                        .get(GET_INGREDIENTS_LIST)
                        .body().as(ListOfIngredients.class);
    }

    @Step("Сериализация из объекта в формат List и получение любого ингредиента для создания заказа.")
    public String gettingListOfIngredients() {
        listOfIngredients = getIngredientsObject();
        List<Data> dataList = listOfIngredients.getData();

        StringBuilder stringBuilder = new StringBuilder("");

        // Итерация по списку ингредиентов и их вывод.
        for (int i = 0; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            stringBuilder.append(data.getId() + ",");
        }

        String[] split = stringBuilder.toString().split(",");

        Random randomIngredients = new Random();
        int index = randomIngredients.nextInt(split.length);

        return split[index];
    }

    @Step("Создание заказа.")
    public ValidatableResponse orderCreate(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(POST_ORDER_CREATE)
                .then().log().all();
    }

    @Step("Создание заказа без ингредиентов.")
    public ValidatableResponse orderCreateWithoutIngredients(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(POST_ORDER_CREATE)
                .then().log().all();
    }


}