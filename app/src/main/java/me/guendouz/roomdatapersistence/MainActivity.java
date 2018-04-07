package me.guendouz.roomdatapersistence;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import me.guendouz.roomdatapersistence.entity.Recipe;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.OnDeleteButtonClickListener {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private RecipesAdapter recipesAdapter;

    private RecipeViewModel recipeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            recipeViewModel.addRecipe(new Recipe());
        });

        recipesAdapter = new RecipesAdapter(this, this);
        recyclerView = findViewById(R.id.rvRecipeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipesAdapter);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getAllRecipes().observe(this, recipes -> {
            recipesAdapter.setData(recipes);
        });
    }

    @Override
    public void onDeleteButtonClicked(Recipe recipe) {
        recipeViewModel.deleteRecipe(recipe);

    }
}
