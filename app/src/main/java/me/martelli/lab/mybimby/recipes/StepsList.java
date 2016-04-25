package me.martelli.lab.mybimby.recipes;

import java.util.List;

public class StepsList {
    private String title;
    private List<String> steps;

    public StepsList(List<String> steps) {
        this.steps = steps;
    }

    public StepsList(String title, List<String> steps) {
        this.title = title;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSteps() {
        return steps;
    }
}
