package me.martelli.lab.mybimby;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.martelli.lab.mybimby.recipes.BaseInfo;
import me.martelli.lab.mybimby.recipes.RecipeListAdapter;
import me.martelli.lab.mybimby.recipes.RecipeUtils;
import me.martelli.lab.mybimby.steps.RecipeDetailActivityStepper;
import me.martelli.lab.mybimby.util.RecyclerItemClickListener;

public class RecipeListActivity extends AppCompatActivity {
    private View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_recipe_list);

        setupToolbar();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipe_recycler_view);
        assert recyclerView != null;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new RecipeListAdapter(RecipeUtils.getDummyRecipes());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(RecipeListActivity.this, RecipeDetailActivityStepper.class);
                    intent.putExtra(RecipeDetailActivityStepper.RECIPE_EXTRA, position);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView = view.findViewById(R.id.info_image);
                        String imageTransitionName = getString(R.string.recipe_image);

                        ViewCompat.setTransitionName(imageView, imageTransitionName);

                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(RecipeListActivity.this, imageView, imageTransitionName);

                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            })
        );
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(0);
        actionBar.setTitle(R.string.recipe_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && imageView != null) {
            ViewCompat.setTransitionName(imageView, null);
            imageView = null;
        }
    }

    @BindingAdapter("bind:src")
    public static void setImageUrl(ImageView view, Uri image) {
        int width = view.getContext().getResources().getDisplayMetrics().widthPixels;
        Picasso.with(view.getContext()).load(image).resize(width, 0).into(view);
    }

    @BindingAdapter("bind:difficulty")
    public static void setDifficulty(TextView view, @BaseInfo.Difficulty int difficulty) {
        view.setText(RecipeUtils.formatDifficulty(view.getContext(), difficulty));
    }

    @BindingAdapter("bind:setTransitionName")
    public static void setTransitionName(ImageView view, @StringRes int name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName(view.getResources().getString(name));
        }
    }
}
