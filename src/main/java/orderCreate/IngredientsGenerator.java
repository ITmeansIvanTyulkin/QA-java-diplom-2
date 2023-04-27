package orderCreate;

import com.google.gson.Gson;

public class IngredientsGenerator {

    private OrderCreateSteps step;
    Gson gson = new Gson();
    private String ingredients = "ingredients";
    private String numberOfOrder;

    public IngredientsGenerator() {
    }

    public IngredientsGenerator(String ingredients, String numberOfOrder) {
        this.ingredients = ingredients;
        this.numberOfOrder = numberOfOrder;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(String numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

//    public String generateOrder() {
//        OrderCreateSteps orderCreateSteps = new OrderCreateSteps();
//        String numberOfOrder = orderCreateSteps.gettingListOfIngredients();
//        return numberOfOrder;
//    }
//
//    public String convertToJson() {
//        IngredientsGenerator ingredientsGenerator = new IngredientsGenerator(ingredients, generateOrder());
//        String json = gson.toJson(ingredientsGenerator);
//        return json;
//    }
}