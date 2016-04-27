package me.martelli.lab.mybimby;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.martelli.lab.mybimby.databinding.ActivityRecipeDetailBinding;
import me.martelli.lab.mybimby.recipes.IngredientsList;
import me.martelli.lab.mybimby.recipes.Recipe;
import me.martelli.lab.mybimby.recipes.RecipeUtils;

public class RecipeDetailActivity extends AppCompatActivity {
    public final static String RECIPE_EXTRA = "recipe_extra";

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRecipeDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        actionBar.setTitle(null);

        int position = getIntent().getIntExtra(RECIPE_EXTRA, -1);
        Recipe recipe = RecipeUtils.getDummyRecipes().get(position);

        binding.setRecipe(recipe);

        imageView = (ImageView) findViewById(R.id.recipe_image);
        textView = (TextView) findViewById(R.id.recipe_name);
        assert imageView != null;
        assert textView != null;

        ViewCompat.setTransitionName(imageView, getString(R.string.recipe_image));

        int width = getResources().getDisplayMetrics().widthPixels;
        imageView.setMaxHeight(width * 2 / 3);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.recipe_ingredients_list);
        assert linearLayout != null;
        LayoutInflater inflater = getLayoutInflater();

        for(IngredientsList ingredientsList : recipe.getIngredientsBlocks()) {
            String heading = ingredientsList.getTitle();
            if(heading == null) {
                heading = getString(R.string.ingredients);
            }

            TextView header = (TextView) inflater.inflate(R.layout.recipe_detail_header, null);
            header.setText(heading);
            linearLayout.addView(header);

            for(String ingredient : ingredientsList.getIngredients()) {
                TextView text = (TextView) inflater.inflate(R.layout.recipe_detail_item, null);
                text.setText(ingredient);
                linearLayout.addView(text);
            }
        }

        if(recipe.getUtensils().size() != 0) {
            TextView header = (TextView) inflater.inflate(R.layout.recipe_detail_header, null);
            header.setText(R.string.utensils);
            linearLayout.addView(header);

            for(String utensil : recipe.getUtensils()) {
                TextView text = (TextView) inflater.inflate(R.layout.recipe_detail_item, null);
                text.setText(utensil);
                linearLayout.addView(text);
            }
        }
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
        int width = getResources().getDisplayMetrics().widthPixels;
        textView.setHeight(width - imageView.getHeight());
    }
}
