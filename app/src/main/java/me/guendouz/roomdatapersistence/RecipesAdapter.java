package me.guendouz.roomdatapersistence;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.guendouz.roomdatapersistence.entity.Recipe;

/**
 * A simple RecyclerView adapter to show recipes.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClicked(Recipe recipe);
    }

    private List<Recipe> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnDeleteButtonClickListener onDeleteButtonClickListener;

    public RecipesAdapter(Context context, OnDeleteButtonClickListener listener) {
        this.context = context;
        this.onDeleteButtonClickListener = listener;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.layout_item_recipe, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    public void setData(List<Recipe> newData) {
        data = newData;
        notifyDataSetChanged();
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvContent;
        private Button btnDelete;

        RecipeViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvItemRecipeName);
            tvContent = itemView.findViewById(R.id.tvItemRecipeDescription);
            btnDelete = itemView.findViewById(R.id.btnDeleteRecipe);
        }

        void bind(final Recipe recipe) {
            if (recipe != null) {
                tvTitle.setText(recipe.getName());
                tvContent.setText(recipe.getDescription());
                btnDelete.setOnClickListener(v -> {
                    if (onDeleteButtonClickListener != null)
                        onDeleteButtonClickListener.onDeleteButtonClicked(recipe);
                });

            }
        }

    }


}
