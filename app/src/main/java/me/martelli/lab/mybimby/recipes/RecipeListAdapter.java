package me.martelli.lab.mybimby.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.martelli.lab.mybimby.databinding.RecipeListItemBinding;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private List<Recipe> recipes;

    public RecipeListAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            RecipeListItemBinding binding = RecipeListItemBinding.inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }

        private RecipeListItemBinding mBinding;

        private ViewHolder(RecipeListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindTo(Recipe user) {
            mBinding.setRecipe(user);
            mBinding.executePendingBindings();
        }
    }
}
