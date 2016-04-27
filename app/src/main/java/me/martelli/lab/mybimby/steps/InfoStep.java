package me.martelli.lab.mybimby.steps;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import java.util.List;

import me.martelli.lab.mybimby.R;
import me.martelli.lab.mybimby.RecipeListActivity;
import me.martelli.lab.mybimby.databinding.ActivityRecipeDetailBinding;
import me.martelli.lab.mybimby.recipes.IngredientsList;
import me.martelli.lab.mybimby.recipes.Recipe;
import me.martelli.lab.mybimby.recipes.RecipeUtils;
import me.martelli.lab.mybimby.util.OnFocusListenable;

public class InfoStep extends AbstractStep implements OnFocusListenable {
    private static final String ARG_RECIPE = "recipe_position";
    private ImageView imageView;
    private TextView textView;

    public static InfoStep newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE, position);
        InfoStep fragment = new InfoStep();
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
        ActivityRecipeDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_recipe_detail, container, false);
        View rootView = binding.getRoot();

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        actionBar.setTitle(null);

        final Recipe recipe = RecipeUtils.getDummyRecipes().get(getArguments().getInt(ARG_RECIPE));
        binding.setRecipe(recipe);

        imageView = (ImageView) rootView.findViewById(R.id.recipe_image);
        textView = (TextView) rootView.findViewById(R.id.recipe_name);

        int width = getResources().getDisplayMetrics().widthPixels;
        imageView.setMaxHeight(width * 2 / 3);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.recipe_ingredients_list);
        assert linearLayout != null;

        for(IngredientsList ingredientsList : recipe.getIngredientsBlocks()) {
            String heading = ingredientsList.getTitle();
            if(heading == null) {
                heading = getString(R.string.ingredients);
            }

            addRecipeSection(ingredientsList.getIngredients(), heading, inflater, linearLayout);
        }

        if(recipe.getUtensils().size() != 0) {
            addRecipeSection(recipe.getUtensils(), getString(R.string.utensils), inflater, linearLayout);
        }

        if(recipe.getAdvices().size() != 0) {
            addRecipeSection(recipe.getAdvices(), getString(R.string.advices), inflater, linearLayout);
        }

        if(recipe.getVariants().size() != 0) {
            addRecipeSection(recipe.getVariants(), getString(R.string.variants), inflater, linearLayout);
        }

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(RecipeUtils.shareRecipe(getActivity(), recipe), getString(R.string.share_recipe)));
            }
        });

        return rootView;
    }

    private void addRecipeSection(List<String> list, String title, LayoutInflater inflater, LinearLayout linearLayout) {
        TextView header = (TextView) inflater.inflate(R.layout.recipe_detail_header, linearLayout, false);
        header.setText(title);
        linearLayout.addView(header);

        for(String item : list) {
            TextView text = (TextView) inflater.inflate(R.layout.recipe_detail_item, linearLayout, false);
            text.setText(RecipeUtils.formatHtml(item));
            linearLayout.addView(text);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        int width = getResources().getDisplayMetrics().widthPixels;
        textView.setHeight(width - imageView.getHeight());
    }
}
