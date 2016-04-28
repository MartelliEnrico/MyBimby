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
import me.martelli.lab.mybimby.recipes.RecipeUtils;

public class StepsBlockStep extends AbstractStep {
    private static final String ARG_TITLE = "recipe_step_title";
    private static final String ARG_STEP = "recipe_step";
    private static final String ARG_STEP_POS = "recipe_step_pos";
    private static final String ARG_STEP_TOTAL = "recipe_step_total";

    public static StepsBlockStep newInstance(@Nullable String title, String step, int stepPosition, int totalBlocks) {
        Bundle args = new Bundle();
        if(title != null) {
            args.putString(ARG_TITLE, title);
        }
        args.putString(ARG_STEP, step);
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

        String title = getArguments().getString(ARG_TITLE, getString(R.string.steps));
        int current = getArguments().getInt(ARG_STEP_POS);
        int total = getArguments().getInt(ARG_STEP_TOTAL);
        actionBar.setTitle(title + " (" + (current + 1) + "/" + total + ")");

        TextView textView = (TextView) rootView.findViewById(R.id.recipe_step);
        assert textView != null;
        textView.setText(RecipeUtils.formatHtml(getArguments().getString(ARG_STEP)));

        return rootView;
    }
}
