package me.martelli.lab.mybimby.steps;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;

import com.github.fcannizzaro.materialstepper.style.ProgressStepper;

import me.martelli.lab.mybimby.R;
import me.martelli.lab.mybimby.recipes.Recipe;
import me.martelli.lab.mybimby.recipes.RecipeUtils;
import me.martelli.lab.mybimby.recipes.StepsList;
import me.martelli.lab.mybimby.util.OnFocusListenable;

public class RecipeDetailActivityStepper extends ProgressStepper {
    public final static String RECIPE_EXTRA = "recipe_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int position = getIntent().getIntExtra(RECIPE_EXTRA, -1);
        Recipe recipe = RecipeUtils.getDummyRecipes().get(position);

        addStep(InfoStep.newInstance(position));

        for(int i = 0, n = recipe.getStepsBlocks().size(); i < n; i++) {
            StepsList stepsList = recipe.getStepsBlocks().get(i);
            for(int j = 0, m = stepsList.getSteps().size(); j < m; j++) {
                addStep(StepsBlockStep.newInstance(position, i, j, m));
            }
        }

        setColorPrimary(R.color.colorAccent);

        super.onCreate(savedInstanceState);

        ActivityCompat.postponeEnterTransition(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(mSteps.getCurrent() instanceof OnFocusListenable) {
            ((OnFocusListenable) mSteps.getCurrent()).onWindowFocusChanged(hasFocus);
        }
    }

    @Override
    public void onComplete() {
        finish();
    }
}
