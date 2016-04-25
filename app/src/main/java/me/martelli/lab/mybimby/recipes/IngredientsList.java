package me.martelli.lab.mybimby.recipes;

import java.util.List;

public class IngredientsList {
    private String title;
    private List<String> ingredients;

    public IngredientsList(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public IngredientsList(String title, List<String> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
