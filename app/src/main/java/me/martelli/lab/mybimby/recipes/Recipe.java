package me.martelli.lab.mybimby.recipes;

import android.net.Uri;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Recipe {
    private String name;
    private Uri image;
    private BaseInfo info;
    private List<IngredientsList> ingredientsBlocks;
    private List<String> utensils;
    private List<StepsList> stepsBlocks;
    private List<String> advices;
    private List<String> variants;

    private Recipe(String name, Uri image, BaseInfo info, List<IngredientsList> ingredientsBlocks,
                  List<String> utensils, List<StepsList> stepsBlocks, List<String> advices,
                  List<String> variants) {
        this.name = name;
        this.image = image;
        this.info = info;
        this.ingredientsBlocks = ingredientsBlocks;
        this.utensils = utensils;
        this.stepsBlocks = stepsBlocks;
        this.advices = advices;
        this.variants = variants;
    }

    public static class Builder {
        private String name;
        private Uri image;
        private BaseInfo info;
        private List<IngredientsList> ingredientsBlocks = new LinkedList<>();
        private List<String> utensils = new LinkedList<>();
        private List<StepsList> stepsBlocks = new LinkedList<>();
        private List<String> advices = new LinkedList<>();
        private List<String> variants = new LinkedList<>();

        public Builder(String name, Uri image) {
            this.name = name;
            this.image = image;
        }

        public Builder setBaseInfo(BaseInfo info) {
            this.info = info;
            return this;
        }

        public Builder setBaseInfo(int workTime, int totalTime, @BaseInfo.Difficulty int difficulty, int portions) {
            this.info = new BaseInfo(workTime, totalTime, difficulty, portions);
            return this;
        }

        public Builder setIngredients(List<IngredientsList> ingredientsBlocks) {
            this.ingredientsBlocks = ingredientsBlocks;
            return this;
        }

        public Builder addIngredientsBlock(IngredientsList ingredients) {
            this.ingredientsBlocks.add(ingredients);
            return this;
        }

        public Builder addIngredientsBlock(String... ingredients) {
            return addIngredientsBlock(new IngredientsList(Arrays.asList(ingredients)));
        }

        public Builder setUtensils(List<String> utensils) {
            this.utensils = utensils;
            return this;
        }

        public Builder setUtensils(String... utensils) {
            return setUtensils(Arrays.asList(utensils));
        }

        public Builder addUtensil(String utensil) {
            this.utensils.add(utensil);
            return this;
        }

        public Builder setSteps(List<StepsList> stepsBlocks) {
            this.stepsBlocks = stepsBlocks;
            return this;
        }

        public Builder addStepsBlock(StepsList steps) {
            this.stepsBlocks.add(steps);
            return this;
        }

        public Builder addStepsBlock(String... steps) {
            return addStepsBlock(new StepsList(Arrays.asList(steps)));
        }

        public Builder setAdvices(List<String> advices) {
            this.advices = advices;
            return this;
        }

        public Builder setAdvices(String... advices) {
            return setAdvices(Arrays.asList(advices));
        }

        public Builder addAdvice(String advice) {
            this.advices.add(advice);
            return this;
        }

        public Builder setVariants(List<String> variants) {
            this.variants = variants;
            return this;
        }

        public Builder setVariants(String... variants) {
            return setVariants(Arrays.asList(variants));
        }

        public Builder addVariant(String variant) {
            this.variants.add(variant);
            return this;
        }

        public Recipe build() {
            return new Recipe(name, image, info, ingredientsBlocks, utensils, stepsBlocks, advices, variants);
        }
    }

    public String getName() {
        return name;
    }

    public Uri getImage() {
        return image;
    }

    public BaseInfo getInfo() {
        return info;
    }

    public List<IngredientsList> getIngredientsBlocks() {
        return ingredientsBlocks;
    }

    public List<String> getUtensils() {
        return utensils;
    }

    public List<StepsList> getStepsBlocks() {
        return stepsBlocks;
    }

    public List<String> getAdvices() {
        return advices;
    }

    public List<String> getVariants() {
        return variants;
    }
}
