package me.martelli.lab.mybimby;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.martelli.lab.mybimby.recipes.Recipe;
import me.martelli.lab.mybimby.recipes.RecipeListAdapter;
import me.martelli.lab.mybimby.recipes.RecipeUtils;
import me.martelli.lab.mybimby.steps.RecipeDetailActivityStepper;
import me.martelli.lab.mybimby.util.RecyclerItemClickListener;

public class RecipeListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Recipe> recipes;
    private View imageView, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipes = RecipeUtils.getDummyRecipes();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(0);
        actionBar.setTitle(R.string.recipe_list);

        recyclerView = (RecyclerView) findViewById(R.id.recipe_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecipeListAdapter(recipes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(RecipeListActivity.this, RecipeDetailActivityStepper.class);
                    intent.putExtra(RecipeDetailActivity.RECIPE_EXTRA, position);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView = view.findViewById(R.id.info_image);
                        textView = view.findViewById(R.id.info_text);
                        String imageTransitionName = getString(R.string.recipe_image);
                        String textTransitionName = getString(R.string.recipe_name);

                        ViewCompat.setTransitionName(imageView, imageTransitionName);
                        ViewCompat.setTransitionName(textView, textTransitionName);

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                RecipeListActivity.this,
                                new Pair<>(imageView, imageTransitionName),
                                new Pair<>(textView, textTransitionName));

                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            })
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && imageView != null) {
            ViewCompat.setTransitionName(imageView, null);
            ViewCompat.setTransitionName(textView, null);

            imageView = null;
            textView = null;
        }
    }

    @BindingAdapter("bind:src")
    public static void setImageUrl(ImageView view, String url) {
        int width = view.getContext().getResources().getDisplayMetrics().widthPixels;
        Picasso.with(view.getContext()).load(url).resize(width, 0).into(view);
    }
}
