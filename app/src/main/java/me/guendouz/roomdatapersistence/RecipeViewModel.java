package me.guendouz.roomdatapersistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import me.guendouz.roomdatapersistence.entity.Recipe;

/**
 * A ViewModel that provides access to Recipe table and keep track of the changes on this particular table.
 * We are extending the AndroidViewModel class since we need an instance of the Application to create the database.
 */
public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        allRecipes = recipeRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.addRecipe(recipe);
    }

    public void deleteRecipe(Recipe recipe){
        recipeRepository.deleteRecipe(recipe);
    }

}
