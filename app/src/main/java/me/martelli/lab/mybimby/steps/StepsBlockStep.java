package me.martelli.lab.mybimby.steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import me.martelli.lab.mybimby.R;
import me.martelli.lab.mybimby.recipes.Recipe;
import me.martelli.lab.mybimby.recipes.RecipeUtils;
import me.martelli.lab.mybimby.recipes.StepsList;

public class StepsBlockStep extends AbstractStep {
    private static final String ARG_RECIPE = "recipe_position";
    private static final String ARG_STEP = "recipe_step";
    private static final String ARG_STEP_POS = "recipe_step_pos";
    private static final String ARG_STEP_TOTAL = "recipe_step_total";

    public static StepsBlockStep newInstance(int position, int blockPosition, int stepPosition, int totalBlocks) {
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE, position);
        args.putInt(ARG_STEP, blockPosition);
        args.putInt(ARG_STEP_POS, stepPosition);
        args.putInt(ARG_STEP_TOTAL, totalBlocks);
        StepsBlockStep fragment = new StepsBlockStep();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String name() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe_steps, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Recipe recipe = RecipeUtils.getDummyRecipes().get(getArguments().getInt(ARG_RECIPE));

        StepsList stepList = recipe.getStepsBlocks().get(getArguments().getInt(ARG_STEP));

        String title = stepList.getTitle();
        if(title == null) {
            title = getString(R.string.steps);
        }
        int current = getArguments().getInt(ARG_STEP_POS);
        int total = getArguments().getInt(ARG_STEP_TOTAL);
        actionBar.setTitle(title + " (" + (current + 1) + "/" + total + ")");

        TextView textView = (TextView) rootView.findViewById(R.id.recipe_step);
        assert textView != null;
        textView.setText(RecipeUtils.formatHtml(stepList.getSteps().get(current)));

        return rootView;
    }
}
